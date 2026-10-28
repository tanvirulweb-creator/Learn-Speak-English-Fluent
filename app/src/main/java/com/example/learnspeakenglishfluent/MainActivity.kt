package com.example.learnspeakenglishfluent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.englishlearning.data.model.Lesson
import com.example.englishlearning.data.model.QuizItem
import com.example.englishlearning.data.model.WordItem
import com.example.englishlearning.data.repository.UserProgressRepository
import com.example.englishlearning.engine.audio.TtsManager

class MainActivity : ComponentActivity() {

    private val progressRepository = UserProgressRepository()
    private lateinit var ttsManager: TtsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ttsManager = TtsManager(this)

        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFFF8FAFC)
                ) { innerPadding ->
                    val currentLevel by progressRepository.currentLevel.collectAsState()
                    val totalStars by progressRepository.totalStars.collectAsState()

                    var selectedLessonId by remember { mutableStateOf("step_1_1") }
                    val currentLesson = currentLevel.lessons.find { it.id == selectedLessonId }
                        ?: currentLevel.lessons.first()

                    var isQuizActive by remember { mutableStateOf(false) }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (isQuizActive) {
                            QuizScreen(
                                quizzes = currentLesson.quizzes,
                                ttsManager = ttsManager,
                                onQuizComplete = { passedScore ->
                                    isQuizActive = false
                                    if (passedScore >= 4) {
                                        progressRepository.completeLesson(currentLesson.id, stars = 3)
                                    }
                                },
                                onExitQuiz = { isQuizActive = false }
                            )
                        } else {
                            VocabularyScreen(
                                levelTitle = currentLevel.titleBn,
                                totalStars = totalStars,
                                lessons = currentLevel.lessons,
                                activeLesson = currentLesson,
                                onSelectLesson = { selectedLessonId = it },
                                onStartQuiz = { isQuizActive = true },
                                onWordSpeak = { word -> ttsManager.speak(word) }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ttsManager.shutdown()
    }
}

/**
 * নিচে নিচে বড় ছবিযুক্ত শব্দতালিকা ও আনলিমিটেড স্ক্রল ভিউ
 */
@Composable
fun VocabularyScreen(
    levelTitle: String,
    totalStars: Int,
    lessons: List<Lesson>,
    activeLesson: Lesson,
    onSelectLesson: (String) -> Unit,
    onStartQuiz: () -> Unit,
    onWordSpeak: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // হেডার অংশ
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Learn & Speak English",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = levelTitle,
                        fontSize = 13.sp,
                        color = Color(0xFF64748B)
                    )
                }
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFFFEF3C7),
                    shadowElevation = 2.dp
                ) {
                    Text(
                        text = "⭐ $totalStars",
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD97706),
                        fontSize = 16.sp
                    )
                }
            }
        }

        // স্টেপ নেভিগেশন (স্টেপ ১.১, ১.২, ১.৩)
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(lessons) { lesson ->
                    val isSelected = lesson.id == activeLesson.id
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = when {
                            isSelected -> Color(0xFF4F46E5)
                            lesson.isUnlocked -> Color.White
                            else -> Color(0xFFE2E8F0)
                        },
                        modifier = Modifier.clickable(enabled = lesson.isUnlocked) {
                            onSelectLesson(lesson.id)
                        },
                        shadowElevation = if (isSelected) 4.dp else 1.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (lesson.isUnlocked) lesson.titleBn.split(":")[0] else "🔒 " + lesson.titleBn.split(":")[0],
                                color = if (isSelected) Color.White else if (lesson.isUnlocked) Color(0xFF334155) else Color(0xFF94A3B8),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            if (lesson.starsEarned > 0) {
                                Text(text = " ⭐", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }

        // টেস্ট বাটন
        item {
            Button(
                onClick = onStartQuiz,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = "🎯 কুইজ টেস্ট শুরু করো (পাস করতে ৪/৫ লাগবে)",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        item {
            Text(
                text = "📚 শব্দ ও ফনিক্স (বড় ছবিতে ক্লিক করে সঠিক উচ্চারণ শোনো):",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF334155)
            )
        }

        // নিচে নিচে সাজানো বড় ছবির শব্দ কার্ডগুলো (আনলিমিটেড স্ক্রলযোগ্য)
        items(activeLesson.words) { word ->
            LargeVisualWordCard(word = word, onSpeak = { onWordSpeak(word.englishWord) })
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

/**
 * নিচে নিচে সাজানো বড় থ্রি-ডি ছবিযুক্ত কার্ড
 */
@Composable
fun LargeVisualWordCard(word: WordItem, onSpeak: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSpeak() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // বড় থ্রি-ডি ছবি বা GIF
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFF1F5F9))
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(word.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = word.englishWord,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // ওপরে ডানপাশে স্পষ্ট সাউন্ড বাটন
                IconButton(
                    onClick = onSpeak,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(44.dp)
                        .background(Color.White.copy(alpha = 0.9f), shape = CircleShape)
                ) {
                    Text(text = "🔊", fontSize = 20.sp)
                }
            }

            // টেক্সট বিবরণ (ইংরেজি বড় ফন্ট, ফনিক্স ও বাংলা অর্থ)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = word.englishWord,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = "উচ্চারণ: ${word.bengaliPronunciation}",
                            fontSize = 15.sp,
                            color = Color(0xFF4F46E5),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFEEF2FF)
                    ) {
                        Text(
                            text = word.bengaliMeaning,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3730A3)
                        )
                    }
                }

                if (word.exampleSentenceEn.isNotBlank()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "উদাহরণ: \"${word.exampleSentenceEn}\"",
                        fontSize = 13.sp,
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "বাংলা: ${word.exampleSentenceBn}",
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }
        }
    }
}

/**
 * বড় ছবিযুক্ত ৫টি কুইজ স্ক্রিন
 */
@Composable
fun QuizScreen(
    quizzes: List<QuizItem>,
    ttsManager: TtsManager,
    onQuizComplete: (score: Int) -> Unit,
    onExitQuiz: () -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }
    var showResultDialog by remember { mutableStateOf(false) }

    val currentQuiz = quizzes.getOrNull(currentIndex)

    if (showResultDialog) {
        val isPassed = score >= 4
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    text = if (isPassed) "🎉 অসাধারণ! তুমি পাস করেছো!" else "😢 আরেকটু চেষ্টা করো!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column {
                    Text(text = "তুমি ৫টির মধ্যে $score টি সঠিক উত্তর দিয়েছো।", fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (isPassed) "দারুণ! পরবর্তী ধাপটি আনলক হয়ে গেছে! ⭐⭐⭐" else "পাস করতে কমপক্ষে ৪টি প্রশ্নের সঠিক উত্তর দিতে হবে।",
                        color = if (isPassed) Color(0xFF10B981) else Color(0xFFEF4444),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showResultDialog = false
                        onQuizComplete(score)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = if (isPassed) Color(0xFF10B981) else Color(0xFF4F46E5))
                ) {
                    Text(text = if (isPassed) "পরবর্তী ধাপে যাও 🚀" else "আবার চেষ্টা করো 🔄")
                }
            }
        )
    }

    if (currentQuiz == null) return

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onExitQuiz) {
                    Text(text = "✕", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Text(
                    text = "প্রশ্ন ${currentIndex + 1} / ${quizzes.size}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4F46E5)
                )
                Text(
                    text = "স্কোর: $score",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF10B981)
                )
            }

            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / quizzes.size.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFF4F46E5),
                trackColor = Color(0xFFCBD5E1)
            )
        }

        // কুইজের বড় ছবি
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color(0xFFF1F5F9))
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(currentQuiz.questionImageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Quiz Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Text(
                        text = currentQuiz.questionPromptBn,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }

        // নিচে নিচে ৪টি অপশন
        items(currentQuiz.options) { option ->
            val isSelected = selectedOption == option
            val isCorrect = option == currentQuiz.correctAnswer

            val buttonBg = when {
                isSubmitted && isCorrect -> Color(0xFFD1FAE5)
                isSubmitted && isSelected && !isCorrect -> Color(0xFFFEE2E2)
                isSelected -> Color(0xFFEEF2FF)
                else -> Color.White
            }

            val borderStroke = when {
                isSubmitted && isCorrect -> Color(0xFF10B981)
                isSubmitted && isSelected && !isCorrect -> Color(0xFFEF4444)
                isSelected -> Color(0xFF4F46E5)
                else -> Color(0xFFE2E8F0)
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable(enabled = !isSubmitted) {
                        selectedOption = option
                        ttsManager.speak(option)
                    },
                shape = RoundedCornerShape(14.dp),
                color = buttonBg,
                border = androidx.compose.foundation.BorderStroke(2.dp, borderStroke),
                shadowElevation = 1.dp
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = option,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                }
            }
        }

        // সাবমিট বা নেক্সট বাটন
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (!isSubmitted) {
                        if (selectedOption != null) {
                            isSubmitted = true
                            if (selectedOption == currentQuiz.correctAnswer) {
                                score++
                            }
                        }
                    } else {
                        if (currentIndex < quizzes.size - 1) {
                            currentIndex++
                            selectedOption = null
                            isSubmitted = false
                        } else {
                            showResultDialog = true
                        }
                    }
                },
                enabled = selectedOption != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSubmitted) Color(0xFF4F46E5) else Color(0xFF10B981)
                )
            ) {
                Text(
                    text = when {
                        !isSubmitted -> "উত্তর যাচাই করো"
                        currentIndex < quizzes.size - 1 -> "পরবর্তী প্রশ্ন ➔"
                        else -> "ফলাফল দেখো 🏆"
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
