package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class PathfinderRepository private constructor(context: Context) {

  private val prefs: SharedPreferences =
    context.applicationContext.getSharedPreferences("pathfinder_local_prefs", Context.MODE_PRIVATE)

  private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

  private val profileAdapter = moshi.adapter(StudentProfile::class.java)
  private val planAdapter = moshi.adapter(StudentPlan::class.java)
  private val stringSetType = Types.newParameterizedType(Set::class.java, String::class.java)
  private val stringSetAdapter = moshi.adapter<Set<String>>(stringSetType)
  private val chatListType = Types.newParameterizedType(List::class.java, ChatMessage::class.java)
  private val chatListAdapter = moshi.adapter<List<ChatMessage>>(chatListType)

  // Reactive state holders
  private val _profile = MutableStateFlow(loadProfile())
  val profile: StateFlow<StudentProfile> = _profile.asStateFlow()

  private val _plan = MutableStateFlow(loadPlan())
  val plan: StateFlow<StudentPlan> = _plan.asStateFlow()

  private val _savedCareers = MutableStateFlow(loadSavedCareers())
  val savedCareers: StateFlow<Set<String>> = _savedCareers.asStateFlow()

  private val _savedCourses = MutableStateFlow(loadSavedCourses())
  val savedCourses: StateFlow<Set<String>> = _savedCourses.asStateFlow()

  private val _comparedCareerIds = MutableStateFlow<List<String>>(emptyList())
  val comparedCareerIds: StateFlow<List<String>> = _comparedCareerIds.asStateFlow()

  private val _comparedCourseIds = MutableStateFlow<List<String>>(emptyList())
  val comparedCourseIds: StateFlow<List<String>> = _comparedCourseIds.asStateFlow()

  private val _chatMessages = MutableStateFlow(loadChatMessages())
  val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

  // --- Profile methods ---

  private fun loadProfile(): StudentProfile {
    val json = prefs.getString("student_profile", null)
    return if (json != null) {
      try {
        profileAdapter.fromJson(json) ?: StudentProfile()
      } catch (e: Exception) {
        StudentProfile()
      }
    } else {
      StudentProfile(
        studyStage = StudyStage.AFTER_10TH,
        subjects = listOf("Science", "Mathematics", "English"),
        interests = listOf("Problem solving", "Technology"),
        strengths = listOf("Logical reasoning", "Practical experiments"),
        preferences = listOf("Hands-on learning", "Balanced work-life")
      )
    }
  }

  fun updateProfile(newProfile: StudentProfile) {
    _profile.value = newProfile
    prefs.edit().putString("student_profile", profileAdapter.toJson(newProfile)).apply()
  }

  fun setStudyStage(stage: StudyStage) {
    val updated = _profile.value.copy(studyStage = stage)
    updateProfile(updated)
  }

  fun addInterest(interest: String) {
    val current = _profile.value.interests.toMutableList()
    if (!current.contains(interest)) {
      current.add(interest)
      updateProfile(_profile.value.copy(interests = current))
    }
  }

  fun removeInterest(interest: String) {
    val current = _profile.value.interests.toMutableList()
    current.remove(interest)
    updateProfile(_profile.value.copy(interests = current))
  }

  fun setCareerFeedback(careerId: String, feedback: CareerFeedback) {
    val currentFeedback = _profile.value.feedback.toMutableMap()
    currentFeedback[careerId] = feedback
    updateProfile(_profile.value.copy(feedback = currentFeedback))
  }

  // --- Saved items ---

  private fun loadSavedCareers(): Set<String> {
    val json = prefs.getString("saved_careers", null) ?: return setOf("car-data-analyst", "car-agri-horticulture")
    return try {
      stringSetAdapter.fromJson(json) ?: emptySet()
    } catch (e: Exception) {
      emptySet()
    }
  }

  fun toggleSaveCareer(careerId: String) {
    val current = _savedCareers.value.toMutableSet()
    if (current.contains(careerId)) {
      current.remove(careerId)
    } else {
      current.add(careerId)
    }
    _savedCareers.value = current
    prefs.edit().putString("saved_careers", stringSetAdapter.toJson(current)).apply()
  }

  private fun loadSavedCourses(): Set<String> {
    val json = prefs.getString("saved_courses", null) ?: return setOf("crs-inter-mpc", "crs-btech-cse")
    return try {
      stringSetAdapter.fromJson(json) ?: emptySet()
    } catch (e: Exception) {
      emptySet()
    }
  }

  fun toggleSaveCourse(courseId: String) {
    val current = _savedCourses.value.toMutableSet()
    if (current.contains(courseId)) {
      current.remove(courseId)
    } else {
      current.add(courseId)
    }
    _savedCourses.value = current
    prefs.edit().putString("saved_courses", stringSetAdapter.toJson(current)).apply()
  }

  // --- Comparison tray (up to 3 items) ---

  fun toggleCompareCareer(careerId: String): Boolean {
    val current = _comparedCareerIds.value.toMutableList()
    if (current.contains(careerId)) {
      current.remove(careerId)
      _comparedCareerIds.value = current
      return true
    } else if (current.size < 3) {
      current.add(careerId)
      _comparedCareerIds.value = current
      return true
    }
    return false // Tray full
  }

  fun clearCareerComparisons() {
    _comparedCareerIds.value = emptyList()
  }

  fun toggleCompareCourse(courseId: String): Boolean {
    val current = _comparedCourseIds.value.toMutableList()
    if (current.contains(courseId)) {
      current.remove(courseId)
      _comparedCourseIds.value = current
      return true
    } else if (current.size < 3) {
      current.add(courseId)
      _comparedCourseIds.value = current
      return true
    }
    return false
  }

  fun clearCourseComparisons() {
    _comparedCourseIds.value = emptyList()
  }

  // --- Plan ---

  private fun loadPlan(): StudentPlan {
    val json = prefs.getString("student_plan", null)
    return if (json != null) {
      try {
        planAdapter.fromJson(json) ?: defaultPlan()
      } catch (e: Exception) {
        defaultPlan()
      }
    } else {
      defaultPlan()
    }
  }

  private fun defaultPlan(): StudentPlan {
    return StudentPlan(
      steps = SampleData.defaultPlanSteps,
      firstChoice = "Science (PCM) with Computer Science / Polytechnic Diploma",
      firstChoiceReason = "Curious about software systems, mechanics, and practical math.",
      backupChoice = "Commerce with Informatics Practices or ITI Electrician",
      backupChoiceReason = "Reliable practical skills with strong independent career pathways.",
      questionsForMentors = listOf(
        "Does the local higher secondary school have functioning computer & physics labs?",
        "What are the typical bus travel times and total book expenses for Class 11?",
        "Are government post-matric scholarships accessible for our family category?"
      ),
      generalNotes = "Plan to visit two nearby colleges next Saturday with elder cousin."
    )
  }

  fun updatePlan(newPlan: StudentPlan) {
    _plan.value = newPlan
    prefs.edit().putString("student_plan", planAdapter.toJson(newPlan)).apply()
  }

  fun toggleStepCompletion(stepId: String) {
    val current = _plan.value.steps.map { step ->
      if (step.id == stepId) step.copy(isCompleted = !step.isCompleted) else step
    }
    updatePlan(_plan.value.copy(steps = current))
  }

  fun addPlanStep(title: String, description: String, targetDate: String? = null) {
    val newStep = PlanStep(
      id = "step-${UUID.randomUUID().toString().take(8)}",
      title = title,
      description = description,
      isCompleted = false,
      targetDate = targetDate,
      isPredefined = false
    )
    val current = _plan.value.steps + newStep
    updatePlan(_plan.value.copy(steps = current))
  }

  fun removePlanStep(stepId: String) {
    val current = _plan.value.steps.filter { it.id != stepId }
    updatePlan(_plan.value.copy(steps = current))
  }

  fun updatePlanWorksheet(
    firstChoice: String,
    firstReason: String,
    backupChoice: String,
    backupReason: String,
    notes: String,
    questions: List<String>
  ) {
    val current = _plan.value.copy(
      firstChoice = firstChoice,
      firstChoiceReason = firstReason,
      backupChoice = backupChoice,
      backupChoiceReason = backupReason,
      generalNotes = notes,
      questionsForMentors = questions
    )
    updatePlan(current)
  }

  // --- Guided Chat ---

  private fun loadChatMessages(): List<ChatMessage> {
    val json = prefs.getString("chat_history", null)
    if (json != null) {
      try {
        val list = chatListAdapter.fromJson(json)
        if (!list.isNullOrEmpty()) return list
      } catch (e: Exception) {
        // fallback
      }
    }
    return listOf(
      ChatMessage(
        id = "chat-1",
        sender = MessageSender.GUIDE,
        text = "Welcome to Pathfinder India. Let's start with you. What subjects, activities, or problems do you enjoy thinking about?",
        suggestedChips = SampleData.sampleChatSuggestions,
        stepPrompt = "interests"
      )
    )
  }

  fun sendUserChatMessage(text: String) {
    val current = _chatMessages.value.toMutableList()
    val userMsg = ChatMessage(
      id = "user-${System.currentTimeMillis()}",
      sender = MessageSender.USER,
      text = text
    )
    current.add(userMsg)

    // Rule-based demo response
    val guideResponse = generateDemoGuideResponse(text, _profile.value)
    current.add(guideResponse)

    _chatMessages.value = current
    prefs.edit().putString("chat_history", chatListAdapter.toJson(current)).apply()
  }

  private fun generateDemoGuideResponse(userInput: String, currentProfile: StudentProfile): ChatMessage {
    val lower = userInput.lowercase()
    val stageNotSet = currentProfile.studyStage == StudyStage.BOTH

    if (lower.contains("problem") || lower.contains("math") || lower.contains("logic") || lower.contains("code")) {
      addInterest("Problem solving")
      return ChatMessage(
        id = "guide-${System.currentTimeMillis()}",
        sender = MessageSender.GUIDE,
        text = "That analytical curiosity is wonderful. Whether it's diagnosing a circuit, writing code, or tracking financial records, problem solvers find many rewarding paths. Are you looking at pathways right after Class 10 or after Class 12 / Intermediate?",
        suggestedChips = listOf("Choosing after Class 10", "Choosing after Class 12", "Explore Technology paths", "Explore Science & Commerce"),
        stepPrompt = "stage"
      )
    }

    if (lower.contains("help") || lower.contains("people") || lower.contains("counsel") || lower.contains("doctor") || lower.contains("nurse")) {
      addInterest("Helping people")
      return ChatMessage(
        id = "guide-${System.currentTimeMillis()}",
        sender = MessageSender.GUIDE,
        text = "Supporting others is at the heart of meaningful work—from healthcare and teaching to community law and psychology. What kind of environment feels more comfortable: a clinical setting, a school, or community fieldwork?",
        suggestedChips = listOf("Clinical & healthcare", "Teaching & mentoring", "Community & public service", "Skip this question"),
        stepPrompt = "environment"
      )
    }

    if (lower.contains("art") || lower.contains("design") || lower.contains("draw") || lower.contains("creative")) {
      addInterest("Art & Design")
      return ChatMessage(
        id = "guide-${System.currentTimeMillis()}",
        sender = MessageSender.GUIDE,
        text = "Design is much more than decoration—it's how we make everyday tools, buildings, and communications usable and pleasant. Would you like to explore visual digital design, architecture, or crafts?",
        suggestedChips = listOf("Digital & UI design", "Architecture & spaces", "Crafts & media", "Show me directions"),
        stepPrompt = "design_branch"
      )
    }

    if (lower.contains("nature") || lower.contains("plant") || lower.contains("farm") || lower.contains("animal") || lower.contains("environment")) {
      addInterest("Nature & Environment")
      return ChatMessage(
        id = "guide-${System.currentTimeMillis()}",
        sender = MessageSender.GUIDE,
        text = "Ecology, modern agriculture, and environmental sciences are crucial for our country's future. Do you lean towards outdoor fieldwork, laboratory research, or sustainable technology like solar energy?",
        suggestedChips = listOf("Outdoor farm advisory", "Renewable energy & solar", "Environmental science", "Show me directions"),
        stepPrompt = "nature_branch"
      )
    }

    if (lower.contains("business") || lower.contains("money") || lower.contains("account") || lower.contains("finance")) {
      addInterest("Business & Finance")
      return ChatMessage(
        id = "guide-${System.currentTimeMillis()}",
        sender = MessageSender.GUIDE,
        text = "Understanding commerce, markets, and enterprise gives you versatile tools. Are you intrigued by financial accounting, corporate management, or starting a local venture?",
        suggestedChips = listOf("Accounting & Auditing (CA)", "Business management (BBA)", "Corporate law", "Show me directions"),
        stepPrompt = "business_branch"
      )
    }

    if (lower.contains("10") || lower.contains("tenth") || lower.contains("class 10")) {
      setStudyStage(StudyStage.AFTER_10TH)
      return ChatMessage(
        id = "guide-${System.currentTimeMillis()}",
        sender = MessageSender.GUIDE,
        text = "Got it! We've set your stage to 'After Class 10'. You have solid pathways ahead: Higher Secondary (Science/Commerce/Humanities), Polytechnic Diplomas, ITI Crafts, and Open Schooling. What subjects felt most engaging in school?",
        suggestedChips = listOf("Maths & Science", "Social Sciences & English", "Hands-on crafts", "Show me directions"),
        stepPrompt = "subjects"
      )
    }

    if (lower.contains("12") || lower.contains("inter") || lower.contains("twelfth") || lower.contains("intermediate")) {
      setStudyStage(StudyStage.AFTER_12TH)
      return ChatMessage(
        id = "guide-${System.currentTimeMillis()}",
        sender = MessageSender.GUIDE,
        text = "Great! We've set your stage to 'After Class 12 / Intermediate'. This opens doors to Bachelor degrees, specialized diplomas, apprenticeships, and professional licenses. You don't have to pick just one yet—what broad field sparks your interest?",
        suggestedChips = listOf("Engineering & Tech", "Healthcare & Care", "Commerce & Law", "Creative & Media"),
        stepPrompt = "field_after_12"
      )
    }

    // Default supportive response
    return ChatMessage(
      id = "guide-${System.currentTimeMillis()}",
      sender = MessageSender.GUIDE,
      text = "Thank you for sharing that. It's completely natural to explore multiple thoughts at once. You can change your mind at any point. Would you like to see a few sample directions tailored to what we've discussed?",
      suggestedChips = listOf("Show me directions", "Explore Study Paths", "Tell you more interests", "I'm still figuring it out"),
      stepPrompt = "general"
    )
  }

  fun resetAllDemoData() {
    prefs.edit().clear().apply()
    _profile.value = StudentProfile(
      studyStage = StudyStage.AFTER_10TH,
      subjects = listOf("Science", "Mathematics", "English"),
      interests = listOf("Problem solving", "Technology"),
      strengths = listOf("Logical reasoning", "Practical experiments"),
      preferences = listOf("Hands-on learning", "Balanced work-life")
    )
    _plan.value = defaultPlan()
    _savedCareers.value = setOf("car-data-analyst", "car-agri-horticulture")
    _savedCourses.value = setOf("crs-inter-mpc", "crs-btech-cse")
    _comparedCareerIds.value = emptyList()
    _comparedCourseIds.value = emptyList()
    _chatMessages.value = listOf(
      ChatMessage(
        id = "chat-1",
        sender = MessageSender.GUIDE,
        text = "Welcome to Pathfinder India. Let's start with you. What subjects, activities, or problems do you enjoy thinking about?",
        suggestedChips = SampleData.sampleChatSuggestions,
        stepPrompt = "interests"
      )
    )
  }

  companion object {
    @Volatile
    private var INSTANCE: PathfinderRepository? = null

    fun getInstance(context: Context): PathfinderRepository {
      return INSTANCE ?: synchronized(this) {
        INSTANCE ?: PathfinderRepository(context).also { INSTANCE = it }
      }
    }
  }
}
