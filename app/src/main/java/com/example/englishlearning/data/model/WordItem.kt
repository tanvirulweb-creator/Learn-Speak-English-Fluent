package com.example.englishlearning.data.model

/**
 * ইংরেজি শেখার প্রতিটি শব্দের মডেল।
 * এতে বড় থ্রি-ডি (3D) ছবি বা অ্যানিমেটেড GIF ফাইল লোড করার সম্পূর্ণ সাপোর্ট রয়েছে।
 * কোনো ইমোজি ব্যবহার করা হয়নি।
 */
data class WordItem(
    val id: String,
    val englishWord: String,            // যেমন: "Apple"
    val bengaliMeaning: String,          // যেমন: "আপেল"
    val bengaliPronunciation: String,    // যেমন: "অ্যাপল"
    val imageUrl: String,               // বড় থ্রি-ডি ছবি বা অ্যানিমেটেড GIF ফাইলের লিংক (Cloud / Asset)
    val exampleSentenceEn: String = "", // উদাহরণ বাক্য (ইংরেজি)
    val exampleSentenceBn: String = "", // উদাহরণ বাক্য (বাংলা)
    val cardBgColorHex: String = "#F8FAFC" // ব্যাকগ্রাউন্ড টোন
)
