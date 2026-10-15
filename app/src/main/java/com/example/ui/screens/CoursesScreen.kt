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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
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
import com.example.data.Course
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
fun CoursesScreen(
  courses: List<Course>,
  profile: StudentProfile,
  savedCourses: Set<String>,
  comparedCourseIds: List<String>,
  searchQuery: String,
  selectedArea: String,
  selectedType: String,
  onSearchChange: (String) -> Unit,
  onAreaChange: (String) -> Unit,
  onTypeChange: (String) -> Unit,
  onStageChange: (StudyStage) -> Unit,
  onCourseClick: (Course) -> Unit,
  onToggleSaveCourse: (String) -> Unit,
  onToggleCompareCourse: (String) -> Unit,
  onClearComparisons: () -> Unit,
  onOpenCompareView: () -> Unit,
  selectedCourseDetail: Course?,
  onDismissDetail: () -> Unit,
  modifier: Modifier = Modifier
) {
  val areaFilterOptions = listOf("All", "Engineering", "Healthcare", "Agriculture", "Commerce", "Law", "Design", "Hospitality", "Secondary", "Trade")
  val typeFilterOptions = listOf("All", "Degree", "Diploma", "Certificate", "Higher Secondary", "Integrated")

  var showAreaMenu by remember { mutableStateOf(false) }
  var showTypeMenu by remember { mutableStateOf(false) }

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
            text = "Course & Program Explorer",
            backgroundColor = SageSurface,
            textColor = InkForestPrimary
          )
          Text(
            text = "Compare practical courses",
            style = MaterialTheme.typography.displaySmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Normal,
              color = InkForestDeep
            )
          )
          Text(
            text = "Investigate degrees, polytechnic diplomas, and trade craft certificates. All duration notes reflect typical estimates and vary by board or university.",
            style = MaterialTheme.typography.bodyMedium.copy(color = InkForestMuted)
          )
        }
      }

      // Search & Filters Row
      item {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            placeholder = { Text("Search courses, subjects, or branches...", fontSize = 13.sp) },
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
              .fillMaxWidth()
              .testTag("courses_search_input"),
            singleLine = true
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            // Area Menu
            Box(modifier = Modifier.weight(1f)) {
              OutlinedButton(
                onClick = { showAreaMenu = true },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, if (selectedArea != "All") TerracottaAccent else BorderNeutral),
                colors = ButtonDefaults.outlinedButtonColors(
                  containerColor = if (selectedArea != "All") TerracottaContainer.copy(alpha = 0.5f) else PaperWhite
                ),
                modifier = Modifier.fillMaxWidth().testTag("courses_area_filter")
              ) {
                Text(
                  text = if (selectedArea == "All") "Course Area" else selectedArea,
                  color = InkForestDeep,
                  style = MaterialTheme.typography.labelSmall
                )
              }
              DropdownMenu(expanded = showAreaMenu, onDismissRequest = { showAreaMenu = false }) {
                areaFilterOptions.forEach { area ->
                  DropdownMenuItem(text = { Text(area) }, onClick = {
                    onAreaChange(area)
                    showAreaMenu = false
                  })
                }
              }
            }

            // Type Menu
            Box(modifier = Modifier.weight(1f)) {
              OutlinedButton(
                onClick = { showTypeMenu = true },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, if (selectedType != "All") TerracottaAccent else BorderNeutral),
                colors = ButtonDefaults.outlinedButtonColors(
                  containerColor = if (selectedType != "All") TerracottaContainer.copy(alpha = 0.5f) else PaperWhite
                ),
                modifier = Modifier.fillMaxWidth().testTag("courses_type_filter")
              ) {
                Text(
                  text = if (selectedType == "All") "Program Type" else selectedType,
                  color = InkForestDeep,
                  style = MaterialTheme.typography.labelSmall
                )
              }
              DropdownMenu(expanded = showTypeMenu, onDismissRequest = { showTypeMenu = false }) {
                typeFilterOptions.forEach { t ->
                  DropdownMenuItem(text = { Text(t) }, onClick = {
                    onTypeChange(t)
                    showTypeMenu = false
                  })
                }
              }
            }
          }
        }
      }

      // Result count note
      item {
        Text(
          text = "Showing ${courses.size} sample course options (${profile.studyStage.displayName})",
          style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted)
        )
      }

      // Course Cards
      items(courses, key = { it.id }) { course ->
        val isSaved = savedCourses.contains(course.id)
        val isCompared = comparedCourseIds.contains(course.id)

        CourseCard(
          course = course,
          isSaved = isSaved,
          isCompared = isCompared,
          onCardClick = { onCourseClick(course) },
          onToggleSave = { onToggleSaveCourse(course.id) },
          onToggleCompare = { onToggleCompareCourse(course.id) }
        )
      }

      item {
        Spacer(modifier = Modifier.height(80.dp))
      }
    }

    // Comparison Floating Bar
    ComparisonFloatingTray(
      selectedCount = comparedCourseIds.size,
      itemTypeLabel = "courses",
      onCompareClicked = onOpenCompareView,
      onClearClicked = onClearComparisons,
      modifier = Modifier.align(Alignment.BottomCenter)
    )

    // Course Detail Modal
    if (selectedCourseDetail != null) {
      CourseDetailDialog(
        course = selectedCourseDetail,
        isSaved = savedCourses.contains(selectedCourseDetail.id),
        isCompared = comparedCourseIds.contains(selectedCourseDetail.id),
        onDismiss = onDismissDetail,
        onToggleSave = { onToggleSaveCourse(selectedCourseDetail.id) },
        onToggleCompare = { onToggleCompareCourse(selectedCourseDetail.id) }
      )
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CourseCard(
  course: Course,
  isSaved: Boolean,
  isCompared: Boolean,
  onCardClick: () -> Unit,
  onToggleSave: () -> Unit,
  onToggleCompare: () -> Unit
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
      .testTag("course_card_${course.id}")
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          EditorialBadge(text = course.area, backgroundColor = SageSurface, textColor = InkForestPrimary)
          EditorialBadge(text = course.courseType, backgroundColor = IvoryBackground, textColor = TerracottaAccent)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(onClick = onToggleCompare, modifier = Modifier.size(36.dp).testTag("compare_course_${course.id}")) {
            Icon(
              imageVector = Icons.Default.CompareArrows,
              contentDescription = if (isCompared) "Remove comparison" else "Compare course",
              tint = if (isCompared) TerracottaAccent else InkForestMuted
            )
          }

          IconButton(onClick = onToggleSave, modifier = Modifier.size(36.dp).testTag("save_course_${course.id}")) {
            Icon(
              imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
              contentDescription = if (isSaved) "Saved" else "Save course",
              tint = if (isSaved) TerracottaAccent else InkForestMuted
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = course.title,
        style = MaterialTheme.typography.titleLarge.copy(
          fontFamily = EditorialSerifFont,
          fontWeight = FontWeight.Bold,
          color = InkForestDeep
        )
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = "Duration: ${course.approxDuration}",
        style = MaterialTheme.typography.bodySmall.copy(
          color = InkForestMuted,
          fontWeight = FontWeight.Medium
        )
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = course.personalizationNote,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = InkForestDeep,
          lineHeight = 20.sp
        )
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Subjects covered
      Text(
        text = "Subjects & core skills:",
        style = MaterialTheme.typography.labelSmall.copy(color = InkForestMuted)
      )
      Spacer(modifier = Modifier.height(4.dp))
      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        course.subjectsOrSkills.take(3).forEach { subj ->
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = SageSurfaceVariant,
            modifier = Modifier.padding(bottom = 2.dp)
          ) {
            Text(
              text = subj,
              style = MaterialTheme.typography.labelSmall.copy(color = InkForestDeep, fontSize = 11.sp),
              modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(12.dp))
      HorizontalDivider(color = BorderNeutral.copy(alpha = 0.5f))
      Spacer(modifier = Modifier.height(8.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Relates to: ${course.relatedCareers.firstOrNull() ?: ""}",
          style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted, fontSize = 11.5.sp)
        )

        Text(
          text = "View course details ->",
          style = MaterialTheme.typography.labelSmall.copy(
            color = TerracottaAccent,
            fontWeight = FontWeight.Bold
          )
        )
      }
    }
  }
}

@Composable
private fun CourseDetailDialog(
  course: Course,
  isSaved: Boolean,
  isCompared: Boolean,
  onDismiss: () -> Unit,
  onToggleSave: () -> Unit,
  onToggleCompare: () -> Unit
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
          Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            EditorialBadge(text = course.area)
            EditorialBadge(text = course.courseType, backgroundColor = IvoryBackground, textColor = TerracottaAccent)
          }
          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = InkForestDeep)
          }
        }

        Text(
          text = course.title,
          style = MaterialTheme.typography.displaySmall.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Bold,
            color = InkForestDeep
          )
        )

        Text(
          text = "Estimated Duration: ${course.approxDuration}",
          style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted, fontWeight = FontWeight.SemiBold)
        )

        HorizontalDivider(color = BorderNeutral)

        // Section: What students study
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "What students study:",
            style = MaterialTheme.typography.titleSmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Bold,
              color = InkForestDeep
            )
          )
          course.whatStudentsStudy.forEach { item ->
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
              Text(text = item, style = MaterialTheme.typography.bodyMedium.copy(color = InkForestDeep, lineHeight = 20.sp))
            }
          }
        }

        // Section: Skills practised
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "Practical skills practised:",
            style = MaterialTheme.typography.titleSmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Bold,
              color = InkForestDeep
            )
          )
          course.skillsPractised.forEach { skill ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = SageSurface.copy(alpha = 0.5f),
              border = BorderStroke(1.dp, BorderNeutral),
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(
                text = skill,
                style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep),
                modifier = Modifier.padding(10.dp)
              )
            }
          }
        }

        // Section: Questions to ask the institution
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "Questions to ask the school or college:",
            style = MaterialTheme.typography.titleSmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Bold,
              color = InkForestDeep
            )
          )
          course.questionsToAsk.forEach { q ->
            Row(
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(Icons.Default.HelpOutline, contentDescription = null, tint = GoldMuted, modifier = Modifier.size(16.dp))
              Text(text = q, style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep, lineHeight = 18.sp))
            }
          }
        }

        // Verification disclaimer box
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = GoldMutedLight,
          border = BorderStroke(1.dp, GoldMuted.copy(alpha = 0.3f)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
              text = "What to verify before enrollment:",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = GoldMuted)
            )
            course.whatToVerify.forEach { v ->
              Text(
                text = v,
                style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep, fontSize = 11.5.sp)
              )
            }
          }
        }

        // Dialog buttons
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
            Text(if (isSaved) "Saved" else "Save course", color = PaperWhite)
          }
        }
      }
    }
  }
}
