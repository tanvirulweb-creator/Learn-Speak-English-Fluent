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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    containerColor = Color(0xFFF1F5F9)
                ) { innerPadding ->
                    val currentLevel by progressRepository.currentLevel.collectAsState()
                    val totalStars by progressRepository.totalStars.collectAsState()

                    // সিলেক্টেড স্টেপ (ডিফল্টভাবে প্রথম স্টেপ)
                    var selectedLessonId by remember { mutableStateOf("step_1_1") }
                    val currentLesson = currentLevel.lessons.find { it.id == selectedLessonId }
                        ?: currentLevel.lessons.first()

                    // কুইজ স্টেট
                    var isQuizActive by remember { mutableStateOf(false) }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (isQuizActive) {
                            // কুইজ স্ক্রিন
                            QuizScreen(
                                quizzes = currentLesson.quizzes,
                                ttsManager = ttsManager,
                                onQuizComplete = { passedScore ->
                                    isQuizActive = false
                                    if (passedScore >= 4) {
                                        // ৪ বা ততোধিক সঠিক হলে ৩ স্টার পেয়ে পরবর্তী লেভেল আনলক হবে
                                        progressRepository.completeLesson(currentLesson.id, stars = 3)
                                    }
                                },
                                onExitQuiz = { isQuizActive = false }
                            )
                        } else {
                            // মূল শব্দ, ছবি ও ফনিক্স স্ক্রিন
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
 * শব্দ ও ফনিক্স শেখার স্ক্রিন (ছবি, উচ্চারণ ও স্ক্রলযোগ্য তালিকা)
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        // টপ বার (মোট স্টার ও টাইটেল)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Learn & Speak English",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1E293B)
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
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "⭐ $totalStars", fontWeight = FontWeight.Bold, color = Color(0xFFD97706), fontSize = 16.sp)
                }
            }
        }

        // স্টেপ সিলেকশন বার (স্টেপ ১.১, ১.২, ১.৩ - আনলক থাকলে ক্লিক করা যাবে)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp)
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
                    modifier = Modifier
                        .clickable(enabled = lesson.isUnlocked) { onSelectLesson(lesson.id) },
                    shadowElevation = if (isSelected) 4.dp else 1.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
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

        // কুইজ শুরু করার আকর্ষণীয় বাটন
        Button(
            onClick = onStartQuiz,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
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

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "📚 শব্দ ও ফনিক্স (ছবিতে ক্লিক করে সঠিক উচ্চারণ শোনো):",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF475569),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // স্ক্রলযোগ্য শব্দের তালিকা (পাশে ছবি/ইমোজি, ইংরেজি, বাংলা উচ্চারণ ও অর্থ)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(activeLesson.words) { word ->
                WordCardItem(word = word, onSpeak = { onWordSpeak(word.englishWord) })
            }
        }
    }
}

/**
 * প্রতিটি শব্দের ভিজ্যুয়াল কার্ড (ছবি + ইংরেজি + ফনিক্স উচ্চারণ + অর্থ + স্পিকার)
 */
@Composable
fun WordCardItem(word: WordItem, onSpeak: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSpeak() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ছবির অংশ (বড় আকর্ষণীয় ইলাস্ট্রেশন/ইমোজি)
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFFEEF2FF), shape = RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = word.emojiIcon, fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.width(14.dp))

            // টেক্সট অংশ (ইংরেজি শব্দ, বাংলা উচ্চারণ ও অর্থ)
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = word.englishWord,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "(${word.bengaliPronunciation})",
                        fontSize = 13.sp,
                        color = Color(0xFF6366F1),
                        fontWeight = FontWeight.Medium
                    )
                }

                Text(
                    text = "অর্থ: ${word.bengaliMeaning}",
                    fontSize = 14.sp,
                    color = Color(0xFF475569),
                    fontWeight = FontWeight.SemiBold
                )

                if (word.exampleSentenceEn.isNotBlank()) {
                    Text(
                        text = word.exampleSentenceEn,
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }

            // সাউন্ড প্লে বাটন
            IconButton(
                onClick = onSpeak,
                modifier = Modifier
                    .size(42.dp)
                    .background(Color(0xFFE0E7FF), shape = CircleShape)
            ) {
                Text(text = "🔊", fontSize = 18.sp)
            }
        }
    }
}

/**
 * ৫টি বহুনির্বাচনী কুইজ স্ক্রিন (ছবি দেখে ৪টি অপশন থেকে নির্বাচন ও পাস/ফেল যাচাই)
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
                    text = if (isPassed) "🎉 অসাধারণ! তুমি পাস করেছো!" else "😢 আরেকটু চেষ্টা করতে হবে!",
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // কুইজ হেডার (প্রোগ্রেস ও বের হওয়ার বাটন)
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
                .padding(vertical = 12.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Color(0xFF4F46E5),
            trackColor = Color(0xFFCBD5E1)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // কুইজের প্রশ্ন ও বড় ছবি
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ছবির ইমোজি
                Text(
                    text = currentQuiz.questionImageEmoji,
                    fontSize = 72.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = currentQuiz.questionPromptBn,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ৪টি অপশন বাটন
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            currentQuiz.options.forEach { option ->
                val isSelected = selectedOption == option
                val isCorrect = option == currentQuiz.correctAnswer

                val buttonBg = when {
                    isSubmitted && isCorrect -> Color(0xFFD1FAE5) // সঠিক হলে গ্রিন
                    isSubmitted && isSelected && !isCorrect -> Color(0xFFFEE2E2) // ভুল হলে রেড
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
                            ttsManager.speak(option) // অপশনে চাপলে উচ্চারণ পড়ে শোনাবে
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
        }

        Spacer(modifier = Modifier.weight(1f))

        // উত্তর সাবমিট বা পরবর্তী প্রশ্নের বাটন
        Button(
            onClick = {
                if (!isSubmitted) {
                    // সাবমিট করা হলো
                    if (selectedOption != null) {
                        isSubmitted = true
                        if (selectedOption == currentQuiz.correctAnswer) {
                            score++
                        }
                    }
                } else {
                    // পরবর্তী প্রশ্নে যাওয়া
                    if (currentIndex < quizzes.size - 1) {
                        currentIndex++
                        selectedOption = null
                        isSubmitted = false
                    } else {
                        // কুইজ সম্পন্ন হলো
                        showResultDialog = true
                    }
                }
            },
            enabled = selectedOption != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
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
