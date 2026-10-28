package com.example.englishlearning.data.repository

import com.example.englishlearning.data.model.Lesson
import com.example.englishlearning.data.model.Level
import com.example.englishlearning.data.model.QuizItem
import com.example.englishlearning.data.model.QuizType
import com.example.englishlearning.data.model.WordItem

/**
 * লেভেল ১-এর জন্য সুবিশাল ও সমৃদ্ধ শব্দভাণ্ডার।
 * এখানে প্রতিটি শব্দের সাথে বড় থ্রি-ডি (3D) ছবির লিংক, সঠিক উচ্চারণ ও বাংলা অর্থ রয়েছে।
 * আপনি পরবর্তীতে যত খুশি শব্দ এখানে যোগ করতে পারবেন, অ্যাপ নিজে থেকেই আনলিমিটেড স্ক্রল করাবে।
 */
object Level1ContentRepository {

    fun getLevel1Data(): Level {
        return Level(
            id = 1,
            titleBn = "লেভেল ১: শব্দ, ফনিক্স ও প্রাথমিক জ্ঞান",
            titleEn = "Level 1: Phonics & Visual Vocabulary",
            subtitleBn = "বড় থ্রি-ডি ছবি দেখে শব্দ শেখা ও কুইজ টেস্ট",
            isUnlocked = true,
            lessons = listOf(
                createStep1_1(),
                createStep1_2(),
                createStep1_3()
            )
        )
    }

    /**
     * স্টেপ ১.১: প্রথম পরিচয় ও ফনিক্স (বড় থ্রি-ডি ছবি ও অনেকগুলো শব্দ)
     */
    private fun createStep1_1(): Lesson {
        return Lesson(
            id = "step_1_1",
            levelId = 1,
            titleBn = "স্টেপ ১.১: প্রথম পরিচয় ও ফনিক্স",
            titleEn = "Step 1.1: 3D Visual Phonics",
            descriptionBn = "বড় থ্রি-ডি ছবি ও অডিও শুনে ইংরেজি শব্দের সঠিক উচ্চারণ শেখো।",
            isUnlocked = true,
            starsEarned = 0,
            words = listOf(
                WordItem(
                    id = "w1",
                    englishWord = "Apple",
                    bengaliMeaning = "আপেল",
                    bengaliPronunciation = "অ্যাপল",
                    imageUrl = "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "An apple a day keeps the doctor away.",
                    exampleSentenceBn = "প্রতিদিন একটি আপেল ডাক্তার দূরে রাখে।",
                    cardBgColorHex = "#FEE2E2"
                ),
                WordItem(
                    id = "w2",
                    englishWord = "Ball",
                    bengaliMeaning = "বল",
                    bengaliPronunciation = "বল্",
                    imageUrl = "https://images.unsplash.com/photo-1575361204480-aadea25e6e68?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "We play with a soccer ball.",
                    exampleSentenceBn = "আমরা ফুটবল দিয়ে খেলি।",
                    cardBgColorHex = "#E0E7FF"
                ),
                WordItem(
                    id = "w3",
                    englishWord = "Cat",
                    bengaliMeaning = "বিড়াল",
                    bengaliPronunciation = "ক্যাট",
                    imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "The cute cat is sleeping.",
                    exampleSentenceBn = "মিষ্টি বিড়ালটি ঘুমাচ্ছে।",
                    cardBgColorHex = "#FEF3C7"
                ),
                WordItem(
                    id = "w4",
                    englishWord = "Dog",
                    bengaliMeaning = "কুকুর",
                    bengaliPronunciation = "ডগ",
                    imageUrl = "https://images.unsplash.com/photo-1543466835-00a7907e9de1?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "The dog wags its tail.",
                    exampleSentenceBn = "কুকুরটি তার লেজ নাড়ায়।",
                    cardBgColorHex = "#FFEDD5"
                ),
                WordItem(
                    id = "w5",
                    englishWord = "Elephant",
                    bengaliMeaning = "হাতি",
                    bengaliPronunciation = "এলিফ্যান্ট",
                    imageUrl = "https://images.unsplash.com/photo-1557050543-4d5f4e07ef46?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "The elephant has a long trunk.",
                    exampleSentenceBn = "হাতির একটি লম্বা শুঁড় আছে।",
                    cardBgColorHex = "#F1F5F9"
                ),
                WordItem(
                    id = "w6",
                    englishWord = "Fish",
                    bengaliMeaning = "মাছ",
                    bengaliPronunciation = "ফিশ",
                    imageUrl = "https://images.unsplash.com/photo-1522069169874-c58ec4b76be5?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "Goldfish swims in the water.",
                    exampleSentenceBn = "গোল্ডফিশ পানিতে সাঁতার কাটে।",
                    cardBgColorHex = "#E0F2FE"
                ),
                WordItem(
                    id = "w7",
                    englishWord = "Grapes",
                    bengaliMeaning = "আঙ্গুর",
                    bengaliPronunciation = "গ্রেপ্স",
                    imageUrl = "https://images.unsplash.com/photo-1537640538966-79f369143f8f?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "Fresh grapes are sweet and sour.",
                    exampleSentenceBn = "তাজা আঙ্গুর মিষ্টি ও টক হয়।",
                    cardBgColorHex = "#F3E8FF"
                ),
                WordItem(
                    id = "w8",
                    englishWord = "Horse",
                    bengaliMeaning = "ঘোড়া",
                    bengaliPronunciation = "হর্স",
                    imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "The horse runs like the wind.",
                    exampleSentenceBn = "ঘোড়া বাতাসের মতো দৌড়ায়।",
                    cardBgColorHex = "#FEE2E2"
                ),
                WordItem(
                    id = "w9",
                    englishWord = "Ice Cream",
                    bengaliMeaning = "আইসক্রিম",
                    bengaliPronunciation = "আইসক্রিম",
                    imageUrl = "https://images.unsplash.com/photo-1497034825429-c343d7c6a68f?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "Strawberry ice cream is delicious.",
                    exampleSentenceBn = "স্ট্রবেরি আইসক্রিম অনেক সুস্বাদু।",
                    cardBgColorHex = "#FCE7F3"
                ),
                WordItem(
                    id = "w10",
                    englishWord = "Juice",
                    bengaliMeaning = "ফলের রস",
                    bengaliPronunciation = "জুস",
                    imageUrl = "https://images.unsplash.com/photo-1613478223719-2ab802602423?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "A glass of orange juice.",
                    exampleSentenceBn = "এক গ্লাস কমলার রস।",
                    cardBgColorHex = "#FEF3C7"
                ),
                WordItem(
                    id = "w11",
                    englishWord = "Lion",
                    bengaliMeaning = "সিংহ",
                    bengaliPronunciation = "লায়ন",
                    imageUrl = "https://images.unsplash.com/photo-1614027164847-1b28cfe1df60?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "The lion roars loudly.",
                    exampleSentenceBn = "সিংহ গর্জন করে।",
                    cardBgColorHex = "#FFEDD5"
                ),
                WordItem(
                    id = "w12",
                    englishWord = "Mango",
                    bengaliMeaning = "আম",
                    bengaliPronunciation = "ম্যাঙ্গো",
                    imageUrl = "https://images.unsplash.com/photo-1553279768-865429fa0078?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "Mango is the king of fruits.",
                    exampleSentenceBn = "আম হলো ফলের রাজা।",
                    cardBgColorHex = "#FEF08A"
                ),
                WordItem(
                    id = "w13",
                    englishWord = "Orange",
                    bengaliMeaning = "কমলা",
                    bengaliPronunciation = "অরেঞ্জ",
                    imageUrl = "https://images.unsplash.com/photo-1582979512210-99b6a53386f9?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "Orange is rich in Vitamin C.",
                    exampleSentenceBn = "কমলায় প্রচুর ভিটামিন সি আছে।",
                    cardBgColorHex = "#FFEDD5"
                ),
                WordItem(
                    id = "w14",
                    englishWord = "Rabbit",
                    bengaliMeaning = "খরগোশ",
                    bengaliPronunciation = "র‍্যাবিট",
                    imageUrl = "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "The white rabbit hops around.",
                    exampleSentenceBn = "সাদা খরগোশটি লাফিয়ে চলে।",
                    cardBgColorHex = "#F1F5F9"
                ),
                WordItem(
                    id = "w15",
                    englishWord = "Sun",
                    bengaliMeaning = "সূর্য",
                    bengaliPronunciation = "সান",
                    imageUrl = "https://images.unsplash.com/photo-1538370965046-79c0d6907d47?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "The sun rises in the east.",
                    exampleSentenceBn = "সূর্য পূর্ব দিকে ওঠে।",
                    cardBgColorHex = "#FEF9C3"
                ),
                WordItem(
                    id = "w16",
                    englishWord = "Tiger",
                    bengaliMeaning = "বাঘ",
                    bengaliPronunciation = "টাইগার",
                    imageUrl = "https://images.unsplash.com/photo-1561731216-c3a4d99437d5?w=600&auto=format&fit=crop&q=80",
                    exampleSentenceEn = "The Royal Bengal Tiger is mighty.",
                    exampleSentenceBn = "রয়েল বেঙ্গল টাইগার অত্যন্ত শক্তিশালী।",
                    cardBgColorHex = "#FFEDD5"
                )
            ),
            // ৫টি কুইজ - প্রতিটিতে ৪টি অপশন এবং বড় ছবির লিংক
            quizzes = listOf(
                QuizItem(
                    id = "q1",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageUrl = "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=600&auto=format&fit=crop&q=80",
                    questionPromptBn = "এই বড় ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Apple", "Ball", "Cat", "Dog"),
                    correctAnswer = "Apple",
                    explanationBn = "এটি হলো Apple (আপেল)"
                ),
                QuizItem(
                    id = "q2",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=600&auto=format&fit=crop&q=80",
                    questionPromptBn = "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Elephant", "Fish", "Cat", "Horse"),
                    correctAnswer = "Cat",
                    explanationBn = "এটি হলো Cat (বিড়াল)"
                ),
                QuizItem(
                    id = "q3",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageUrl = "https://images.unsplash.com/photo-1522069169874-c58ec4b76be5?w=600&auto=format&fit=crop&q=80",
                    questionPromptBn = "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Fish", "Grapes", "Orange", "Ball"),
                    correctAnswer = "Fish",
                    explanationBn = "এটি হলো Fish (মাছ)"
                ),
                QuizItem(
                    id = "q4",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageUrl = "https://images.unsplash.com/photo-1575361204480-aadea25e6e68?w=600&auto=format&fit=crop&q=80",
                    questionPromptBn = "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Dog", "Ball", "Juice", "Lion"),
                    correctAnswer = "Ball",
                    explanationBn = "এটি হলো Ball (খেলার বল)"
                ),
                QuizItem(
                    id = "q5",
                    type = QuizType.PICTURE_IDENTIFY,
                    questionImageUrl = "https://images.unsplash.com/photo-1614027164847-1b28cfe1df60?w=600&auto=format&fit=crop&q=80",
                    questionPromptBn = "এই ছবিটি কিসের? সঠিক ইংরেজি নির্বাচন করো:",
                    options = listOf("Horse", "Cat", "Lion", "Tiger"),
                    correctAnswer = "Lion",
                    explanationBn = "এটি হলো Lion (বনের রাজা সিংহ)"
                )
            )
        )
    }

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
                WordItem("w20", "Red", "লাল", "রেড", "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=600&auto=format&fit=crop&q=80", "The rose is red.", "গোলাপটি লাল।"),
                WordItem("w21", "Green", "সবুজ", "গ্রিন", "https://images.unsplash.com/photo-1518531933037-91b2f5f229cc?w=600&auto=format&fit=crop&q=80", "The leaves are green.", "গাছের পাতা সবুজ।"),
                WordItem("w22", "Blue", "নীল", "ব্লু", "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=600&auto=format&fit=crop&q=80", "The ocean is blue.", "সমুদ্র নীল।"),
                WordItem("w23", "Yellow", "হলুদ", "ইয়েলো", "https://images.unsplash.com/photo-1538370965046-79c0d6907d47?w=600&auto=format&fit=crop&q=80", "The sunflower is yellow.", "সূর্যমুখী হলুদ।")
            ),
            quizzes = listOf(
                QuizItem("q2_1", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=600&auto=format&fit=crop&q=80", "এই রঙের ইংরেজি কী?", listOf("Red", "Blue", "Green", "Yellow"), "Red"),
                QuizItem("q2_2", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1518531933037-91b2f5f229cc?w=600&auto=format&fit=crop&q=80", "এই রঙের ইংরেজি কী?", listOf("Yellow", "Green", "Red", "Blue"), "Green"),
                QuizItem("q2_3", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=600&auto=format&fit=crop&q=80", "এই রঙের ইংরেজি কী?", listOf("Blue", "Red", "Green", "Yellow"), "Blue"),
                QuizItem("q2_4", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1538370965046-79c0d6907d47?w=600&auto=format&fit=crop&q=80", "এই রঙের ইংরেজি কী?", listOf("Green", "Yellow", "Blue", "Red"), "Yellow"),
                QuizItem("q2_5", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1538370965046-79c0d6907d47?w=600&auto=format&fit=crop&q=80", "সূর্যের রঙের ইংরেজি নাম কী?", listOf("Yellow", "Blue", "Green", "Red"), "Yellow")
            )
        )
    }

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
                WordItem("w30", "Hello", "হ্যালো / ওহে", "হ্যালো", "https://images.unsplash.com/photo-1577563908411-5077b6dc7624?w=600&auto=format&fit=crop&q=80", "Hello my dear friend!", "হ্যালো আমার প্রিয় বন্ধু!"),
                WordItem("w31", "Thank you", "ধন্যবাদ", "থ্যাঙ্ক ইউ", "https://images.unsplash.com/photo-1531206715517-5c0ba140b2b8?w=600&auto=format&fit=crop&q=80", "Thank you for your help.", "তোমার সাহায্যের জন্য ধন্যবাদ।"),
                WordItem("w32", "Goodbye", "বিদায়", "গুডবাই", "https://images.unsplash.com/photo-1506869640319-fe1a24fd76dc?w=600&auto=format&fit=crop&q=80", "Goodbye, see you tomorrow.", "বিদায়, কাল দেখা হবে।")
            ),
            quizzes = listOf(
                QuizItem("q3_1", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1577563908411-5077b6dc7624?w=600&auto=format&fit=crop&q=80", "কাউকে অভিবাদন জানাতে কী বলবে?", listOf("Hello", "Goodbye", "No", "Please"), "Hello"),
                QuizItem("q3_2", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1531206715517-5c0ba140b2b8?w=600&auto=format&fit=crop&q=80", "কেউ সাহায্য করলে কী বলবে?", listOf("Hello", "Thank you", "Goodbye", "Red"), "Thank you"),
                QuizItem("q3_3", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1506869640319-fe1a24fd76dc?w=600&auto=format&fit=crop&q=80", "বিদায় নেওয়ার সময় কী বলবে?", listOf("Goodbye", "Thank you", "Hello", "Please"), "Goodbye"),
                QuizItem("q3_4", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1531206715517-5c0ba140b2b8?w=600&auto=format&fit=crop&q=80", "ধন্যবাদ এর ইংরেজি কী?", listOf("Thank you", "Sorry", "Welcome", "Please"), "Thank you"),
                QuizItem("q3_5", QuizType.PICTURE_IDENTIFY, "https://images.unsplash.com/photo-1577563908411-5077b6dc7624?w=600&auto=format&fit=crop&q=80", "বন্ধুকে কী বলবে?", listOf("Hello", "Goodbye", "Cat", "Dog"), "Hello")
            )
        )
    }
}
