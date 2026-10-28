# 📱 বাংলাভাষী ও শিশুদের জন্য ইংরেজি শেখার অ্যান্ড্রয়েড অ্যাপ (Kids to Pro)
## 🛠️ মাস্টার টেকনিক্যাল ব্লুপ্রিন্ট ও কমপ্লিট রোডম্যাপ

---

## ১. প্রজেক্ট পরিচিতি ও আর্কিটেকচার ওভারভিউ

* **টার্গেট অডিয়েন্স:** বাংলাভাষী (শিশু থেকে শুরু করে বড়রা যারা অনর্গল ইংরেজি বলতে চান)।
* **অ্যাপ আর্কিটেকচার:** নেটিভ অ্যান্ড্রয়েড (Kotlin + Jetpack Compose) উইথ **MVVM (Model-View-ViewModel)** প্যাটার্ন।
* **মূল বৈশিষ্ট্য:**
  1. গেমিফাইড লেভেল আনলক পাথওয়ে (ডুওলিঙ্গোর মতো)।
  2. ১০০% ফ্রি অফলাইন নেটিভ ভয়েস ইঞ্জিন (Android TTS & SpeechRecognizer)।
  3. সম্পূর্ণ ফ্রি ইউজার বিহেভিয়ার ও লেসন ট্র্যাকিং (Firebase Analytics)।
  4. হালকা সাইজ (১০–১৫ এমবি) এবং ল্যাগ-মুক্ত দ্রুত পারফরম্যান্স।

---

## ২. প্রস্তুতি ও প্রয়োজনীয় টুলস (Environment Setup)

কাজ শুরু করার আগে আপনার পিসিতে যা যা সেটআপ থাকতে হবে:

| উপাদান | প্রয়োজনীয় সংস্করণ | বিবরণ |
| :--- | :--- | :--- |
| **অপারেটিং সিস্টেম** | Windows / macOS / Linux | ন্যূনতম ৮ জিবি র‍্যাম (১৬ জিবি হলে সেরা)। |
| **Android Studio** | Latest Version (Koala / Ladybug) | অফিসিয়াল আইডিই (IDE)। |
| **Java Development Kit (JDK)** | JDK 17 বা JDK 21 | Android Studio-এর ভেতরেই বিল্ট-ইন থাকে। |
| **Android SDK** | Min SDK: 24 (Android 7.0) <br> Target SDK: 34/35 | যাতে পুরনো ও নতুন প্রায় সব ডিভাইসে চলে। |
| **টেস্টিং ডিভাইস** | Physical Android Phone বা Emulator | ফিজিক্যাল ফোনে মাইক্রোফোন ও স্পিকার সরাসরি টেস্ট করা সহজ। |

---

## ৩. প্রয়োজনীয় লাইব্রেরি ও ডিপেন্ডেন্সি (Tech Stack)

অ্যাপটি তৈরি করতে `build.gradle.kts` ফাইলে যেসব ফ্রি ডিপেন্ডেন্সি ব্যবহার করা হবে:

1. **Jetpack Compose:** আধুনিক ডিক্লোরেটিভ ইউআই ও অ্যানিমেশনের জন্য।
2. **Navigation Compose:** এক স্ক্রিন থেকে অন্য স্ক্রিনে ডাটা পাস ও যাওয়ার জন্য।
3. **Android Speech & TTS:** `android.speech.tts.TextToSpeech` এবং `android.speech.SpeechRecognizer` (কোনো অতিরিক্ত লাইব্রেরি ছাড়াই বিল্ট-ইন কাজ করবে)।
4. **Jetpack DataStore (Preferences):** ব্যবহারকারীর লেভেল আনলক ও স্টার পয়েন্ট লোকালি সেভ রাখার জন্য।
5. **Kotlinx Coroutines & Flow:** স্মুথ ব্যাকগ্রাউন্ড প্রসেসিংয়ের জন্য।
6. **Firebase Analytics:** ইউজার অ্যানালিটিক্স ও বিহেভিয়ার ট্র্যাকিংয়ের জন্য।

---

## ৪. ফোল্ডার স্ট্রাকচার (Clean Architecture)

প্রজেক্টটি গোছানো রাখতে নিচের ফোল্ডার বিন্যাস অনুসরণ করা হবে:

```
app/src/main/java/com/example/englishlearning/
│
├── data/
│   ├── model/             # ডেটা ক্লাস (Level, Lesson, WordItem, Quiz)
│   ├── repository/        # ডেটা লোড ও সেভ করার লজিক
│   └── local/             # DataStore ম্যানেজার (Progress সেভার)
│
├── engine/
│   ├── audio/             # Text-To-Speech (TTS) ম্যানেজার
│   └── speech/            # Speech-To-Text (উচ্চারণ যাচাইকারী)
│
├── ui/
│   ├── map/               # হোম স্ক্রিন (ডুওলিঙ্গো স্টাইলের লেভেল ম্যাপ)
│   ├── lesson/            # শব্দ ও বাক্য শেখার স্ক্রিন
│   ├── quiz/              # উচ্চারণ টেস্ট ও ড্র্যাগ-ড্রপ কুইজ
│   ├── components/        # পুনরায় ব্যবহারযোগ্য বাটন, কার্ড, স্টার
│   └── theme/             # বাচ্চাদের উপযোগী আকর্ষণীয় থিম ও কালার
│
└── analytics/
    └── AnalyticsHelper.kt # ফায়ারবেস ট্র্যাকিং ইভেন্ট হ্যান্ডলার
```

---

## ৫. ধাপে ধাপে টেকনিক্যাল বাস্তবায়ন নির্দেশিকা (Step-by-Step Implementation)

### 📌 ধাপ ১: প্রজেক্ট ইনিশিয়ালাইজেশন ও পারমিশন
* Android Studio-তে `Empty Compose Activity` দিয়ে নতুন প্রজেক্ট তৈরি।
* `AndroidManifest.xml`-এ মাইক্রোফোন পারমিশন যুক্ত করা:
  ```xml
  <uses-permission android:name="android.permission.RECORD_AUDIO" />
  <uses-permission android:name="android.permission.INTERNET" />
  ```

---

### 📌 ধাপ ২: ডেটা মডেল ও লেভেল ১ স্ট্রাকচার
ডেটার জন্য কোটলিন ডেটা ক্লাস তৈরি:
```kotlin
data class Level(
    val id: Int,
    val title: String,
    val isUnlocked: Boolean,
    val lessons: List<Lesson>
)

data class Lesson(
    val id: String,
    val title: String,
    val starsEarned: Int = 0,
    val words: List<WordItem>
)

data class WordItem(
    val english: String,
    val bengali: String,
    val pronunciationBengali: String,
    val imageRes: Int? = null
)
```

---

### 📌 ধাপ ৩: নেটিভ অডিও ও স্পিচ ইঞ্জিন তৈরি
1. **TTS Engine (`TtsManager.kt`):**
   * টেক্সট দিলে যেন স্পষ্ট আমেরিকান বা ব্রিটিশ অ্যাকসেন্টে পড়ে শোনায়।
   * বাচ্চাদের জন্য গতি (`setSpeechRate`) ০.৭৫x রাখা যায়।
2. **Speech Engine (`SpeechManager.kt`):**
   * মাইক চেপে ইউজার কথা বললে অডিও শুনে টেক্সট বের করা।
   * লেভেনশটাইন ডিসট্যান্স (Levenshtein algorithm) ব্যবহার করে দেখা ইউজারের উচ্চারণ শব্দের সাথে কত শতাংশ মিলল।

---

### 📌 ধাপ ৪: গেমিফাইড লেভেল ম্যাপ ডিজাইন (UI/UX)
* **Roadmap Screen:**
  * আঁকাবাঁকা নোড (Nodes) আকারে লেভেলগুলো সাজানো।
  * স্টেপ ১ আনলকড থাকবে।
  * স্টেপ ২, ৩ লকড (🔒) থাকবে।
  * একটি স্টেপ সফলভাবে শেষ করলে অ্যানিমেশনসহ পরবর্তী স্টেপ আনলক হবে।

---

### 📌 ধাপ ৫: লেসন ও কুইজ মেকানিক্স
* **কার্ড ভিউ:** উপরে ছবি, নিচে ইংরেজি শব্দ, ক্লিক করলে অডিও বাজবে, সাথে বাংলা অর্থ।
* **স্পিকিং চ্যালেঞ্জ:** স্ক্রিনে লেখা থাকবে *"Apple"* ➜ ইউজার মাইক চেপে বলবে *"Apple"* ➜ সঠিক হলে গ্রিন স্ক্রিন + সাউন্ড, ভুল হলে আবার চেষ্টা করার অপশন।

---

### 📌 ধাপ ৬: অগ্রগতি সংরক্ষণ (Local Persistence)
* **Jetpack DataStore** দিয়ে ইউজারের অগ্রগতি সংরক্ষণ:
  * কোন লেভেল পর্যন্ত শেষ হয়েছে।
  * মোট অর্জিত স্টার ও পয়েন্ট।
  * অ্যাপ বন্ধ করে পুনরায় ওপেন করলেও সব তথ্য সংরক্ষিত থাকবে।

---

### 📌 ধাপ ৭: ফায়ারবেস অ্যানালিটিক্স ও ট্র্যাকিং সেটআপ
* ফায়ারবেস কনসোল থেকে প্রজেক্ট তৈরি করে `google-services.json` অ্যাপে যুক্ত করা।
* যেসব ইভেন্ট স্বয়ংক্রিয়ভাবে ক্লাউডে লগ হবে:
  * `lesson_started` (লেসন আইডি সহ)
  * `lesson_completed` (সময় ও স্টার সহ)
  * `lesson_time_spent` (কত সেকেন্ড সময় লেগেছে)
  * `pronunciation_failed` (কোন শব্দের উচ্চারণে ইউজার বেশি ভুল করছে)

---

## ৬. প্রথম ধাপের জন্য চেকলিস্ট

- [ ] Android Studio ওপেন করে নতুন Jetpack Compose প্রজেক্ট তৈরি।
- [ ] প্যাকেজ স্ট্রাকচার সেটআপ করা (`data`, `engine`, `ui`)।
- [ ] লেভেল ১-এর জন্য প্রাথমিক কনটেন্ট ও ডেটা ক্লাস লেখা।
