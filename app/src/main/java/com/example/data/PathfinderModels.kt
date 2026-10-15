package com.example.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
enum class StudyStage(val displayName: String, val shortLabel: String) {
  AFTER_10TH("After Class 10", "Class 10"),
  AFTER_12TH("After Intermediate / Class 12", "Class 12 / Inter"),
  BOTH("All Stages", "All")
}

@JsonClass(generateAdapter = true)
enum class CareerFeedback {
  INTERESTED,
  NOT_FOR_ME,
  UNSURE
}

@JsonClass(generateAdapter = true)
data class Career(
  val id: String,
  val title: String,
  val field: String,
  val overview: String,
  val relevantSkills: List<String>,
  val whyItMayInterestYou: String,
  val possibleRoutes: List<String>,
  val workInvolves: List<String>,
  val thingsToInvestigate: List<String>,
  val relatedOptions: List<String>,
  val stageRelevance: StudyStage,
  val tags: List<String>
)

@JsonClass(generateAdapter = true)
data class Course(
  val id: String,
  val title: String,
  val area: String,
  val stage: StudyStage,
  val courseType: String,
  val approxDuration: String,
  val subjectsOrSkills: List<String>,
  val relatedCareers: List<String>,
  val personalizationNote: String,
  val whatStudentsStudy: List<String>,
  val skillsPractised: List<String>,
  val furtherStudy: List<String>,
  val questionsToAsk: List<String>,
  val whatToVerify: List<String>
)

@JsonClass(generateAdapter = true)
data class Pathway(
  val id: String,
  val title: String,
  val stage: StudyStage,
  val category: String,
  val shortDescription: String,
  val whoMayWantToExplore: String,
  val whatLearningInvolves: String,
  val questionsToCheck: List<String>,
  val sampleDirections: List<String>
)

@JsonClass(generateAdapter = true)
data class StudentProfile(
  val studyStage: StudyStage = StudyStage.AFTER_10TH,
  val subjects: List<String> = emptyList(),
  val interests: List<String> = emptyList(),
  val strengths: List<String> = emptyList(),
  val preferences: List<String> = emptyList(),
  val goals: List<String> = emptyList(),
  val avoidList: List<String> = emptyList(),
  val feedback: Map<String, CareerFeedback> = emptyMap()
)

@JsonClass(generateAdapter = true)
data class PlanStep(
  val id: String,
  val title: String,
  val description: String,
  val isCompleted: Boolean = false,
  val targetDate: String? = null,
  val personalNote: String? = null,
  val isPredefined: Boolean = false
)

@JsonClass(generateAdapter = true)
data class StudentPlan(
  val steps: List<PlanStep> = emptyList(),
  val firstChoice: String = "",
  val firstChoiceReason: String = "",
  val backupChoice: String = "",
  val backupChoiceReason: String = "",
  val questionsForMentors: List<String> = emptyList(),
  val generalNotes: String = ""
)

@JsonClass(generateAdapter = true)
enum class MessageSender {
  USER,
  GUIDE
}

@JsonClass(generateAdapter = true)
data class ChatMessage(
  val id: String,
  val sender: MessageSender,
  val text: String,
  val suggestedChips: List<String> = emptyList(),
  val stepPrompt: String? = null
)
