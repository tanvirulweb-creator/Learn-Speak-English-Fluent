package com.example.learnspeakenglishfluent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.englishlearning.data.model.Lesson
import com.example.englishlearning.data.repository.UserProgressRepository

class MainActivity : ComponentActivity() {

    private val progressRepository = UserProgressRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF7F9FC)
                ) {
                    val currentLevel by progressRepository.currentLevel.collectAsState()
                    val totalStars by progressRepository.totalStars.collectAsState()

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // অ্যাপ হেডার
                        AppHeader(
                            totalStars = totalStars,
                            completionPercent = currentLevel.completionPercentage()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = currentLevel.titleBn,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                        Text(
                            text = currentLevel.subtitleBn,
                            fontSize = 13.sp,
                            color = Color(0xFF64748B)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // লেসনগুলোর তালিকা (পার্ট ১ ভেরিফিকেশন ও আনলক টেস্ট)
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(currentLevel.lessons) { lesson ->
                                LessonCard(
                                    lesson = lesson,
                                    onCompleteClick = {
                                        progressRepository.completeLesson(lesson.id, stars = 3)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppHeader(totalStars: Int, completionPercent: Float) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4F46E5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Learn & Speak English",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "অগ্রগতি: ${completionPercent.toInt()}% সম্পন্ন",
                    fontSize = 14.sp,
                    color = Color(0xFFC7D2FE)
                )
            }
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFFEF3C7)
            ) {
                Text(
                    text = "⭐ $totalStars স্টার",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD97706)
                )
            }
        }
    }
}

@Composable
fun LessonCard(lesson: Lesson, onCompleteClick: () -> Unit) {
    val backgroundColor = if (lesson.isUnlocked) Color.White else Color(0xFFF1F5F9)
    val titleColor = if (lesson.isUnlocked) Color(0xFF0F172A) else Color(0xFF94A3B8)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(if (lesson.isUnlocked) 4.dp else 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = lesson.titleBn,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleColor
                )

                if (lesson.isUnlocked) {
                    val starIcons = "⭐".repeat(lesson.starsEarned)
                    Text(text = if (lesson.starsEarned > 0) starIcons else "🔓 উন্মুক্ত")
                } else {
                    Text(text = "🔒 তালাবদ্ধ", color = Color(0xFF94A3B8))
                }
            }

            Text(
                text = lesson.descriptionBn,
                fontSize = 12.sp,
                color = Color(0xFF64748B),
                modifier = Modifier.padding(top = 4.dp)
            )

            if (lesson.isUnlocked) {
                Spacer(modifier = Modifier.height(10.dp))
                // শব্দের প্রিভিউ
                Text(
                    text = "শব্দ: " + lesson.words.joinToString(", ") { "${it.englishWord} (${it.bengaliMeaning})" },
                    fontSize = 12.sp,
                    color = Color(0xFF4338CA)
                )

                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onCompleteClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "টেস্ট: সম্পন্ন করুন ও পরেরটি আনলক করুন")
                }
            }
        }
    }
}
