package com.example.englishlearning.data.model

/**
 * মূল লেভেল মডেল (যেমন: Level 1 - Kids & Beginners Foundation)।
 * এর ভেতরে একাধিক সাব-স্টেপ (Lessons) থাকবে।
 */
data class Level(
    val id: Int,                    // যেমন: 1
    val titleBn: String,            // যেমন: "লেভেল ১: একদম শুরু (বাচ্চা ও নতুনদের জন্য)"
    val titleEn: String,            // যেমন: "Level 1: Foundation"
    val subtitleBn: String,         // যেমন: "সহজ শব্দ, সঠিক উচ্চারণ ও প্রথম কথা বলা"
    val isUnlocked: Boolean = false,
    val lessons: List<Lesson> = emptyList()
) {
    /**
     * এই লেভেলের সবগুলো সাব-স্টেপের মোট স্টার সংখ্যা হিসাব করার ফাংশন
     */
    fun totalStarsEarned(): Int = lessons.sumOf { it.starsEarned }
    
    /**
     * লেভেলের কত শতাংশ সম্পন্ন হয়েছে তা বের করার ফাংশন (০% থেকে ১০০%)
     */
    fun completionPercentage(): Float {
        if (lessons.isEmpty()) return 0f
        val completedCount = lessons.count { it.starsEarned > 0 }
        return (completedCount.toFloat() / lessons.size.toFloat()) * 100f
    }
}
