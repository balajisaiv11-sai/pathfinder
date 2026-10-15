package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Career
import com.example.data.Course
import com.example.data.SampleData
import com.example.ui.ComparisonType
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
fun ComparisonScreen(
  type: ComparisonType,
  comparedCareerIds: List<String>,
  comparedCourseIds: List<String>,
  onRemoveCareer: (String) -> Unit,
  onRemoveCourse: (String) -> Unit,
  onClose: () -> Unit,
  modifier: Modifier = Modifier
) {
  val comparedCareers = SampleData.sampleCareers.filter { comparedCareerIds.contains(it.id) }
  val comparedCourses = SampleData.sampleCourses.filter { comparedCourseIds.contains(it.id) }

  Surface(
    modifier = modifier.fillMaxSize(),
    color = IvoryBackground
  ) {
    Column(modifier = Modifier.fillMaxSize()) {
      // Top bar
      Surface(
        color = PaperWhite,
        border = BorderStroke(1.dp, BorderNeutral),
        modifier = Modifier.fillMaxWidth()
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
            IconButton(onClick = onClose, modifier = Modifier.size(36.dp)) {
              Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to browsing", tint = InkForestDeep)
            }
            Column {
              Text(
                text = if (type == ComparisonType.CAREER) "Career Comparison" else "Course Comparison",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontFamily = EditorialSerifFont,
                  fontWeight = FontWeight.Bold,
                  color = InkForestDeep
                )
              )
              Text(
                text = "Comparing ${if (type == ComparisonType.CAREER) comparedCareers.size else comparedCourses.size} of 3 items",
                style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted, fontSize = 11.sp)
              )
            }
          }

          TextButton(onClick = onClose) {
            Text("Done", color = TerracottaAccent, fontWeight = FontWeight.Bold)
          }
        }
      }

      // Guidance Note
      Surface(
        shape = RoundedCornerShape(10.dp),
        color = GoldMutedLight,
        border = BorderStroke(1.dp, GoldMuted.copy(alpha = 0.25f)),
        modifier = Modifier
          .padding(horizontal = 16.dp, vertical = 12.dp)
          .fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Icon(Icons.Default.Info, contentDescription = null, tint = GoldMuted, modifier = Modifier.size(16.dp))
          Text(
            text = "Use this comparison to decide what to research next. We do not rank options or declare a single 'winner'.",
            style = MaterialTheme.typography.bodySmall.copy(
              fontFamily = EditorialSansFont,
              color = InkForestDeep,
              fontSize = 12.sp
            )
          )
        }
      }

      // Comparison Panels
      if (type == ComparisonType.CAREER) {
        if (comparedCareers.isEmpty()) {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No careers currently selected for comparison.", color = InkForestMuted)
          }
        } else {
          LazyColumn(
            modifier = Modifier
              .fillMaxSize()
              .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
          ) {
            items(comparedCareers, key = { it.id }) { career ->
              CareerComparisonCard(career = career, onRemove = { onRemoveCareer(career.id) })
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
          }
        }
      } else {
        if (comparedCourses.isEmpty()) {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No courses currently selected for comparison.", color = InkForestMuted)
          }
        } else {
          LazyColumn(
            modifier = Modifier
              .fillMaxSize()
              .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
          ) {
            items(comparedCourses, key = { it.id }) { course ->
              CourseComparisonCard(course = course, onRemove = { onRemoveCourse(course.id) })
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
          }
        }
      }
    }
  }
}

@Composable
private fun CareerComparisonCard(
  career: Career,
  onRemove: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = PaperWhite),
    border = BorderStroke(1.dp, BorderNeutral),
    modifier = Modifier.fillMaxWidth().testTag("compare_item_${career.id}")
  ) {
    Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        EditorialBadge(text = career.field)
        IconButton(onClick = onRemove, modifier = Modifier.size(32.dp)) {
          Icon(Icons.Default.Close, contentDescription = "Remove from comparison", tint = InkForestMuted)
        }
      }

      Text(
        text = career.title,
        style = MaterialTheme.typography.titleLarge.copy(
          fontFamily = EditorialSerifFont,
          fontWeight = FontWeight.Bold,
          color = InkForestDeep
        )
      )

      Text(
        text = career.overview,
        style = MaterialTheme.typography.bodyMedium.copy(color = InkForestMuted, lineHeight = 20.sp)
      )

      HorizontalDivider(color = BorderNeutral.copy(alpha = 0.5f))

      // Activities
      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Typical work activities:", style = MaterialTheme.typography.labelSmall.copy(color = TerracottaAccent, fontWeight = FontWeight.Bold))
        career.workInvolves.take(2).forEach { w ->
          Text("• $w", style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
        }
      }

      // Skills used
      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Skills used or built:", style = MaterialTheme.typography.labelSmall.copy(color = InkForestPrimary, fontWeight = FontWeight.Bold))
        Text(career.relevantSkills.joinToString(", "), style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
      }

      // Possible routes
      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Possible study routes:", style = MaterialTheme.typography.labelSmall.copy(color = InkForestPrimary, fontWeight = FontWeight.Bold))
        career.possibleRoutes.forEach { r ->
          Text("→ $r", style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
        }
      }

      // Questions to verify
      Surface(
        shape = RoundedCornerShape(8.dp),
        color = SageSurface.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text("Questions that need verification:", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = InkForestDeep))
          career.thingsToInvestigate.take(2).forEach { inv ->
            Text("? $inv", style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep, fontSize = 11.5.sp))
          }
        }
      }
    }
  }
}

@Composable
private fun CourseComparisonCard(
  course: Course,
  onRemove: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = PaperWhite),
    border = BorderStroke(1.dp, BorderNeutral),
    modifier = Modifier.fillMaxWidth().testTag("compare_item_${course.id}")
  ) {
    Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          EditorialBadge(text = course.area)
          EditorialBadge(text = course.courseType, backgroundColor = IvoryBackground, textColor = TerracottaAccent)
        }
        IconButton(onClick = onRemove, modifier = Modifier.size(32.dp)) {
          Icon(Icons.Default.Close, contentDescription = "Remove from comparison", tint = InkForestMuted)
        }
      }

      Text(
        text = course.title,
        style = MaterialTheme.typography.titleLarge.copy(
          fontFamily = EditorialSerifFont,
          fontWeight = FontWeight.Bold,
          color = InkForestDeep
        )
      )

      Text(
        text = "Duration: ${course.approxDuration}",
        style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted, fontWeight = FontWeight.Medium)
      )

      HorizontalDivider(color = BorderNeutral.copy(alpha = 0.5f))

      // What students study
      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("What students study:", style = MaterialTheme.typography.labelSmall.copy(color = TerracottaAccent, fontWeight = FontWeight.Bold))
        course.whatStudentsStudy.take(2).forEach { s ->
          Text("• $s", style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
        }
      }

      // Practical training
      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Practical training & skills:", style = MaterialTheme.typography.labelSmall.copy(color = InkForestPrimary, fontWeight = FontWeight.Bold))
        Text(course.skillsPractised.joinToString(", "), style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep))
      }

      // Questions to ask institution
      Surface(
        shape = RoundedCornerShape(8.dp),
        color = SageSurface.copy(alpha = 0.6f),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text("Questions to ask the institution:", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = InkForestDeep))
          course.questionsToAsk.take(2).forEach { q ->
            Text("? $q", style = MaterialTheme.typography.bodySmall.copy(color = InkForestDeep, fontSize = 11.5.sp))
          }
        }
      }
    }
  }
}
