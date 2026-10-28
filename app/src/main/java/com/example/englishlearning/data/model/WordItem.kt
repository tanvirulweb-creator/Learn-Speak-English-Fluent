package com.example.englishlearning.data.model

/**
 * ইংরেজি শেখার প্রতিটি শব্দের মডেল।
 * এতে ছবি/ইমোজি, ইংরেজি বানান, বাংলা অর্থ এবং বাংলা উচ্চারণ থাকবে।
 */
data class WordItem(
    val id: String,
    val englishWord: String,            // যেমন: "Apple"
    val bengaliMeaning: String,          // যেমন: "আপেল"
    val bengaliPronunciation: String,    // যেমন: "অ্যাপল"
    val emojiIcon: String = "✨",         // ছবির বিকল্প বা ইলাস্ট্রেশন ইমোজি (যেমন: 🍎)
    val exampleSentenceEn: String = "",
    val exampleSentenceBn: String = "",
    val imageAssetPath: String? = null
)
