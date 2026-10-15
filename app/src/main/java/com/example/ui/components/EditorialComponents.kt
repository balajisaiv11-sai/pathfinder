package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Close
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.StudyStage
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
fun EditorialBadge(
  text: String,
  modifier: Modifier = Modifier,
  backgroundColor: Color = SageSurface,
  textColor: Color = InkForestPrimary,
  icon: ImageVector? = null
) {
  Surface(
    modifier = modifier.clip(RoundedCornerShape(8.dp)),
    color = backgroundColor,
    border = BorderStroke(1.dp, BorderNeutral.copy(alpha = 0.6f))
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      if (icon != null) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = textColor,
          modifier = Modifier.size(13.dp)
        )
      }
      Text(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(
          fontFamily = EditorialSansFont,
          fontWeight = FontWeight.SemiBold,
          color = textColor
        )
      )
    }
  }
}

@Composable
fun StageSelectorPanels(
  selectedStage: StudyStage,
  onStageSelected: (StudyStage) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier.fillMaxWidth()) {
    Text(
      text = "Where are you currently exploring?",
      style = MaterialTheme.typography.titleSmall.copy(
        fontFamily = EditorialSansFont,
        fontWeight = FontWeight.SemiBold,
        color = InkForestPrimary
      ),
      modifier = Modifier.padding(bottom = 10.dp)
    )

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      // Class 10 Panel
      val is10thSelected = selectedStage == StudyStage.AFTER_10TH
      Card(
        modifier = Modifier
          .weight(1f)
          .testTag("stage_selector_class_10")
          .clickable { onStageSelected(StudyStage.AFTER_10TH) },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (is10thSelected) TerracottaContainer.copy(alpha = 0.45f) else PaperWhite
        ),
        border = BorderStroke(
          width = if (is10thSelected) 2.dp else 1.dp,
          color = if (is10thSelected) TerracottaAccent else BorderNeutral
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (is10thSelected) 2.dp else 0.dp)
      ) {
        Column(
          modifier = Modifier.padding(14.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            EditorialBadge(
              text = "Class 10",
              backgroundColor = if (is10thSelected) TerracottaAccent else SageSurface,
              textColor = if (is10thSelected) PaperWhite else InkForestPrimary
            )
            Box(
              modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(if (is10thSelected) TerracottaAccent else SageSurface)
                .border(1.dp, if (is10thSelected) TerracottaAccent else BorderNeutral, CircleShape),
              contentAlignment = Alignment.Center
            ) {
              if (is10thSelected) {
                Icon(
                  imageVector = Icons.Default.Check,
                  contentDescription = "Selected",
                  tint = PaperWhite,
                  modifier = Modifier.size(14.dp)
                )
              }
            }
          }
          Text(
            text = "I'm choosing after Class 10",
            style = MaterialTheme.typography.titleMedium.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.SemiBold,
              color = InkForestDeep,
              fontSize = 17.sp
            )
          )
          Text(
            text = "Explore 11th & 12th streams (Science/Commerce/Arts), Polytechnic Diplomas, ITI Trades & Open Schooling.",
            style = MaterialTheme.typography.bodySmall.copy(
              color = InkForestMuted,
              lineHeight = 16.sp
            )
          )
        }
      }

      // Class 12 / Inter Panel
      val is12thSelected = selectedStage == StudyStage.AFTER_12TH
      Card(
        modifier = Modifier
          .weight(1f)
          .testTag("stage_selector_class_12")
          .clickable { onStageSelected(StudyStage.AFTER_12TH) },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (is12thSelected) TerracottaContainer.copy(alpha = 0.45f) else PaperWhite
        ),
        border = BorderStroke(
          width = if (is12thSelected) 2.dp else 1.dp,
          color = if (is12thSelected) TerracottaAccent else BorderNeutral
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (is12thSelected) 2.dp else 0.dp)
      ) {
        Column(
          modifier = Modifier.padding(14.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            EditorialBadge(
              text = "Intermediate / 12",
              backgroundColor = if (is12thSelected) TerracottaAccent else SageSurface,
              textColor = if (is12thSelected) PaperWhite else InkForestPrimary
            )
            Box(
              modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(if (is12thSelected) TerracottaAccent else SageSurface)
                .border(1.dp, if (is12thSelected) TerracottaAccent else BorderNeutral, CircleShape),
              contentAlignment = Alignment.Center
            ) {
              if (is12thSelected) {
                Icon(
                  imageVector = Icons.Default.Check,
                  contentDescription = "Selected",
                  tint = PaperWhite,
                  modifier = Modifier.size(14.dp)
                )
              }
            }
          }
          Text(
            text = "I'm choosing after Class 12",
            style = MaterialTheme.typography.titleMedium.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.SemiBold,
              color = InkForestDeep,
              fontSize = 17.sp
            )
          )
          Text(
            text = "Explore Bachelor degrees (B.A./B.Sc./B.Com.), Professional degrees, Paramedical, Apprenticeships & Work-study.",
            style = MaterialTheme.typography.bodySmall.copy(
              color = InkForestMuted,
              lineHeight = 16.sp
            )
          )
        }
      }
    }
  }
}

@Composable
fun ReassuranceCard(
  modifier: Modifier = Modifier,
  title: String = "You don't need to decide your entire future today.",
  body: String = "Education in India has multiple transfer routes, lateral entry options, and second chances. Explore with curiosity—you can always adapt as your interests evolve."
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = SageSurface.copy(alpha = 0.7f)),
    border = BorderStroke(1.dp, BorderNeutral)
  ) {
    Row(
      modifier = Modifier.padding(16.dp),
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Box(
        modifier = Modifier
          .size(32.dp)
          .clip(CircleShape)
          .background(GoldMutedLight),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.Info,
          contentDescription = null,
          tint = GoldMuted,
          modifier = Modifier.size(18.dp)
        )
      }
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleSmall.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.SemiBold,
            color = InkForestDeep
          )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = body,
          style = MaterialTheme.typography.bodySmall.copy(
            color = InkForestMuted,
            lineHeight = 18.sp
          )
        )
      }
    }
  }
}

@Composable
fun ComparisonFloatingTray(
  selectedCount: Int,
  itemTypeLabel: String,
  onCompareClicked: () -> Unit,
  onClearClicked: () -> Unit,
  modifier: Modifier = Modifier
) {
  AnimatedVisibility(
    visible = selectedCount > 0,
    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
    modifier = modifier
  ) {
    Surface(
      shape = RoundedCornerShape(16.dp),
      color = InkForestDeep,
      shadowElevation = 6.dp,
      border = BorderStroke(1.dp, TerracottaAccent.copy(alpha = 0.5f)),
      modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .fillMaxWidth()
    ) {
      Row(
        modifier = Modifier
          .padding(horizontal = 16.dp, vertical = 12.dp)
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Box(
            modifier = Modifier
              .size(28.dp)
              .clip(CircleShape)
              .background(TerracottaAccent),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "$selectedCount/3",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = PaperWhite
              )
            )
          }
          Text(
            text = "$itemTypeLabel selected",
            style = MaterialTheme.typography.bodyMedium.copy(
              color = PaperWhite,
              fontWeight = FontWeight.Medium
            )
          )
        }

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          if (selectedCount >= 2) {
            Button(
              onClick = onCompareClicked,
              colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
              shape = RoundedCornerShape(8.dp),
              modifier = Modifier.testTag("compare_tray_action")
            ) {
              Icon(
                imageVector = Icons.Default.CompareArrows,
                contentDescription = null,
                tint = PaperWhite,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text("Compare", color = PaperWhite, style = MaterialTheme.typography.labelMedium)
            }
          } else {
            Text(
              text = "Select 1 more to compare",
              style = MaterialTheme.typography.bodySmall.copy(
                color = PaperWhite.copy(alpha = 0.75f),
                fontFamily = EditorialSansFont
              )
            )
          }

          IconButton(
            onClick = onClearClicked,
            modifier = Modifier.size(32.dp)
          ) {
            Icon(
              imageVector = Icons.Outlined.Close,
              contentDescription = "Clear comparison tray",
              tint = PaperWhite.copy(alpha = 0.7f),
              modifier = Modifier.size(18.dp)
            )
          }
        }
      }
    }
  }
}
