package com.example.englishlearning.data.model

/**
 * ইংরেজি শেখার প্রতিটি শব্দের মডেল।
 * এটি বাচ্চার স্ক্রিনে ছবি, ইংরেজি বানান, বাংলা অর্থ এবং উচ্চারণে সাহায্য করবে।
 */
data class WordItem(
    val id: String,
    val englishWord: String,           // যেমন: "Apple"
    val bengaliMeaning: String,         // যেমন: "আপেল"
    val bengaliPronunciation: String,   // যেমন: "অ্যাপল" (বাচ্চাদের সহজে পড়ার জন্য)
    val exampleSentenceEn: String,      // যেমন: "This is an apple."
    val exampleSentenceBn: String,      // যেমন: "এটি একটি আপেল।"
    val imageAssetPath: String? = null  // ছবির পাথ
)
