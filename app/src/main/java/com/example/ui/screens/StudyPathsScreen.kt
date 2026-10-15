package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Pathway
import com.example.data.SampleData
import com.example.data.StudyStage
import com.example.ui.ScreenDestination
import com.example.ui.components.EditorialBadge
import com.example.ui.components.ReassuranceCard
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
import com.example.ui.theme.TerracottaAccent
import com.example.ui.theme.TerracottaContainer

@Composable
fun StudyPathsScreen(
  currentStage: StudyStage,
  onStageChange: (StudyStage) -> Unit,
  onNavigate: (ScreenDestination) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedTab by remember(currentStage) {
    mutableStateOf(if (currentStage == StudyStage.AFTER_12TH) 1 else 0)
  }

  val activeStage = if (selectedTab == 0) StudyStage.AFTER_10TH else StudyStage.AFTER_12TH

  val displayedPathways = SampleData.samplePathways.filter { it.stage == activeStage }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(IvoryBackground)
      .padding(horizontal = 20.dp),
    verticalArrangement = Arrangement.spacedBy(18.dp)
  ) {
    item {
      Spacer(modifier = Modifier.height(16.dp))
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        EditorialBadge(
          text = "Structured Study Pathways",
          backgroundColor = SageSurface,
          textColor = InkForestPrimary,
          icon = Icons.Default.School
        )
        Text(
          text = "Explore educational routes",
          style = MaterialTheme.typography.displaySmall.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Normal,
            color = InkForestDeep
          )
        )
        Text(
          text = "Academic streams, technical diplomas, and hands-on apprenticeships are equally valuable choices in India's education ecosystem.",
          style = MaterialTheme.typography.bodyMedium.copy(color = InkForestMuted)
        )
      }
    }

    // Prominent Stage Switcher Tabs
    item {
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = SageSurface.copy(alpha = 0.6f),
        border = BorderStroke(1.dp, BorderNeutral),
        modifier = Modifier.fillMaxWidth()
      ) {
        TabRow(
          selectedTabIndex = selectedTab,
          containerColor = SageSurface.copy(alpha = 0.6f),
          indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
              modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
              color = TerracottaAccent,
              height = 3.dp
            )
          },
          divider = {}
        ) {
          Tab(
            selected = selectedTab == 0,
            onClick = {
              selectedTab = 0
              onStageChange(StudyStage.AFTER_10TH)
            },
            text = {
              Text(
                "After Class 10",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontFamily = EditorialSansFont,
                  fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium,
                  color = if (selectedTab == 0) TerracottaAccent else InkForestMuted
                )
              )
            },
            modifier = Modifier.testTag("tab_after_10th")
          )

          Tab(
            selected = selectedTab == 1,
            onClick = {
              selectedTab = 1
              onStageChange(StudyStage.AFTER_12TH)
            },
            text = {
              Text(
                "After Intermediate / 12th",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontFamily = EditorialSansFont,
                  fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium,
                  color = if (selectedTab == 1) TerracottaAccent else InkForestMuted
                )
              )
            },
            modifier = Modifier.testTag("tab_after_12th")
          )
        }
      }
    }

    // Official variation disclaimer
    item {
      Surface(
        shape = RoundedCornerShape(10.dp),
        color = GoldMutedLight,
        border = BorderStroke(1.dp, GoldMuted.copy(alpha = 0.3f)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.Top,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = GoldMuted,
            modifier = Modifier.size(16.dp)
          )
          Text(
            text = "Subject combinations, entrance eligibility, course duration, recognition, fees, and admission criteria vary significantly by state, board, institution, and academic year. Always verify with official authorities.",
            style = MaterialTheme.typography.bodySmall.copy(
              color = InkForestDeep,
              lineHeight = 16.sp,
              fontSize = 11.5.sp
            )
          )
        }
      }
    }

    // Pathways items
    items(displayedPathways, key = { it.id }) { pathway ->
      PathwayCard(
        pathway = pathway,
        onExploreCourses = { onNavigate(ScreenDestination.COURSES) },
        onExploreCareers = { onNavigate(ScreenDestination.CAREERS) }
      )
    }

    item {
      ReassuranceCard(
        title = "Changing paths later",
        body = "Many students transition between fields (e.g. from engineering polytechnic into corporate management, or from commerce into digital design). Focus on solid fundamentals now."
      )
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PathwayCard(
  pathway: Pathway,
  onExploreCourses: () -> Unit,
  onExploreCareers: () -> Unit
) {
  var isExpanded by remember { mutableStateOf(false) }
  val arrowRotation by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f, label = "arrow")

  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = PaperWhite),
    border = BorderStroke(1.dp, BorderNeutral),
    modifier = Modifier
      .fillMaxWidth()
      .testTag("pathway_card_${pathway.id}")
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        EditorialBadge(
          text = pathway.category,
          backgroundColor = SageSurface,
          textColor = InkForestPrimary
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = pathway.title,
        style = MaterialTheme.typography.titleLarge.copy(
          fontFamily = EditorialSerifFont,
          fontWeight = FontWeight.Bold,
          color = InkForestDeep
        )
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = pathway.shortDescription,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = InkForestMuted,
          lineHeight = 21.sp
        )
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Section 1: Who may want to explore
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(IvoryBackground)
          .padding(12.dp)
      ) {
        Text(
          text = "Who may want to explore this:",
          style = MaterialTheme.typography.labelSmall.copy(
            color = TerracottaAccent,
            fontWeight = FontWeight.Bold
          )
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
          text = pathway.whoMayWantToExplore,
          style = MaterialTheme.typography.bodySmall.copy(
            color = InkForestDeep,
            lineHeight = 17.sp
          )
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Section 2: What learning involves
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(SageSurface.copy(alpha = 0.4f))
          .padding(12.dp)
      ) {
        Text(
          text = "What learning can involve:",
          style = MaterialTheme.typography.labelSmall.copy(
            color = InkForestPrimary,
            fontWeight = FontWeight.Bold
          )
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
          text = pathway.whatLearningInvolves,
          style = MaterialTheme.typography.bodySmall.copy(
            color = InkForestDeep,
            lineHeight = 17.sp
          )
        )
      }

      // Sample Directions Chips
      if (pathway.sampleDirections.isNotEmpty()) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
          text = "Sample directions & fields:",
          style = MaterialTheme.typography.labelSmall.copy(
            color = InkForestMuted,
            fontWeight = FontWeight.Medium
          )
        )
        Spacer(modifier = Modifier.height(6.dp))
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          pathway.sampleDirections.forEach { dir ->
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = PaperWhite,
              border = BorderStroke(1.dp, BorderNeutral)
            ) {
              Text(
                text = dir,
                style = MaterialTheme.typography.labelSmall.copy(
                  color = InkForestDeep,
                  fontSize = 11.5.sp
                ),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))
      HorizontalDivider(color = BorderNeutral.copy(alpha = 0.5f))
      Spacer(modifier = Modifier.height(10.dp))

      // Expandable "Questions to check"
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { isExpanded = !isExpanded }
          .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(
            imageVector = Icons.Default.HelpOutline,
            contentDescription = null,
            tint = TerracottaAccent,
            modifier = Modifier.size(16.dp)
          )
          Text(
            text = "Questions to check before choosing",
            style = MaterialTheme.typography.labelMedium.copy(
              fontFamily = EditorialSansFont,
              fontWeight = FontWeight.SemiBold,
              color = InkForestDeep
            )
          )
        }
        Icon(
          imageVector = Icons.Default.ExpandMore,
          contentDescription = if (isExpanded) "Collapse" else "Expand",
          tint = InkForestMuted,
          modifier = Modifier.rotate(arrowRotation)
        )
      }

      AnimatedVisibility(visible = isExpanded) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          pathway.questionsToCheck.forEach { q ->
            Row(
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(6.dp)
                  .padding(top = 6.dp)
                  .clip(CircleShape)
                  .background(TerracottaAccent)
              )
              Text(
                text = q,
                style = MaterialTheme.typography.bodySmall.copy(
                  color = InkForestDeep,
                  lineHeight = 17.sp
                )
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Bottom links
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Button(
          onClick = onExploreCourses,
          colors = ButtonDefaults.buttonColors(containerColor = SageSurfaceVariant),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text(
            "View related courses",
            color = InkForestDeep,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
          )
        }

        Button(
          onClick = onExploreCareers,
          colors = ButtonDefaults.buttonColors(containerColor = TerracottaContainer),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text(
            "View related careers",
            color = TerracottaAccent,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
          )
        }
      }
    }
  }
}
