package com.example.englishlearning.engine.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

/**
 * অ্যান্ড্রয়েডের নেটিভ Text-To-Speech (TTS) ম্যানেজার।
 * এটি সম্পূর্ণ অফলাইনে এবং ১০০% ফ্রিতে যে কোনো ইংরেজি শব্দ স্পষ্ট ও সঠিক উচ্চারণে পড়ে শোনাবে।
 */
class TtsManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // ভাষা হিসেবে US English সেট করা
            val result = tts?.setLanguage(Locale.US)
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                isInitialized = true
                // বাচ্চাদের বোঝার সুবিধার্থে গতি কিছুটা স্বাভাবিক রাখা (0.85x)
                tts?.setSpeechRate(0.85f)
                tts?.setPitch(1.0f)
            }
        }
    }

    /**
     * নির্দিষ্ট ইংরেজি শব্দ বা বাক্য মুখে উচ্চারণ করে শোনানো
     */
    fun speak(text: String) {
        if (isInitialized && text.isNotBlank()) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "UTTERANCE_ID_${System.currentTimeMillis()}")
        }
    }

    /**
     * মেমোরি লিক রোধ করতে রিসোর্স ক্লিয়ার করা
     */
    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
