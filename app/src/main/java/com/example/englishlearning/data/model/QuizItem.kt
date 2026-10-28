package com.example.englishlearning.data.model

enum class QuizType {
    PICTURE_IDENTIFY,
    LISTEN_AND_PICK,
    TRANSLATE_MATCH
}

data class QuizItem(
    val id: String,
    val type: QuizType = QuizType.PICTURE_IDENTIFY,
    val questionImageUrl: String,          // বড় থ্রি-ডি ছবি বা অ্যানিমেটেড GIF লিংক
    val questionPromptBn: String,          // "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:"
    val options: List<String>,             // ৪টি অপশন
    val correctAnswer: String,             // সঠিক উত্তর
    val explanationBn: String = ""         // সঠিক উত্তরের বাংলা ব্যাখ্যা
)
