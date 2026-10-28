package com.example.englishlearning.data.model

enum class QuizType {
    PICTURE_IDENTIFY,   // ছবি দেখে নাম বলা
    LISTEN_AND_PICK,    // শুনে সঠিক উত্তর নির্বাচন
    TRANSLATE_MATCH     // বাংলা অর্থ দেখে ইংরেজি নির্বাচন
}

data class QuizItem(
    val id: String,
    val type: QuizType = QuizType.PICTURE_IDENTIFY,
    val questionImageEmoji: String,        // যেমন: "🍎" বা "🐱"
    val questionPromptBn: String,          // যেমন: "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:"
    val options: List<String>,             // ৪টি অপশন
    val correctAnswer: String,             // সঠিক উত্তর
    val explanationBn: String = ""         // সঠিক উত্তরের পর বাংলা ব্যাখ্যা
)
