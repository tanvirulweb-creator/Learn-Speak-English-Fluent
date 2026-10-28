package com.example.englishlearning.data.repository

import com.example.englishlearning.data.model.Lesson
import com.example.englishlearning.data.model.Level
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ব্যবহারকারীর অগ্রগতি (Progress) এবং স্বয়ংক্রিয় আনলক লজিক নিয়ন্ত্রণকারী।
 * এটি লেভেল ও লেসনগুলোর লাইভ স্ট্যাটাস অ্যাপের স্ক্রিনে রিয়েল-টাইমে আপডেট করে।
 */
class UserProgressRepository {

    // লেভেল ১-এর প্রাথমিক ডেটা লোড
    private val _currentLevel = MutableStateFlow(Level1ContentRepository.getLevel1Data())
    val currentLevel: StateFlow<Level> = _currentLevel.asStateFlow()

    // ব্যবহারকারীর মোট অর্জিত স্টার সংখ্যা
    private val _totalStars = MutableStateFlow(0)
    val totalStars: StateFlow<Int> = _totalStars.asStateFlow()

    /**
     * একটি লেসন সম্পন্ন হলে এই ফাংশনটি কল হবে:
     * ১. সেই লেসনে প্রাপ্ত স্টার (১ থেকে ৩) সেভ হবে।
     * ২. স্বয়ংক্রিয়ভাবে পরবর্তী লেসনটি আনলক (Unlock) হয়ে যাবে।
     * ৩. মোট স্টার কাউন্ট আপডেট হবে।
     */
    fun completeLesson(lessonId: String, stars: Int) {
        val currentLessons = _currentLevel.value.lessons.toMutableList()
        val completedIndex = currentLessons.indexOfFirst { it.id == lessonId }

        if (completedIndex != -1) {
            // ১. বর্তমান লেসন আপডেট করা
            val currentLesson = currentLessons[completedIndex]
            val updatedCurrent = currentLesson.copy(
                starsEarned = maxOf(currentLesson.starsEarned, stars)
            )
            currentLessons[completedIndex] = updatedCurrent

            // ২. পরবর্তী লেসনের তালা খুলে দেওয়া (Auto Unlock Next Lesson)
            val nextIndex = completedIndex + 1
            if (nextIndex < currentLessons.size) {
                val nextLesson = currentLessons[nextIndex]
                if (!nextLesson.isUnlocked) {
                    currentLessons[nextIndex] = nextLesson.copy(isUnlocked = true)
                }
            }

            // ৩. স্টেট আপডেট করা
            val updatedLevel = _currentLevel.value.copy(lessons = currentLessons)
            _currentLevel.update { updatedLevel }
            _totalStars.update { updatedLevel.totalStarsEarned() }
        }
    }

    /**
     * নির্দিষ্ট লেসনের বিস্তারিত তথ্য পাওয়ার ফাংশন
     */
    fun getLessonById(lessonId: String): Lesson? {
        return _currentLevel.value.lessons.find { it.id == lessonId }
    }

    /**
     * লেভেল ১ কি সম্পূর্ণ শেষ হয়েছে?
     * সব লেসন পার হলে এটি true হবে, যা পরবর্তীতে লেভেল ২ আনলক করতে কাজে লাগবে।
     */
    fun isLevel1Completed(): Boolean {
        return _currentLevel.value.lessons.all { it.starsEarned > 0 }
    }
}
