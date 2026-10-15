package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.StudentProfile
import com.example.data.StudyStage
import com.example.ui.components.EditorialBadge
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileScreen(
  profile: StudentProfile,
  onProfileChange: (StudentProfile) -> Unit,
  onResetData: () -> Unit,
  modifier: Modifier = Modifier
) {
  var showResetConfirmation by remember { mutableStateOf(false) }

  var newSubjectText by remember { mutableStateOf("") }
  var newInterestText by remember { mutableStateOf("") }
  var newStrengthText by remember { mutableStateOf("") }
  var newPreferenceText by remember { mutableStateOf("") }
  var newGoalText by remember { mutableStateOf("") }
  var newAvoidText by remember { mutableStateOf("") }

  // Section collapse states
  var expandStageAndSubjects by remember { mutableStateOf(true) }
  var expandInterests by remember { mutableStateOf(true) }
  var expandStrengths by remember { mutableStateOf(true) }
  var expandPreferences by remember { mutableStateOf(true) }
  var expandGoals by remember { mutableStateOf(true) }
  var expandAvoid by remember { mutableStateOf(true) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(IvoryBackground)
      .padding(horizontal = 20.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item {
      Spacer(modifier = Modifier.height(16.dp))
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        EditorialBadge(
          text = "Student Profile",
          backgroundColor = SageSurface,
          textColor = InkForestPrimary,
          icon = Icons.Default.Person
        )
        Text(
          text = "Your exploration preferences",
          style = MaterialTheme.typography.displaySmall.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Normal,
            color = InkForestDeep
          )
        )
        Text(
          text = "Everything here is editable at any time. It adjusts how examples and directions are highlighted throughout the app.",
          style = MaterialTheme.typography.bodyMedium.copy(color = InkForestMuted)
        )
      }
    }

    // Section 1: Study Stage & Current Subjects
    item {
      CollapsibleSection(
        title = "Study Stage and Current Subjects",
        isExpanded = expandStageAndSubjects,
        onToggle = { expandStageAndSubjects = !expandStageAndSubjects }
      ) {
        StageSelectorPanels(
          selectedStage = profile.studyStage,
          onStageSelected = { onProfileChange(profile.copy(studyStage = it)) }
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text("Subjects you are currently studying or enjoy:", style = MaterialTheme.typography.labelSmall.copy(color = InkForestMuted))
        Spacer(modifier = Modifier.height(6.dp))

        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          profile.subjects.forEach { subj ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = SageSurface,
              border = BorderStroke(1.dp, BorderNeutral)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(text = subj, style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
                Icon(
                  imageVector = Icons.Default.Close,
                  contentDescription = "Remove",
                  tint = InkForestMuted,
                  modifier = Modifier.size(12.dp).clickable {
                    onProfileChange(profile.copy(subjects = profile.subjects - subj))
                  }
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          OutlinedTextField(
            value = newSubjectText,
            onValueChange = { newSubjectText = it },
            placeholder = { Text("Add subject (e.g. Physics, History)...", fontSize = 12.sp) },
            modifier = Modifier.weight(1f),
            singleLine = true
          )
          Button(
            onClick = {
              if (newSubjectText.isNotBlank()) {
                onProfileChange(profile.copy(subjects = profile.subjects + newSubjectText.trim()))
                newSubjectText = ""
              }
            },
            colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text("Add", color = PaperWhite)
          }
        }
      }
    }

    // Section 2: Interests and activities
    item {
      CollapsibleSection(
        title = "Interests and Activities",
        isExpanded = expandInterests,
        onToggle = { expandInterests = !expandInterests }
      ) {
        Text("Topics, hobbies, or questions you find yourself thinking about:", style = MaterialTheme.typography.labelSmall.copy(color = InkForestMuted))
        Spacer(modifier = Modifier.height(6.dp))

        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          profile.interests.forEach { item ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = SageSurface,
              border = BorderStroke(1.dp, BorderNeutral)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(text = item, style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
                Icon(
                  imageVector = Icons.Default.Close,
                  contentDescription = "Remove",
                  tint = InkForestMuted,
                  modifier = Modifier.size(12.dp).clickable {
                    onProfileChange(profile.copy(interests = profile.interests - item))
                  }
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          OutlinedTextField(
            value = newInterestText,
            onValueChange = { newInterestText = it },
            placeholder = { Text("Add interest (e.g. Robotics, Writing)...", fontSize = 12.sp) },
            modifier = Modifier.weight(1f),
            singleLine = true
          )
          Button(
            onClick = {
              if (newInterestText.isNotBlank()) {
                onProfileChange(profile.copy(interests = profile.interests + newInterestText.trim()))
                newInterestText = ""
              }
            },
            colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text("Add", color = PaperWhite)
          }
        }
      }
    }

    // Section 3: Strengths and skills to build
    item {
      CollapsibleSection(
        title = "Strengths and Skills to Build",
        isExpanded = expandStrengths,
        onToggle = { expandStrengths = !expandStrengths }
      ) {
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          profile.strengths.forEach { item ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = SageSurface,
              border = BorderStroke(1.dp, BorderNeutral)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(text = item, style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
                Icon(
                  imageVector = Icons.Default.Close,
                  contentDescription = "Remove",
                  tint = InkForestMuted,
                  modifier = Modifier.size(12.dp).clickable {
                    onProfileChange(profile.copy(strengths = profile.strengths - item))
                  }
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          OutlinedTextField(
            value = newStrengthText,
            onValueChange = { newStrengthText = it },
            placeholder = { Text("Add strength (e.g. Observation, Drawing)...", fontSize = 12.sp) },
            modifier = Modifier.weight(1f),
            singleLine = true
          )
          Button(
            onClick = {
              if (newStrengthText.isNotBlank()) {
                onProfileChange(profile.copy(strengths = profile.strengths + newStrengthText.trim()))
                newStrengthText = ""
              }
            },
            colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text("Add", color = PaperWhite)
          }
        }
      }
    }

    // Section 4: Learning Preferences
    item {
      CollapsibleSection(
        title = "Learning Preferences",
        isExpanded = expandPreferences,
        onToggle = { expandPreferences = !expandPreferences }
      ) {
        val presetPreferences = listOf("Hands-on lab / workshop learning", "Reading & conceptual discussion", "Self-paced study", "Team collaboration")
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          presetPreferences.forEach { pref ->
            val isChecked = profile.preferences.contains(pref)
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = if (isChecked) TerracottaContainer.copy(alpha = 0.5f) else PaperWhite,
              border = BorderStroke(1.dp, if (isChecked) TerracottaAccent else BorderNeutral),
              modifier = Modifier
                .fillMaxWidth()
                .clickable {
                  val updated = if (isChecked) profile.preferences - pref else profile.preferences + pref
                  onProfileChange(profile.copy(preferences = updated))
                }
            ) {
              Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = pref,
                  style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal,
                    color = InkForestDeep
                  )
                )
              }
            }
          }
        }
      }
    }

    // Section 5: Things I want to avoid
    item {
      CollapsibleSection(
        title = "Things I Want to Avoid",
        isExpanded = expandAvoid,
        onToggle = { expandAvoid = !expandAvoid }
      ) {
        Text("Things you want to keep in mind (e.g. high upfront fees, long desk-bound shifts):", style = MaterialTheme.typography.labelSmall.copy(color = InkForestMuted))
        Spacer(modifier = Modifier.height(6.dp))

        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          profile.avoidList.forEach { item ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = SageSurface,
              border = BorderStroke(1.dp, BorderNeutral)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(text = item, style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
                Icon(
                  imageVector = Icons.Default.Close,
                  contentDescription = "Remove",
                  tint = InkForestMuted,
                  modifier = Modifier.size(12.dp).clickable {
                    onProfileChange(profile.copy(avoidList = profile.avoidList - item))
                  }
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          OutlinedTextField(
            value = newAvoidText,
            onValueChange = { newAvoidText = it },
            placeholder = { Text("Add factor (e.g. Rote memorization)...", fontSize = 12.sp) },
            modifier = Modifier.weight(1f),
            singleLine = true
          )
          Button(
            onClick = {
              if (newAvoidText.isNotBlank()) {
                onProfileChange(profile.copy(avoidList = profile.avoidList + newAvoidText.trim()))
                newAvoidText = ""
              }
            },
            colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
            shape = RoundedCornerShape(8.dp)
          ) {
            Text("Add", color = PaperWhite)
          }
        }
      }
    }

    // Privacy Section
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SageSurface.copy(alpha = 0.5f)),
        border = BorderStroke(1.dp, BorderNeutral),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(Icons.Default.Lock, contentDescription = null, tint = InkForestPrimary, modifier = Modifier.size(18.dp))
            Text(
              text = "Privacy & Local Device Storage",
              style = MaterialTheme.typography.titleSmall.copy(
                fontFamily = EditorialSerifFont,
                fontWeight = FontWeight.Bold,
                color = InkForestDeep
              )
            )
          }

          Text(
            text = "Pathfinder India operates without user accounts, passwords, or cloud data tracking. All notes, bookmarks, feedback, and profile preferences remain private on this device.",
            style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted, lineHeight = 18.sp)
          )

          Spacer(modifier = Modifier.height(8.dp))

          OutlinedButton(
            onClick = { showResetConfirmation = true },
            border = BorderStroke(1.dp, TerracottaAccent),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.testTag("reset_demo_data_button")
          ) {
            Icon(Icons.Default.DeleteSweep, contentDescription = null, tint = TerracottaAccent, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("Reset all demo data", color = TerracottaAccent, style = MaterialTheme.typography.labelSmall)
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(30.dp))
    }
  }

  // Confirmation Dialog
  if (showResetConfirmation) {
    AlertDialog(
      onDismissRequest = { showResetConfirmation = false },
      title = {
        Text("Reset all demo data?", fontFamily = EditorialSerifFont, fontWeight = FontWeight.Bold)
      },
      text = {
        Text("This will restore default sample action steps, clear your saved bookmarks, chat messages, and custom worksheet notes.")
      },
      confirmButton = {
        Button(
          onClick = {
            onResetData()
            showResetConfirmation = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
          modifier = Modifier.testTag("confirm_reset_data_button")
        ) {
          Text("Reset everything", color = PaperWhite)
        }
      },
      dismissButton = {
        TextButton(onClick = { showResetConfirmation = false }) {
          Text("Cancel", color = InkForestDeep)
        }
      }
    )
  }
}

@Composable
private fun CollapsibleSection(
  title: String,
  isExpanded: Boolean,
  onToggle: () -> Unit,
  content: @Composable () -> Unit
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = PaperWhite),
    border = BorderStroke(1.dp, BorderNeutral),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onToggle() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Bold,
            color = InkForestDeep
          )
        )
        Icon(
          imageVector = Icons.Default.ExpandMore,
          contentDescription = if (isExpanded) "Collapse" else "Expand",
          tint = InkForestMuted,
          modifier = Modifier.rotate(if (isExpanded) 180f else 0f)
        )
      }

      AnimatedVisibility(visible = isExpanded) {
        Column(modifier = Modifier.padding(top = 14.dp)) {
          content()
        }
      }
    }
  }
}
