package com.example.englishlearning.data.repository

import com.example.englishlearning.data.model.Lesson
import com.example.englishlearning.data.model.Level
import com.example.englishlearning.data.model.QuizItem
import com.example.englishlearning.data.model.QuizType
import com.example.englishlearning.data.model.WordItem

/**
 * লেভেল ১-এর জন্য সমৃদ্ধ শব্দ ও ফনিক্স সংগ্রহস্থল।
 * এখানে প্রতিটি শব্দের সাথে আকর্ষণীয় ছবি/ইমোজি, বাংলা উচ্চারণ ও অর্থ রয়েছে।
 * প্রতিটি স্টেপে ৫টি করে বহুনির্বাচনী কুইজ রয়েছে।
 */
object Level1ContentRepository {

    fun getLevel1Data(): Level {
        return Level(
            id = 1,
            titleBn = "লেভেল ১: শব্দ, ফনিক্স ও প্রাথমিক জ্ঞান",
            titleEn = "Level 1: Phonics & Daily Vocabulary",
            subtitleBn = "ছবি দেখে শব্দ শেখা ও কুইজ খেলে পরের ধাপ আনলক করা",
            isUnlocked = true,
            lessons = listOf(
                createStep1_1(),
                createStep1_2(),
                createStep1_3()
            )
        )
    }

    /**
     * স্টেপ ১.১: ফনিক্স ও পরিচিত বস্তু (স্ক্রল করে অনেকগুলো শব্দ দেখার সুযোগ)
     */
    private fun createStep1_1(): Lesson {
        return Lesson(
            id = "step_1_1",
            levelId = 1,
            titleBn = "স্টেপ ১.১: প্রথম পরিচয় ও ফনিক্স",
            titleEn = "Step 1.1: Everyday Phonics",
            descriptionBn = "ছবি ও অডিও শুনে ইংরেজি শব্দের সঠিক উচ্চারণ শেখো।",
            isUnlocked = true,
            starsEarned = 0,
            words = listOf(
                WordItem("w1", "Apple", "আপেল", "অ্যাপল", "🍎", "I love sweet apples.", "আমি মিষ্টি আপেল ভালোবাসি।"),
                WordItem("w2", "Ball", "বল", "বল্", "⚽", "Throw the ball to me.", "আমার দিকে বলটি ছুড়ে দাও।"),
                WordItem("w3", "Cat", "বিড়াল", "ক্যাট", "🐱", "The cat drinks milk.", "বিড়ালটি দুধ খায়।"),
                WordItem("w4", "Dog", "কুকুর", "ডগ", "🐶", "The dog is loyal.", "কুকুর বিশ্বস্ত প্রাণী।"),
                WordItem("w5", "Elephant", "হাতি", "এলিফ্যান্ট", "🐘", "The elephant is huge.", "হাতি অনেক বড়।"),
                WordItem("w6", "Fish", "মাছ", "ফিশ", "🐟", "Fish swims in water.", "মাছ পানিতে সাঁতার কাটে।"),
                WordItem("w7", "Grapes", "আঙ্গুর", "গ্রেপ্স", "🍇", "Grapes are juicy.", "আঙ্গুর রসালো ফল।"),
                WordItem("w8", "Horse", "ঘোড়া", "হর্স", "🐴", "The horse runs fast.", "ঘোড়া দ্রুত দৌড়ায়।"),
                WordItem("w9", "Ice Cream", "আইসক্রিম", "আইসক্রিম", "🍦", "Kids love ice cream.", "বাচ্চারা আইসক্রিম পছন্দ করে।"),
                WordItem("w10", "Juice", "ফলের রস", "জুস", "🧃", "Orange juice is fresh.", "কমলার রস সতেজ।"),
                WordItem("w11", "Kite", "ঘুড়ি", "কাইট", "🪁", "Fly a kite in the sky.", "আকাশে ঘুড়ি ওড়াও।"),
                WordItem("w12", "Lion", "সিংহ", "লায়ন", "🦁", "The lion is the king.", "সিংহ বনের রাজা।")
            ),
            // ৫টি কুইজ, প্রতিটিতে ৪টি করে অপশন
            quizzes = listOf(
                QuizItem(
                    id = "q1",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageEmoji = "🍎",
                    questionPromptBn = "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Apple", "Ball", "Cat", "Dog"),
                    correctAnswer = "Apple",
                    explanationBn = "🍎 হলো Apple (আপেল)"
                ),
                QuizItem(
                    id = "q2",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageEmoji = "🐱",
                    questionPromptBn = "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Elephant", "Fish", "Cat", "Horse"),
                    correctAnswer = "Cat",
                    explanationBn = "🐱 হলো Cat (বিড়াল)"
                ),
                QuizItem(
                    id = "q3",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageEmoji = "🐟",
                    questionPromptBn = "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Fish", "Grapes", "Kite", "Ball"),
                    correctAnswer = "Fish",
                    explanationBn = "🐟 হলো Fish (মাছ)"
                ),
                QuizItem(
                    id = "q4",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageEmoji = "⚽",
                    questionPromptBn = "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Dog", "Ball", "Juice", "Lion"),
                    correctAnswer = "Ball",
                    explanationBn = "⚽ হলো Ball (বল)"
                ),
                QuizItem(
                    id = "q5",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageEmoji = "🦁",
                    questionPromptBn = "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Horse", "Cat", "Lion", "Elephant"),
                    correctAnswer = "Lion",
                    explanationBn = "🦁 হলো Lion (সিংহ)"
                )
            )
        )
    }

    /**
     * স্টেপ ১.২: চেনা রঙ ও আকার
     */
    private fun createStep1_2(): Lesson {
        return Lesson(
            id = "step_1_2",
            levelId = 1,
            titleBn = "স্টেপ ১.২: আকর্ষণীয় রঙ ও আকার",
            titleEn = "Step 1.2: Colors & Shapes",
            descriptionBn = "চারপাশের চেনা রঙগুলোর নাম ও উচ্চারণ শেখা।",
            isUnlocked = false,
            starsEarned = 0,
            words = listOf(
                WordItem("w20", "Red", "লাল", "রেড", "🔴", "The apple is red.", "আপেলটি লাল।"),
                WordItem("w21", "Green", "সবুজ", "গ্রিন", "🟢", "The grass is green.", "ঘাস সবুজ।"),
                WordItem("w22", "Blue", "নীল", "ব্লু", "🔵", "The sky is blue.", "আকাশ নীল।"),
                WordItem("w23", "Yellow", "হলুদ", "ইয়েলো", "🟡", "The sun is yellow.", "সূর্য হলুদ।"),
                WordItem("w24", "Star", "তারা", "স্টার", "⭐", "A bright star.", "উজ্জ্বল তারা।")
            ),
            quizzes = listOf(
                QuizItem("q2_1", QuizType.PICTURE_IDENTIFY, "🔴", "এই রঙের ইংরেজি কী?", listOf("Red", "Blue", "Green", "Yellow"), "Red"),
                QuizItem("q2_2", QuizType.PICTURE_IDENTIFY, "🟢", "এই রঙের ইংরেজি কী?", listOf("Yellow", "Green", "Red", "Blue"), "Green"),
                QuizItem("q2_3", QuizType.PICTURE_IDENTIFY, "🔵", "এই রঙের ইংরেজি কী?", listOf("Blue", "Red", "Green", "Star"), "Blue"),
                QuizItem("q2_4", QuizType.PICTURE_IDENTIFY, "🟡", "এই রঙের ইংরেজি কী?", listOf("Green", "Yellow", "Blue", "Red"), "Yellow"),
                QuizItem("q2_5", QuizType.PICTURE_IDENTIFY, "⭐", "এই আকারের ইংরেজি নাম কী?", listOf("Star", "Ball", "Circle", "Red"), "Star")
            )
        )
    }

    /**
     * স্টেপ ১.৩: প্রথম কুশল বিনিময়
     */
    private fun createStep1_3(): Lesson {
        return Lesson(
            id = "step_1_3",
            levelId = 1,
            titleBn = "স্টেপ ১.৩: প্রথম কথা বলা (Greetings)",
            titleEn = "Step 1.3: Everyday Greetings",
            descriptionBn = "প্রতিদিনের কুশল বিনিময় ও ধন্যবাদ জানানোর বাক্য।",
            isUnlocked = false,
            starsEarned = 0,
            words = listOf(
                WordItem("w30", "Hello", "হ্যালো / ওহে", "হ্যালো", "👋", "Hello friend!", "হ্যালো বন্ধু!"),
                WordItem("w31", "Thank you", "ধন্যবাদ", "থ্যাঙ্ক ইউ", "🙏", "Thank you so much.", "তোমাকে অনেক ধন্যবাদ।"),
                WordItem("w32", "Goodbye", "বিদায়", "গুডবাই", "🙋", "Goodbye, take care.", "বিদায়, ভালো থেকো।"),
                WordItem("w33", "Please", "দয়া করে", "প্লিজ", "🤝", "Please help me.", "দয়া করে আমাকে সাহায্য করো।"),
                WordItem("w34", "Friend", "বন্ধু", "ফ্রেন্ড", "🧑‍🤝‍🧑", "You are my friend.", "তুমি আমার বন্ধু।")
            ),
            quizzes = listOf(
                QuizItem("q3_1", QuizType.PICTURE_IDENTIFY, "👋", "কাউকে অভিবাদন জানাতে কী বলবে?", listOf("Hello", "Goodbye", "No", "Please"), "Hello"),
                QuizItem("q3_2", QuizType.PICTURE_IDENTIFY, "🙏", "কেউ সাহায্য করলে কী বলবে?", listOf("Hello", "Thank you", "Goodbye", "Red"), "Thank you"),
                QuizItem("q3_3", QuizType.PICTURE_IDENTIFY, "🙋", "বিদায় নেওয়ার সময় কী বলবে?", listOf("Goodbye", "Thank you", "Hello", "Please"), "Goodbye"),
                QuizItem("q3_4", QuizType.PICTURE_IDENTIFY, "🤝", "বিনম্রভাবে কিছু চাইতে কী বলবে?", listOf("Please", "No", "Goodbye", "Dog"), "Please"),
                QuizItem("q3_5", QuizType.PICTURE_IDENTIFY, "🧑‍🤝‍🧑", "বন্ধুকে ইংরেজিতে কী বলে?", listOf("Friend", "Enemy", "Cat", "Ball"), "Friend")
            )
        )
    }
}
