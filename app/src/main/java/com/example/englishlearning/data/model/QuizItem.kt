package com.example.englishlearning.data.model

/**
 * কুইজের বিভিন্ন ধরন:
 * 1. LISTEN_AND_PICK: সাউন্ড শুনে সঠিক ছবি বা শব্দ বাছাই করা
 * 2. SPEAK_PRACTICE: মাইক চেপে মুখে ইংরেজি উচ্চারণ করা
 * 3. TRANSLATE_MATCH: বাংলা দেখে সঠিক ইংরেজি নির্বাচন করা
 */
enum class QuizType {
    LISTEN_AND_PICK,
    SPEAK_PRACTICE,
    TRANSLATE_MATCH
}

data class QuizItem(
    val id: String,
    val type: QuizType,
    val questionPromptEn: String,          // যেমন: "Say: Hello!" অথবা "Which one is an Apple?"
    val questionPromptBn: String,          // বাংলা নির্দেশনা: "বলো: হ্যালো!"
    val targetSpeechWord: String? = null,  // স্পিকিং কুইজের ক্ষেত্রে ব্যবহারকারীকে যে শব্দ বলতে হবে
    val options: List<String> = emptyList(), // অপশনগুলো
    val correctAnswer: String,             // সঠিক উত্তর
    val pointsReward: Int = 10             // সঠিক উত্তরের পয়েন্ট
)
