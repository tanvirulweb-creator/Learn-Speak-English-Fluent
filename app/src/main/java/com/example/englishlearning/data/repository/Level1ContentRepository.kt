package com.example.englishlearning.data.repository

import com.example.englishlearning.data.model.*

/**
 * লেভেল ১-এর জন্য প্রাথমিক সমৃদ্ধ কনটেন্ট সংগ্রহস্থল (Repository)।
 * এখানে বাচ্চা ও নতুনদের জন্য ৩টি মৌলিক সাব-স্টেপ তৈরি করা হয়েছে।
 */
object Level1ContentRepository {

    fun getLevel1Data(): Level {
        return Level(
            id = 1,
            titleBn = "লেভেল ১: ফাউন্ডেশন (বাচ্চা ও নতুনদের জন্য)",
            titleEn = "Level 1: Beginner Foundation",
            subtitleBn = "সহজ শব্দ, স্পষ্ট উচ্চারণ ও প্রাথমিক কথোপকথন",
            isUnlocked = true, // প্রথম লেভেলটি সবসময় উন্মুক্ত থাকবে
            lessons = listOf(
                createStep1_1(),
                createStep1_2(),
                createStep1_3()
            )
        )
    }

    /**
     * স্টেপ ১.১: প্রথম পরিচয় ও ফনিক্স শব্দ
     */
    private fun createStep1_1(): Lesson {
        return Lesson(
            id = "step_1_1",
            levelId = 1,
            titleBn = "স্টেপ ১.১: প্রথম শব্দ ও ফনিক্স",
            titleEn = "Step 1.1: Phonics & Everyday Words",
            descriptionBn = "ছবি ও অডিও শুনে ইংরেজি শব্দের সঠিক উচ্চারণ শেখা।",
            isUnlocked = true, // প্রথম স্টেপ আনলক
            starsEarned = 0,
            words = listOf(
                WordItem(
                    id = "w_apple",
                    englishWord = "Apple",
                    bengaliMeaning = "আপেল",
                    bengaliPronunciation = "অ্যাপল",
                    exampleSentenceEn = "I eat an apple.",
                    exampleSentenceBn = "আমি আপেল খাই।"
                ),
                WordItem(
                    id = "w_ball",
                    englishWord = "Ball",
                    bengaliMeaning = "বল / খেলার বল",
                    bengaliPronunciation = "বল্",
                    exampleSentenceEn = "This is a red ball.",
                    exampleSentenceBn = "এটি একটি লাল বল।"
                ),
                WordItem(
                    id = "w_cat",
                    englishWord = "Cat",
                    bengaliMeaning = "বিড়াল",
                    bengaliPronunciation = "ক্যাট",
                    exampleSentenceEn = "The cat is cute.",
                    exampleSentenceBn = "বিড়ালটি সুন্দর।"
                ),
                WordItem(
                    id = "w_dog",
                    englishWord = "Dog",
                    bengaliMeaning = "কুকুর",
                    bengaliPronunciation = "ডগ",
                    exampleSentenceEn = "The dog runs fast.",
                    exampleSentenceBn = "কুকুরটি দ্রুত দৌড়ায়।"
                )
            ),
            quizzes = listOf(
                QuizItem(
                    id = "q_1",
                    type = QuizType.LISTEN_AND_PICK,
                    questionPromptEn = "Which one is 'Apple'?",
                    questionPromptBn = "'আপেল' এর ইংরেজি কোনটি?",
                    options = listOf("Apple", "Cat", "Ball"),
                    correctAnswer = "Apple"
                ),
                QuizItem(
                    id = "q_2",
                    type = QuizType.SPEAK_PRACTICE,
                    questionPromptEn = "Say: 'Cat'",
                    questionPromptBn = "মাইক চেপে মুখে বলো: 'Cat'",
                    targetSpeechWord = "Cat",
                    correctAnswer = "Cat"
                )
            )
        )
    }

    /**
     * স্টেপ ১.২: পরিচিত রঙ ও আকার (Colors & Shapes)
     */
    private fun createStep1_2(): Lesson {
        return Lesson(
            id = "step_1_2",
            levelId = 1,
            titleBn = "স্টেপ ১.২: রঙ ও আকার",
            titleEn = "Step 1.2: Colors & Shapes",
            descriptionBn = "চারপাশের চেনা রঙগুলোর ইংরেজি নাম ও উচ্চারণ।",
            isUnlocked = false, // স্টেপ ১.১ সফলভাবে পার করলে আনলক হবে
            starsEarned = 0,
            words = listOf(
                WordItem(
                    id = "w_red",
                    englishWord = "Red",
                    bengaliMeaning = "লাল",
                    bengaliPronunciation = "রেড",
                    exampleSentenceEn = "The rose is red.",
                    exampleSentenceBn = "গোলাপটি লাল।"
                ),
                WordItem(
                    id = "w_green",
                    englishWord = "Green",
                    bengaliMeaning = "সবুজ",
                    bengaliPronunciation = "গ্রিন",
                    exampleSentenceEn = "Leaves are green.",
                    exampleSentenceBn = "গাছের পাতা সবুজ।"
                ),
                WordItem(
                    id = "w_blue",
                    englishWord = "Blue",
                    bengaliMeaning = "নীল",
                    bengaliPronunciation = "ব্লু",
                    exampleSentenceEn = "The sky is blue.",
                    exampleSentenceBn = "আকাশ নীল।"
                )
            ),
            quizzes = listOf(
                QuizItem(
                    id = "q_3",
                    type = QuizType.TRANSLATE_MATCH,
                    questionPromptEn = "What is 'সবুজ' in English?",
                    questionPromptBn = "'সবুজ' এর ইংরেজি কী?",
                    options = listOf("Blue", "Green", "Red"),
                    correctAnswer = "Green"
                ),
                QuizItem(
                    id = "q_4",
                    type = QuizType.SPEAK_PRACTICE,
                    questionPromptEn = "Say: 'Red'",
                    questionPromptBn = "মাইক চেপে মুখে বলো: 'Red'",
                    targetSpeechWord = "Red",
                    correctAnswer = "Red"
                )
            )
        )
    }

    /**
     * স্টেপ ১.৩: প্রথম কথোপকথন ও অভিবাদন (Daily Greetings)
     */
    private fun createStep1_3(): Lesson {
        return Lesson(
            id = "step_1_3",
            levelId = 1,
            titleBn = "স্টেপ ১.৩: কথা বলা শুরু (Greetings)",
            titleEn = "Step 1.3: First Conversation",
            descriptionBn = "দৈনন্দিন কুশল বিনিময় ও অভিবাদন জানানোর বাক্য।",
            isUnlocked = false, // স্টেপ ১.২ পার করলে আনলক হবে
            starsEarned = 0,
            words = listOf(
                WordItem(
                    id = "w_hello",
                    englishWord = "Hello",
                    bengaliMeaning = "হ্যালো / ওহে",
                    bengaliPronunciation = "হ্যালো",
                    exampleSentenceEn = "Hello, how are you?",
                    exampleSentenceBn = "হ্যালো, আপনি কেমন আছেন?"
                ),
                WordItem(
                    id = "w_thankyou",
                    englishWord = "Thank you",
                    bengaliMeaning = "ধন্যবাদ",
                    bengaliPronunciation = "থ্যাঙ্ক ইউ",
                    exampleSentenceEn = "Thank you very much.",
                    exampleSentenceBn = "আপনাকে অনেক ধন্যবাদ।"
                ),
                WordItem(
                    id = "w_goodbye",
                    englishWord = "Goodbye",
                    bengaliMeaning = "বিদায়",
                    bengaliPronunciation = "গুডবাই",
                    exampleSentenceEn = "Goodbye, see you soon!",
                    exampleSentenceBn = "বিদায়, আবার দেখা হবে!"
                )
            ),
            quizzes = listOf(
                QuizItem(
                    id = "q_5",
                    type = QuizType.SPEAK_PRACTICE,
                    questionPromptEn = "Say: 'Hello'",
                    questionPromptBn = "মাইক চেপে মুখে সুন্দর করে বলো: 'Hello'",
                    targetSpeechWord = "Hello",
                    correctAnswer = "Hello"
                ),
                QuizItem(
                    id = "q_6",
                    type = QuizType.LISTEN_AND_PICK,
                    questionPromptEn = "Someone helped you. What should you say?",
                    questionPromptBn = "কেউ তোমাকে সাহায্য করলে কী বলবে?",
                    options = listOf("Thank you", "Goodbye", "No"),
                    correctAnswer = "Thank you"
                )
            )
        )
    }
}
