package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Career
import com.example.data.CareerFeedback
import com.example.data.Course
import com.example.data.PathfinderRepository
import com.example.data.SampleData
import com.example.data.StudentPlan
import com.example.data.StudentProfile
import com.example.data.StudyStage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class ScreenDestination(val title: String, val shortLabel: String) {
  HOME("Home", "Home"),
  DISCOVER("Discover", "Discover"),
  STUDY_PATHS("Study Paths", "Paths"),
  CAREERS("Careers", "Careers"),
  COURSES("Courses", "Courses"),
  MY_PLAN("My Plan", "Plan"),
  PROFILE("Profile", "Profile")
}

enum class ComparisonType {
  CAREER,
  COURSE
}

class PathfinderViewModel(application: Application) : AndroidViewModel(application) {

  private val repository = PathfinderRepository.getInstance(application)

  val profile: StateFlow<StudentProfile> = repository.profile
  val plan: StateFlow<StudentPlan> = repository.plan
  val savedCareers: StateFlow<Set<String>> = repository.savedCareers
  val savedCourses: StateFlow<Set<String>> = repository.savedCourses
  val comparedCareerIds: StateFlow<List<String>> = repository.comparedCareerIds
  val comparedCourseIds: StateFlow<List<String>> = repository.comparedCourseIds
  val chatMessages = repository.chatMessages

  // Navigation
  private val _currentScreen = MutableStateFlow(ScreenDestination.HOME)
  val currentScreen: StateFlow<ScreenDestination> = _currentScreen.asStateFlow()

  // Career Detail and Course Detail Modals
  private val _selectedCareer = MutableStateFlow<Career?>(null)
  val selectedCareer: StateFlow<Career?> = _selectedCareer.asStateFlow()

  private val _selectedCourse = MutableStateFlow<Course?>(null)
  val selectedCourse: StateFlow<Course?> = _selectedCourse.asStateFlow()

  // Comparison Dialog
  private val _activeComparison = MutableStateFlow<ComparisonType?>(null)
  val activeComparison: StateFlow<ComparisonType?> = _activeComparison.asStateFlow()

  // Career filters
  val careerSearchQuery = MutableStateFlow("")
  val careerSelectedField = MutableStateFlow("All")
  val careerSelectedInterest = MutableStateFlow("All")

  // Course filters
  val courseSearchQuery = MutableStateFlow("")
  val courseSelectedArea = MutableStateFlow("All")
  val courseSelectedType = MutableStateFlow("All")

  // Filtered Careers
  val filteredCareers: StateFlow<List<Career>> = combine(
    profile,
    careerSearchQuery,
    careerSelectedField,
    careerSelectedInterest
  ) { prof, query, field, interest ->
    SampleData.sampleCareers.filter { career ->
      // Stage matching
      val stageMatch = when (prof.studyStage) {
        StudyStage.BOTH -> true
        StudyStage.AFTER_10TH -> career.stageRelevance == StudyStage.AFTER_10TH || career.stageRelevance == StudyStage.BOTH
        StudyStage.AFTER_12TH -> career.stageRelevance == StudyStage.AFTER_12TH || career.stageRelevance == StudyStage.BOTH
      }

      // Query matching
      val queryMatch = query.isBlank() ||
        career.title.contains(query, ignoreCase = true) ||
        career.field.contains(query, ignoreCase = true) ||
        career.relevantSkills.any { it.contains(query, ignoreCase = true) }

      // Field matching
      val fieldMatch = field == "All" || career.field.contains(field, ignoreCase = true)

      // Interest matching
      val interestMatch = interest == "All" || career.tags.any { it.contains(interest, ignoreCase = true) }

      stageMatch && queryMatch && fieldMatch && interestMatch
    }.sortedBy { career ->
      // Put INTERESTED first, NOT_FOR_ME last
      when (prof.feedback[career.id]) {
        CareerFeedback.INTERESTED -> 0
        CareerFeedback.UNSURE -> 1
        null -> 2
        CareerFeedback.NOT_FOR_ME -> 3
      }
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SampleData.sampleCareers)

  // Filtered Courses
  val filteredCourses: StateFlow<List<Course>> = combine(
    profile,
    courseSearchQuery,
    courseSelectedArea,
    courseSelectedType
  ) { prof, query, area, type ->
    SampleData.sampleCourses.filter { course ->
      val stageMatch = when (prof.studyStage) {
        StudyStage.BOTH -> true
        StudyStage.AFTER_10TH -> course.stage == StudyStage.AFTER_10TH || course.stage == StudyStage.BOTH
        StudyStage.AFTER_12TH -> course.stage == StudyStage.AFTER_12TH || course.stage == StudyStage.BOTH
      }

      val queryMatch = query.isBlank() ||
        course.title.contains(query, ignoreCase = true) ||
        course.area.contains(query, ignoreCase = true) ||
        course.subjectsOrSkills.any { it.contains(query, ignoreCase = true) }

      val areaMatch = area == "All" || course.area.contains(area, ignoreCase = true)
      val typeMatch = type == "All" || course.courseType.contains(type, ignoreCase = true)

      stageMatch && queryMatch && areaMatch && typeMatch
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SampleData.sampleCourses)

  fun navigateTo(destination: ScreenDestination) {
    _currentScreen.value = destination
  }

  fun setStudyStage(stage: StudyStage) {
    repository.setStudyStage(stage)
  }

  fun selectCareer(career: Career?) {
    _selectedCareer.value = career
  }

  fun selectCourse(course: Course?) {
    _selectedCourse.value = course
  }

  fun openComparison(type: ComparisonType) {
    _activeComparison.value = type
  }

  fun closeComparison() {
    _activeComparison.value = null
  }

  fun toggleSaveCareer(careerId: String) {
    repository.toggleSaveCareer(careerId)
  }

  fun toggleSaveCourse(courseId: String) {
    repository.toggleSaveCourse(courseId)
  }

  fun toggleCompareCareer(careerId: String): Boolean {
    return repository.toggleCompareCareer(careerId)
  }

  fun clearCareerComparisons() {
    repository.clearCareerComparisons()
  }

  fun toggleCompareCourse(courseId: String): Boolean {
    return repository.toggleCompareCourse(courseId)
  }

  fun clearCourseComparisons() {
    repository.clearCourseComparisons()
  }

  fun setCareerFeedback(careerId: String, feedback: CareerFeedback) {
    repository.setCareerFeedback(careerId, feedback)
  }

  fun sendChatMessage(text: String) {
    repository.sendUserChatMessage(text)
  }

  fun toggleStepCompletion(stepId: String) {
    repository.toggleStepCompletion(stepId)
  }

  fun addPlanStep(title: String, description: String, targetDate: String? = null) {
    repository.addPlanStep(title, description, targetDate)
  }

  fun removePlanStep(stepId: String) {
    repository.removePlanStep(stepId)
  }

  fun updatePlanWorksheet(
    firstChoice: String,
    firstReason: String,
    backupChoice: String,
    backupReason: String,
    notes: String,
    questions: List<String>
  ) {
    repository.updatePlanWorksheet(firstChoice, firstReason, backupChoice, backupReason, notes, questions)
  }

  fun addInterest(interest: String) {
    repository.addInterest(interest)
  }

  fun removeInterest(interest: String) {
    repository.removeInterest(interest)
  }

  fun updateProfile(newProfile: StudentProfile) {
    repository.updateProfile(newProfile)
  }

  fun resetAllDemoData() {
    repository.resetAllDemoData()
    _selectedCareer.value = null
    _selectedCourse.value = null
    _activeComparison.value = null
    careerSearchQuery.value = ""
    careerSelectedField.value = "All"
    careerSelectedInterest.value = "All"
    courseSearchQuery.value = ""
    courseSelectedArea.value = "All"
    courseSelectedType.value = "All"
  }
}
