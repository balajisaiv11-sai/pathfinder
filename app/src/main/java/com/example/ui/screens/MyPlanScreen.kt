package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import com.example.data.PlanStep
import com.example.data.SampleData
import com.example.data.StudentPlan
import com.example.ui.ScreenDestination
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
fun MyPlanScreen(
  plan: StudentPlan,
  savedCareerIds: Set<String>,
  savedCourseIds: Set<String>,
  onToggleStep: (String) -> Unit,
  onAddStep: (String, String, String?) -> Unit,
  onRemoveStep: (String) -> Unit,
  onUpdateWorksheet: (String, String, String, String, String, List<String>) -> Unit,
  onNavigate: (ScreenDestination) -> Unit,
  modifier: Modifier = Modifier
) {
  var showAddStepDialog by remember { mutableStateOf(false) }
  var showEditWorksheetDialog by remember { mutableStateOf(false) }

  val completedStepsCount = plan.steps.count { it.isCompleted }
  val totalStepsCount = plan.steps.size
  val progressFraction = if (totalStepsCount > 0) completedStepsCount.toFloat() / totalStepsCount else 0f

  val savedCareers = SampleData.sampleCareers.filter { savedCareerIds.contains(it.id) }
  val savedCourses = SampleData.sampleCourses.filter { savedCourseIds.contains(it.id) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(IvoryBackground)
      .padding(horizontal = 20.dp),
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    item {
      Spacer(modifier = Modifier.height(16.dp))
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        EditorialBadge(
          text = "Personal Notebook",
          backgroundColor = SageSurface,
          textColor = InkForestPrimary,
          icon = Icons.Default.MenuBook
        )
        Text(
          text = "Your next few steps",
          style = MaterialTheme.typography.displaySmall.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Normal,
            color = InkForestDeep
          )
        )
        Text(
          text = "A quiet, pressure-free notebook for tracking practical inquiries, notes from college visits, and questions for mentors.",
          style = MaterialTheme.typography.bodyMedium.copy(color = InkForestMuted)
        )
      }
    }

    // Progress Card (Calm, non-gamified)
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PaperWhite),
        border = BorderStroke(1.dp, BorderNeutral),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Exploration checklist",
              style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = EditorialSerifFont,
                fontWeight = FontWeight.Bold,
                color = InkForestDeep
              )
            )
            Text(
              text = "$completedStepsCount of $totalStepsCount steps explored",
              style = MaterialTheme.typography.bodySmall.copy(
                color = InkForestMuted,
                fontWeight = FontWeight.Medium
              )
            )
          }

          LinearProgressIndicator(
            progress = { progressFraction },
            modifier = Modifier
              .fillMaxWidth()
              .height(6.dp)
              .clip(RoundedCornerShape(3.dp)),
            color = TerracottaAccent,
            trackColor = SageSurface
          )
        }
      }
    }

    // Editable Checklist Section
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Action steps",
          style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Bold,
            color = InkForestDeep
          )
        )

        Button(
          onClick = { showAddStepDialog = true },
          colors = ButtonDefaults.buttonColors(containerColor = SageSurfaceVariant),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.testTag("add_custom_step_button")
        ) {
          Icon(Icons.Default.Add, contentDescription = null, tint = InkForestDeep, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("Add step", color = InkForestDeep, style = MaterialTheme.typography.labelSmall)
        }
      }
    }

    items(plan.steps, key = { it.id }) { step ->
      StepCard(
        step = step,
        onToggle = { onToggleStep(step.id) },
        onRemove = { onRemoveStep(step.id) }
      )
    }

    // First-Choice and Backup-Choice Worksheet
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PaperWhite),
        border = BorderStroke(1.dp, BorderNeutral),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Directions Worksheet",
              style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = EditorialSerifFont,
                fontWeight = FontWeight.Bold,
                color = InkForestDeep
              )
            )

            TextButton(onClick = { showEditWorksheetDialog = true }, modifier = Modifier.testTag("edit_worksheet_button")) {
              Icon(Icons.Default.EditNote, contentDescription = null, tint = TerracottaAccent, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(4.dp))
              Text("Edit worksheet", color = TerracottaAccent, fontWeight = FontWeight.SemiBold)
            }
          }

          // Primary Direction
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = IvoryBackground,
            border = BorderStroke(1.dp, BorderNeutral),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "Primary Direction to Investigate",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = TerracottaAccent)
              )
              Text(
                text = plan.firstChoice.ifBlank { "Not specified yet. E.g. Science PCM with Computer Science" },
                style = MaterialTheme.typography.bodyMedium.copy(color = InkForestDeep, fontWeight = FontWeight.SemiBold)
              )
              if (plan.firstChoiceReason.isNotBlank()) {
                Text(
                  text = "Why: ${plan.firstChoiceReason}",
                  style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted)
                )
              }
            }
          }

          // Backup Direction
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = SageSurface.copy(alpha = 0.5f),
            border = BorderStroke(1.dp, BorderNeutral),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
              Text(
                text = "Backup Option to Keep Ready",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = InkForestPrimary)
              )
              Text(
                text = plan.backupChoice.ifBlank { "Not specified yet. E.g. Polytechnic Diploma or Commerce" },
                style = MaterialTheme.typography.bodyMedium.copy(color = InkForestDeep, fontWeight = FontWeight.SemiBold)
              )
              if (plan.backupChoiceReason.isNotBlank()) {
                Text(
                  text = "Why: ${plan.backupChoiceReason}",
                  style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted)
                )
              }
            }
          }

          // Questions for mentors
          if (plan.questionsForMentors.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(
                text = "Questions to ask a teacher, elder, or adviser:",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = InkForestDeep)
              )
              plan.questionsForMentors.forEach { q ->
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                  Icon(Icons.Default.HelpOutline, contentDescription = null, tint = GoldMuted, modifier = Modifier.size(15.dp))
                  Text(text = q, style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
                }
              }
            }
          }

          // Personal Notes
          if (plan.generalNotes.isNotBlank()) {
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = GoldMutedLight,
              border = BorderStroke(1.dp, GoldMuted.copy(alpha = 0.25f)),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                  text = "Personal Notes:",
                  style = MaterialTheme.typography.labelSmall.copy(color = GoldMuted, fontWeight = FontWeight.Bold)
                )
                Text(
                  text = plan.generalNotes,
                  style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep)
                )
              }
            }
          }
        }
      }
    }

    // Saved Items Section
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
          text = "Saved for later (${savedCareers.size} careers, ${savedCourses.size} courses)",
          style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Bold,
            color = InkForestDeep
          )
        )

        if (savedCareers.isEmpty() && savedCourses.isEmpty()) {
          Text(
            text = "You haven't bookmarked any careers or courses yet. Use the bookmark icon on any card to save options here.",
            style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted)
          )
        } else {
          savedCareers.forEach { car ->
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = PaperWhite,
              border = BorderStroke(1.dp, BorderNeutral),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column {
                  EditorialBadge(text = car.field, backgroundColor = SageSurface, textColor = InkForestDeep)
                  Spacer(modifier = Modifier.height(4.dp))
                  Text(text = car.title, fontWeight = FontWeight.Bold, color = InkForestDeep)
                }
                Icon(Icons.Default.Bookmark, contentDescription = "Saved", tint = TerracottaAccent)
              }
            }
          }

          savedCourses.forEach { crs ->
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = PaperWhite,
              border = BorderStroke(1.dp, BorderNeutral),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column {
                  EditorialBadge(text = crs.courseType, backgroundColor = IvoryBackground, textColor = TerracottaAccent)
                  Spacer(modifier = Modifier.height(4.dp))
                  Text(text = crs.title, fontWeight = FontWeight.Bold, color = InkForestDeep)
                }
                Icon(Icons.Default.Bookmark, contentDescription = "Saved", tint = TerracottaAccent)
              }
            }
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(30.dp))
    }
  }

  // Dialog: Add Step
  if (showAddStepDialog) {
    AddStepDialog(
      onDismiss = { showAddStepDialog = false },
      onAdd = { title, desc, date ->
        onAddStep(title, desc, date)
        showAddStepDialog = false
      }
    )
  }

  // Dialog: Edit Worksheet
  if (showEditWorksheetDialog) {
    EditWorksheetDialog(
      currentPlan = plan,
      onDismiss = { showEditWorksheetDialog = false },
      onSave = { first, firstR, backup, backupR, notes, questions ->
        onUpdateWorksheet(first, firstR, backup, backupR, notes, questions)
        showEditWorksheetDialog = false
      }
    )
  }
}

@Composable
private fun StepCard(
  step: PlanStep,
  onToggle: () -> Unit,
  onRemove: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = if (step.isCompleted) SageSurface.copy(alpha = 0.5f) else PaperWhite),
    border = BorderStroke(1.dp, BorderNeutral),
    modifier = Modifier
      .fillMaxWidth()
      .testTag("plan_step_${step.id}")
  ) {
    Row(
      modifier = Modifier.padding(14.dp),
      verticalAlignment = Alignment.Top,
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Checkbox(
        checked = step.isCompleted,
        onCheckedChange = { onToggle() },
        colors = CheckboxDefaults.colors(
          checkedColor = TerracottaAccent,
          uncheckedColor = InkForestMuted
        ),
        modifier = Modifier.testTag("step_checkbox_${step.id}")
      )

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = step.title,
          style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.SemiBold,
            color = if (step.isCompleted) InkForestMuted else InkForestDeep
          )
        )
        if (step.description.isNotBlank()) {
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = step.description,
            style = MaterialTheme.typography.bodySmall.copy(
              color = InkForestMuted,
              lineHeight = 16.sp
            )
          )
        }
        if (step.targetDate != null) {
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Target: ${step.targetDate}",
            style = MaterialTheme.typography.labelSmall.copy(color = TerracottaAccent)
          )
        }
        if (step.personalNote != null) {
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Note: ${step.personalNote}",
            style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
          )
        }
      }

      if (!step.isPredefined) {
        IconButton(onClick = onRemove, modifier = Modifier.size(32.dp)) {
          Icon(Icons.Default.DeleteOutline, contentDescription = "Delete step", tint = InkForestMuted)
        }
      }
    }
  }
}

@Composable
private fun AddStepDialog(
  onDismiss: () -> Unit,
  onAdd: (String, String, String?) -> Unit
) {
  var title by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }
  var targetDate by remember { mutableStateOf("") }

  Dialog(onDismissRequest = onDismiss) {
    Surface(
      shape = RoundedCornerShape(16.dp),
      color = PaperWhite,
      border = BorderStroke(1.dp, BorderNeutral),
      modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
          text = "Add Next Action Step",
          style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Bold,
            color = InkForestDeep
          )
        )

        OutlinedTextField(
          value = title,
          onValueChange = { title = it },
          label = { Text("What step do you want to take?") },
          placeholder = { Text("e.g. Visit local polytechnic college") },
          modifier = Modifier.fillMaxWidth().testTag("add_step_title_input")
        )

        OutlinedTextField(
          value = description,
          onValueChange = { description = it },
          label = { Text("Details or specific questions (optional)") },
          modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
          value = targetDate,
          onValueChange = { targetDate = it },
          label = { Text("Target timeframe (optional, e.g. Next weekend)") },
          modifier = Modifier.fillMaxWidth()
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.End
        ) {
          TextButton(onClick = onDismiss) {
            Text("Cancel", color = InkForestMuted)
          }
          Button(
            onClick = {
              if (title.isNotBlank()) {
                onAdd(title.trim(), description.trim(), targetDate.ifBlank { null })
              }
            },
            enabled = title.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.testTag("confirm_add_step_button")
          ) {
            Text("Save step", color = PaperWhite)
          }
        }
      }
    }
  }
}

@Composable
private fun EditWorksheetDialog(
  currentPlan: StudentPlan,
  onDismiss: () -> Unit,
  onSave: (String, String, String, String, String, List<String>) -> Unit
) {
  var firstChoice by remember { mutableStateOf(currentPlan.firstChoice) }
  var firstReason by remember { mutableStateOf(currentPlan.firstChoiceReason) }
  var backupChoice by remember { mutableStateOf(currentPlan.backupChoice) }
  var backupReason by remember { mutableStateOf(currentPlan.backupChoiceReason) }
  var notes by remember { mutableStateOf(currentPlan.generalNotes) }
  var newQuestion by remember { mutableStateOf("") }
  var questionsList by remember { mutableStateOf(currentPlan.questionsForMentors) }

  Dialog(onDismissRequest = onDismiss) {
    Surface(
      shape = RoundedCornerShape(16.dp),
      color = PaperWhite,
      border = BorderStroke(1.dp, BorderNeutral),
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
    ) {
      LazyColumn(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
          Text(
            text = "Edit Directions Worksheet",
            style = MaterialTheme.typography.titleMedium.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Bold,
              color = InkForestDeep
            )
          )
        }

        item {
          OutlinedTextField(
            value = firstChoice,
            onValueChange = { firstChoice = it },
            label = { Text("Primary direction to investigate") },
            modifier = Modifier.fillMaxWidth()
          )
        }

        item {
          OutlinedTextField(
            value = firstReason,
            onValueChange = { firstReason = it },
            label = { Text("Why does this interest you?") },
            modifier = Modifier.fillMaxWidth()
          )
        }

        item {
          OutlinedTextField(
            value = backupChoice,
            onValueChange = { backupChoice = it },
            label = { Text("Backup option") },
            modifier = Modifier.fillMaxWidth()
          )
        }

        item {
          OutlinedTextField(
            value = backupReason,
            onValueChange = { backupReason = it },
            label = { Text("Why is this a comforting backup?") },
            modifier = Modifier.fillMaxWidth()
          )
        }

        item {
          OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Personal notes & thoughts") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4
          )
        }

        item {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
          ) {
            TextButton(onClick = onDismiss) {
              Text("Cancel", color = InkForestMuted)
            }
            Button(
              onClick = {
                onSave(firstChoice, firstReason, backupChoice, backupReason, notes, questionsList)
              },
              colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
              shape = RoundedCornerShape(8.dp)
            ) {
              Text("Save changes", color = PaperWhite)
            }
          }
        }
      }
    }
  }
}
