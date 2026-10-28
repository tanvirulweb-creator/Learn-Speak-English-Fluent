package com.example.englishlearning.data.model

/**
 * প্রতিটি সাব-স্টেপ বা লেসনের ডেটা মডেল।
 * প্রতিটি লেসনে শব্দ শেখা (words) এবং কুইজ প্র্যাকটিস (quizzes) থাকবে।
 */
data class Lesson(
    val id: String,                 // যেমন: "step_1_1"
    val levelId: Int,               // লেভেল নম্বর, যেমন: 1
    val titleBn: String,            // যেমন: "স্টেপ ১.১: ফনিক্স ও প্রথম শব্দ"
    val titleEn: String,            // যেমন: "Step 1.1: Phonics & First Words"
    val descriptionBn: String,      // সংক্ষিপ্ত বিবরণ
    val isUnlocked: Boolean = false,// এই স্টেপটি আনলক আছে কিনা
    val starsEarned: Int = 0,       // ইউজার কয়টি স্টার পেয়েছে (০ থেকে ৩)
    val words: List<WordItem> = emptyList(),
    val quizzes: List<QuizItem> = emptyList()
)
