package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.Career
import com.example.data.SampleData
import com.example.data.StudentProfile
import com.example.data.StudyStage
import com.example.ui.ScreenDestination
import com.example.ui.components.EditorialBadge
import com.example.ui.components.ReassuranceCard
import com.example.ui.components.StageSelectorPanels
import com.example.ui.theme.BorderNeutral
import com.example.ui.theme.EditorialSansFont
import com.example.ui.theme.EditorialSerifFont
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldMutedLight
import com.example.ui.theme.InkForestDeep
import com.example.ui.theme.InkForestMuted
import com.example.ui.theme.InkForestPrimary
import com.example.ui.theme.IvoryBackground
import com.example.ui.theme.PaperWhite
import com.example.ui.theme.SageSurface
import com.example.ui.theme.SageSurfaceVariant
import com.example.ui.theme.TerracottaAccent
import com.example.ui.theme.TerracottaContainer

@Composable
fun HomeScreen(
  profile: StudentProfile,
  savedCareers: Set<String>,
  onNavigate: (ScreenDestination) -> Unit,
  onStageSelected: (StudyStage) -> Unit,
  onCareerClick: (Career) -> Unit,
  onToggleSaveCareer: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  // Curate 3 recommendation directions based on current stage
  val recommendedCareers = SampleData.sampleCareers.filter { career ->
    when (profile.studyStage) {
      StudyStage.AFTER_10TH -> career.stageRelevance == StudyStage.AFTER_10TH || career.stageRelevance == StudyStage.BOTH
      StudyStage.AFTER_12TH -> career.stageRelevance == StudyStage.AFTER_12TH || career.stageRelevance == StudyStage.BOTH
      StudyStage.BOTH -> true
    }
  }.take(3)

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(IvoryBackground)
      .verticalScroll(scrollState)
      .padding(horizontal = 20.dp, vertical = 24.dp),
    verticalArrangement = Arrangement.spacedBy(28.dp)
  ) {

    // --- Hero Section ---
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      EditorialBadge(
        text = "Guidance for your next chapter",
        backgroundColor = SageSurface,
        textColor = InkForestPrimary,
        icon = Icons.Default.Explore
      )

      Text(
        text = "Your next step can start with curiosity.",
        style = MaterialTheme.typography.displayMedium.copy(
          fontFamily = EditorialSerifFont,
          fontWeight = FontWeight.Normal,
          color = InkForestDeep,
          lineHeight = 38.sp
        )
      )

      Text(
        text = "Pathfinder India helps you explore courses, streams, and careers based on your natural interests and practical goals—without locking you into a single path.",
        style = MaterialTheme.typography.bodyLarge.copy(
          fontFamily = EditorialSansFont,
          color = InkForestMuted,
          lineHeight = 23.sp
        )
      )

      // Hero Artwork
      Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, BorderNeutral),
        colors = CardDefaults.cardColors(containerColor = SageSurfaceVariant),
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
      ) {
        Image(
          painter = painterResource(id = R.drawable.hero_pathways),
          contentDescription = "Linocut illustration of pathways, open books, and morning horizons",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
        )
      }

      // Actions
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Button(
          onClick = { onNavigate(ScreenDestination.DISCOVER) },
          colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .weight(1f)
            .height(48.dp)
            .testTag("home_start_exploring_button")
        ) {
          Text(
            text = "Start exploring",
            style = MaterialTheme.typography.labelLarge.copy(
              color = PaperWhite,
              fontWeight = FontWeight.SemiBold
            )
          )
          Spacer(modifier = Modifier.width(6.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = PaperWhite,
            modifier = Modifier.size(16.dp)
          )
        }

        OutlinedButton(
          onClick = { onNavigate(ScreenDestination.STUDY_PATHS) },
          shape = RoundedCornerShape(12.dp),
          border = BorderStroke(1.dp, InkForestPrimary),
          modifier = Modifier
            .weight(1f)
            .height(48.dp)
            .testTag("home_browse_paths_button")
        ) {
          Text(
            text = "Browse study paths",
            style = MaterialTheme.typography.labelLarge.copy(
              color = InkForestPrimary,
              fontWeight = FontWeight.SemiBold
            )
          )
        }
      }
    }

    HorizontalDivider(color = BorderNeutral, thickness = 1.dp)

    // --- Interactive Stage Selector ---
    StageSelectorPanels(
      selectedStage = profile.studyStage,
      onStageSelected = onStageSelected
    )

    // --- A few directions to get you started ---
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "A few directions to get you started",
            style = MaterialTheme.typography.headlineSmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.SemiBold,
              color = InkForestDeep
            )
          )
          Text(
            text = "Examples matched to ${profile.studyStage.displayName.lowercase()}",
            style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted)
          )
        }

        TextButton(
          onClick = { onNavigate(ScreenDestination.CAREERS) },
          modifier = Modifier.testTag("view_all_careers_link")
        ) {
          Text(
            text = "View all",
            style = MaterialTheme.typography.labelMedium.copy(
              color = TerracottaAccent,
              fontWeight = FontWeight.Bold
            )
          )
        }
      }

      recommendedCareers.forEach { career ->
        val isSaved = savedCareers.contains(career.id)
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = PaperWhite),
          border = BorderStroke(1.dp, BorderNeutral),
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onCareerClick(career) }
            .testTag("home_career_card_${career.id}")
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              EditorialBadge(
                text = career.field,
                backgroundColor = SageSurface,
                textColor = InkForestPrimary
              )

              IconButton(
                onClick = { onToggleSaveCareer(career.id) },
                modifier = Modifier.size(36.dp)
              ) {
                Icon(
                  imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                  contentDescription = if (isSaved) "Saved" else "Save career",
                  tint = if (isSaved) TerracottaAccent else InkForestMuted
                )
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = career.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = EditorialSerifFont,
                fontWeight = FontWeight.Bold,
                color = InkForestDeep,
                fontSize = 18.sp
              )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
              text = career.overview,
              style = MaterialTheme.typography.bodyMedium.copy(
                color = InkForestMuted,
                lineHeight = 20.sp
              )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Reason badge
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = GoldMutedLight,
              border = BorderStroke(1.dp, GoldMuted.copy(alpha = 0.3f))
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Lightbulb,
                  contentDescription = null,
                  tint = GoldMuted,
                  modifier = Modifier.size(14.dp)
                )
                Text(
                  text = career.whyItMayInterestYou,
                  style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = EditorialSansFont,
                    color = InkForestDeep,
                    fontSize = 12.sp
                  )
                )
              }
            }
          }
        }
      }
    }

    // --- Compact Three-Step Explainer ---
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SageSurface.copy(alpha = 0.5f)),
      border = BorderStroke(1.dp, BorderNeutral),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Text(
          text = "How to explore without pressure",
          style = MaterialTheme.typography.titleLarge.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.SemiBold,
            color = InkForestDeep
          )
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Box(
            modifier = Modifier
              .size(28.dp)
              .clip(CircleShape)
              .background(TerracottaAccent),
            contentAlignment = Alignment.Center
          ) {
            Text("1", color = PaperWhite, fontWeight = FontWeight.Bold, fontSize = 13.sp)
          }
          Column(modifier = Modifier.weight(1f)) {
            Text("Start with what you enjoy", fontWeight = FontWeight.SemiBold, color = InkForestDeep)
            Text(
              "Share the subjects, problems, or crafts that feel engaging without worrying about career titles.",
              style = MaterialTheme.typography.bodySmall,
              color = InkForestMuted
            )
          }
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Box(
            modifier = Modifier
              .size(28.dp)
              .clip(CircleShape)
              .background(TerracottaAccent),
            contentAlignment = Alignment.Center
          ) {
            Text("2", color = PaperWhite, fontWeight = FontWeight.Bold, fontSize = 13.sp)
          }
          Column(modifier = Modifier.weight(1f)) {
            Text("Explore possible directions", fontWeight = FontWeight.SemiBold, color = InkForestDeep)
            Text(
              "Compare academic streams, technical diplomas, and trades equally. See what daily work looks like.",
              style = MaterialTheme.typography.bodySmall,
              color = InkForestMuted
            )
          }
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Box(
            modifier = Modifier
              .size(28.dp)
              .clip(CircleShape)
              .background(TerracottaAccent),
            contentAlignment = Alignment.Center
          ) {
            Text("3", color = PaperWhite, fontWeight = FontWeight.Bold, fontSize = 13.sp)
          }
          Column(modifier = Modifier.weight(1f)) {
            Text("Make a practical next-step plan", fontWeight = FontWeight.SemiBold, color = InkForestDeep)
            Text(
              "Choose one primary route and a backup. Prepare clear questions to ask a teacher or college.",
              style = MaterialTheme.typography.bodySmall,
              color = InkForestMuted
            )
          }
        }
      }
    }

    // --- Reassurance Card ---
    ReassuranceCard()

    Spacer(modifier = Modifier.height(24.dp))
  }
}
