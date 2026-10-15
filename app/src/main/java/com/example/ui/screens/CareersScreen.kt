package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.Career
import com.example.data.CareerFeedback
import com.example.data.StudentProfile
import com.example.data.StudyStage
import com.example.ui.components.ComparisonFloatingTray
import com.example.ui.components.EditorialBadge
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
fun CareersScreen(
  careers: List<Career>,
  profile: StudentProfile,
  savedCareers: Set<String>,
  comparedCareerIds: List<String>,
  searchQuery: String,
  selectedField: String,
  selectedInterest: String,
  onSearchChange: (String) -> Unit,
  onFieldChange: (String) -> Unit,
  onInterestChange: (String) -> Unit,
  onCareerClick: (Career) -> Unit,
  onToggleSaveCareer: (String) -> Unit,
  onToggleCompareCareer: (String) -> Unit,
  onClearComparisons: () -> Unit,
  onOpenCompareView: () -> Unit,
  onCareerFeedback: (String, CareerFeedback) -> Unit,
  selectedCareerDetail: Career?,
  onDismissDetail: () -> Unit,
  modifier: Modifier = Modifier
) {
  val interestFilterOptions = listOf("All", "technology", "helping people", "nature", "business", "design", "hands-on")
  val fieldFilterOptions = listOf("All", "Technology", "Healthcare", "Agriculture", "Creative", "Finance", "Law", "Trades", "Education")

  var showFieldMenu by remember { mutableStateOf(false) }

  Box(modifier = modifier.fillMaxSize().background(IvoryBackground)) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      item {
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          EditorialBadge(
            text = "Exploration Collection",
            backgroundColor = SageSurface,
            textColor = InkForestPrimary
          )
          Text(
            text = "What kind of work would you like to explore?",
            style = MaterialTheme.typography.displaySmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Normal,
              color = InkForestDeep
            )
          )
          Text(
            text = "Browse possibilities without feeling pressured to pick a single lifelong title. You can bookmark options and mark what appeals to you.",
            style = MaterialTheme.typography.bodyMedium.copy(color = InkForestMuted)
          )
        }
      }

      // Search & Field Filter Row
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            placeholder = { Text("Search careers or skills...", fontSize = 13.sp) },
            leadingIcon = {
              Icon(Icons.Default.Search, contentDescription = null, tint = InkForestMuted)
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = TerracottaAccent,
              unfocusedBorderColor = BorderNeutral,
              focusedContainerColor = PaperWhite,
              unfocusedContainerColor = PaperWhite
            ),
            modifier = Modifier
              .weight(1f)
              .testTag("careers_search_input"),
            singleLine = true
          )

          Box {
            OutlinedButton(
              onClick = { showFieldMenu = true },
              shape = RoundedCornerShape(12.dp),
              border = BorderStroke(1.dp, if (selectedField != "All") TerracottaAccent else BorderNeutral),
              colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (selectedField != "All") TerracottaContainer.copy(alpha = 0.5f) else PaperWhite
              ),
              modifier = Modifier.testTag("careers_field_filter_button")
            ) {
              Icon(Icons.Default.FilterList, contentDescription = null, tint = InkForestDeep, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                if (selectedField == "All") "Field" else selectedField,
                color = InkForestDeep,
                style = MaterialTheme.typography.labelSmall
              )
            }

            DropdownMenu(
              expanded = showFieldMenu,
              onDismissRequest = { showFieldMenu = false }
            ) {
              fieldFilterOptions.forEach { f ->
                DropdownMenuItem(
                  text = { Text(f) },
                  onClick = {
                    onFieldChange(f)
                    showFieldMenu = false
                  }
                )
              }
            }
          }
        }
      }

      // Interest filter chips
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          interestFilterOptions.forEach { opt ->
            val isSelected = selectedInterest.equals(opt, ignoreCase = true)
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = if (isSelected) TerracottaAccent else SageSurface,
              border = BorderStroke(1.dp, if (isSelected) TerracottaAccent else BorderNeutral),
              modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable { onInterestChange(opt) }
                .testTag("interest_chip_$opt")
            ) {
              Text(
                text = opt.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.labelSmall.copy(
                  fontFamily = EditorialSansFont,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  color = if (isSelected) PaperWhite else InkForestDeep
                ),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
              )
            }
          }
        }
      }

      // Result count note
      item {
        Text(
          text = "Showing ${careers.size} possibilities",
          style = MaterialTheme.typography.bodySmall.copy(
            color = InkForestMuted,
            fontFamily = EditorialSansFont
          )
        )
      }

      // Career Cards
      items(careers, key = { it.id }) { career ->
        val isSaved = savedCareers.contains(career.id)
        val isCompared = comparedCareerIds.contains(career.id)
        val feedback = profile.feedback[career.id]

        CareerCard(
          career = career,
          isSaved = isSaved,
          isCompared = isCompared,
          feedback = feedback,
          onCardClick = { onCareerClick(career) },
          onToggleSave = { onToggleSaveCareer(career.id) },
          onToggleCompare = { onToggleCompareCareer(career.id) },
          onFeedback = { fb -> onCareerFeedback(career.id, fb) }
        )
      }

      item {
        Spacer(modifier = Modifier.height(80.dp))
      }
    }

    // Comparison Floating Bar
    ComparisonFloatingTray(
      selectedCount = comparedCareerIds.size,
      itemTypeLabel = "careers",
      onCompareClicked = onOpenCompareView,
      onClearClicked = onClearComparisons,
      modifier = Modifier.align(Alignment.BottomCenter)
    )

    // Career Detail Sheet/Dialog
    if (selectedCareerDetail != null) {
      CareerDetailDialog(
        career = selectedCareerDetail,
        isSaved = savedCareers.contains(selectedCareerDetail.id),
        isCompared = comparedCareerIds.contains(selectedCareerDetail.id),
        feedback = profile.feedback[selectedCareerDetail.id],
        onDismiss = onDismissDetail,
        onToggleSave = { onToggleSaveCareer(selectedCareerDetail.id) },
        onToggleCompare = { onToggleCompareCareer(selectedCareerDetail.id) },
        onFeedback = { fb -> onCareerFeedback(selectedCareerDetail.id, fb) }
      )
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CareerCard(
  career: Career,
  isSaved: Boolean,
  isCompared: Boolean,
  feedback: CareerFeedback?,
  onCardClick: () -> Unit,
  onToggleSave: () -> Unit,
  onToggleCompare: () -> Unit,
  onFeedback: (CareerFeedback) -> Unit
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = PaperWhite),
    border = BorderStroke(
      width = if (isCompared) 2.dp else 1.dp,
      color = if (isCompared) TerracottaAccent else BorderNeutral
    ),
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onCardClick() }
      .testTag("career_card_${career.id}")
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
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

        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(
            onClick = onToggleCompare,
            modifier = Modifier.size(36.dp).testTag("compare_career_${career.id}")
          ) {
            Icon(
              imageVector = Icons.Default.CompareArrows,
              contentDescription = if (isCompared) "Remove from comparison" else "Add to comparison",
              tint = if (isCompared) TerracottaAccent else InkForestMuted
            )
          }

          IconButton(
            onClick = onToggleSave,
            modifier = Modifier.size(36.dp).testTag("save_career_${career.id}")
          ) {
            Icon(
              imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
              contentDescription = if (isSaved) "Saved" else "Save career",
              tint = if (isSaved) TerracottaAccent else InkForestMuted
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = career.title,
        style = MaterialTheme.typography.titleLarge.copy(
          fontFamily = EditorialSerifFont,
          fontWeight = FontWeight.Bold,
          color = InkForestDeep
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

      // Why this may interest you
      Surface(
        shape = RoundedCornerShape(8.dp),
        color = GoldMutedLight,
        border = BorderStroke(1.dp, GoldMuted.copy(alpha = 0.25f)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(Icons.Default.Lightbulb, contentDescription = null, tint = GoldMuted, modifier = Modifier.size(14.dp))
          Text(
            text = career.whyItMayInterestYou,
            style = MaterialTheme.typography.bodySmall.copy(
              color = InkForestDeep,
              fontSize = 11.5.sp
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Relevant skills
      Text(
        text = "Key skills often used:",
        style = MaterialTheme.typography.labelSmall.copy(color = InkForestMuted, fontWeight = FontWeight.Medium)
      )
      Spacer(modifier = Modifier.height(4.dp))
      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        career.relevantSkills.take(3).forEach { skill ->
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = SageSurfaceVariant,
            modifier = Modifier.padding(bottom = 2.dp)
          ) {
            Text(
              text = skill,
              style = MaterialTheme.typography.labelSmall.copy(
                color = InkForestDeep,
                fontSize = 11.sp
              ),
              modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))
      HorizontalDivider(color = BorderNeutral.copy(alpha = 0.5f))
      Spacer(modifier = Modifier.height(10.dp))

      // Feedback row & Explore Details action
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Interested button
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = if (feedback == CareerFeedback.INTERESTED) TerracottaContainer else SageSurface,
            border = BorderStroke(1.dp, if (feedback == CareerFeedback.INTERESTED) TerracottaAccent else BorderNeutral),
            modifier = Modifier
              .clickable { onFeedback(CareerFeedback.INTERESTED) }
              .testTag("feedback_interested_${career.id}")
          ) {
            Text(
              text = "Interested",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = if (feedback == CareerFeedback.INTERESTED) TerracottaAccent else InkForestDeep
              ),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
            )
          }

          // Unsure button
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = if (feedback == CareerFeedback.UNSURE) GoldMutedLight else SageSurface,
            border = BorderStroke(1.dp, if (feedback == CareerFeedback.UNSURE) GoldMuted else BorderNeutral),
            modifier = Modifier.clickable { onFeedback(CareerFeedback.UNSURE) }
          ) {
            Text(
              text = "Unsure",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = if (feedback == CareerFeedback.UNSURE) GoldMuted else InkForestDeep
              ),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
            )
          }

          // Not for me
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = if (feedback == CareerFeedback.NOT_FOR_ME) BorderNeutral.copy(alpha = 0.5f) else SageSurface,
            modifier = Modifier.clickable { onFeedback(CareerFeedback.NOT_FOR_ME) }
          ) {
            Text(
              text = "Not for me",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Normal,
                color = InkForestMuted
              ),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
            )
          }
        }

        Text(
          text = "Explore details ->",
          style = MaterialTheme.typography.labelSmall.copy(
            color = TerracottaAccent,
            fontWeight = FontWeight.Bold
          ),
          modifier = Modifier.clickable { onCardClick() }
        )
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CareerDetailDialog(
  career: Career,
  isSaved: Boolean,
  isCompared: Boolean,
  feedback: CareerFeedback?,
  onDismiss: () -> Unit,
  onToggleSave: () -> Unit,
  onToggleCompare: () -> Unit,
  onFeedback: (CareerFeedback) -> Unit
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = PaperWhite,
      border = BorderStroke(1.dp, BorderNeutral),
      modifier = Modifier
        .fillMaxWidth(0.92f)
        .padding(vertical = 24.dp)
    ) {
      Column(
        modifier = Modifier
          .padding(22.dp)
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          EditorialBadge(text = career.field)
          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = InkForestDeep)
          }
        }

        Text(
          text = career.title,
          style = MaterialTheme.typography.displaySmall.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Bold,
            color = InkForestDeep
          )
        )

        Text(
          text = career.overview,
          style = MaterialTheme.typography.bodyLarge.copy(
            color = InkForestMuted,
            lineHeight = 22.sp
          )
        )

        HorizontalDivider(color = BorderNeutral)

        // Section: What the work can involve
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "What the work can involve:",
            style = MaterialTheme.typography.titleSmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Bold,
              color = InkForestDeep
            )
          )
          career.workInvolves.forEach { item ->
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
                text = item,
                style = MaterialTheme.typography.bodyMedium.copy(color = InkForestDeep, lineHeight = 20.sp)
              )
            }
          }
        }

        // Section: Possible routes to explore
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "Possible study or training routes:",
            style = MaterialTheme.typography.titleSmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Bold,
              color = InkForestDeep
            )
          )
          career.possibleRoutes.forEach { route ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = SageSurface.copy(alpha = 0.5f),
              border = BorderStroke(1.dp, BorderNeutral),
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(
                text = route,
                style = MaterialTheme.typography.bodySmall.copy(
                  color = InkForestDeep,
                  lineHeight = 18.sp
                ),
                modifier = Modifier.padding(10.dp)
              )
            }
          }
        }

        // Section: Things to investigate
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "Things to investigate on your own:",
            style = MaterialTheme.typography.titleSmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Bold,
              color = InkForestDeep
            )
          )
          career.thingsToInvestigate.forEach { item ->
            Row(
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(Icons.Default.HelpOutline, contentDescription = null, tint = GoldMuted, modifier = Modifier.size(16.dp))
              Text(
                text = item,
                style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep, lineHeight = 18.sp)
              )
            }
          }
        }

        // Feedback on suggestion
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = SageSurface.copy(alpha = 0.5f)),
          border = BorderStroke(1.dp, BorderNeutral)
        ) {
          Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
              text = "Does this direction appeal to you?",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = InkForestDeep)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
              Button(
                onClick = { onFeedback(CareerFeedback.INTERESTED) },
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (feedback == CareerFeedback.INTERESTED) TerracottaAccent else PaperWhite
                ),
                border = BorderStroke(1.dp, if (feedback == CareerFeedback.INTERESTED) TerracottaAccent else BorderNeutral),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
              ) {
                Text(
                  "Interested",
                  color = if (feedback == CareerFeedback.INTERESTED) PaperWhite else InkForestDeep,
                  fontSize = 12.sp
                )
              }

              Button(
                onClick = { onFeedback(CareerFeedback.UNSURE) },
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (feedback == CareerFeedback.UNSURE) GoldMuted else PaperWhite
                ),
                border = BorderStroke(1.dp, if (feedback == CareerFeedback.UNSURE) GoldMuted else BorderNeutral),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
              ) {
                Text(
                  "Unsure",
                  color = if (feedback == CareerFeedback.UNSURE) PaperWhite else InkForestDeep,
                  fontSize = 12.sp
                )
              }

              Button(
                onClick = { onFeedback(CareerFeedback.NOT_FOR_ME) },
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (feedback == CareerFeedback.NOT_FOR_ME) BorderNeutral else PaperWhite
                ),
                border = BorderStroke(1.dp, BorderNeutral),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
              ) {
                Text(
                  "Not for me",
                  color = InkForestMuted,
                  fontSize = 12.sp
                )
              }
            }
          }
        }

        // Bottom dialog actions
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          OutlinedButton(
            onClick = onToggleCompare,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
          ) {
            Icon(Icons.Default.CompareArrows, contentDescription = null, tint = InkForestDeep, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(if (isCompared) "In comparison" else "Compare", color = InkForestDeep)
          }

          Button(
            onClick = onToggleSave,
            colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
          ) {
            Icon(if (isSaved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder, contentDescription = null, tint = PaperWhite, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(if (isSaved) "Saved" else "Save option", color = PaperWhite)
          }
        }
      }
    }
  }
}
