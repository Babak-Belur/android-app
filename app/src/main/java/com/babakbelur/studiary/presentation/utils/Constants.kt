package com.babakbelur.studiary.presentation.utils

import com.babakbelur.studiary.core.domain.model.RangeTime
import com.babakbelur.studiary.core.domain.model.TestQuestion

object Constants {

    const val ARG_TARGET_ID = "targetId"

    const val ARG_USER_ID = "userId"

    const val ARG_TARGET_TIME = "targetTime"

    const val ARG_TARGET_SCORE = "targetScore"

    const val ARG_COURSE_ID = "courseId"

    val LIST_QUESTIONS = listOf(
        TestQuestion(
            question = "1. Nilai x yang memenuhi 8^(3x+1)=128^(x-1) adalah...",
            answerA = "-10",
            answerB = "-5",
            answerC = "-2",
            answerD = "2"
        ),TestQuestion(
            question = "2. Jika x dan y adalah penyelesaian dari sistem persamaan 2x+3y=3 dan 3x-y=10, maka nilai 2x-y= ....",
            answerA = "4",
            answerB = "5",
            answerC = "6",
            answerD = "7"
        ),TestQuestion(
            question = "3. Diketahui barisan aritmatika dengan U_5=17 dan U_10=32. Suku ke-20 adalah…",
            answerA = "57",
            answerB = "62",
            answerC = "67",
            answerD = "72"
        ),TestQuestion(
            question = "4. Rumus umum dari barisan bilangan -8,0,8,16,… adalah…",
            answerA = "Un = 2n",
            answerB = "Un = 2n + 2",
            answerC = "Un = 8n + 16",
            answerD = "Un = 8n + 16"
        ),TestQuestion(
            question = "5. Sebuah perusahaan pada bulan pertama memproduksi 8.000 unit barang dan menaikan produksinya tiap bulan sebanyak 300 unit. Jumlah barang yang diproduksi selama satu semester adalah…",
            answerA = "57.000 unit",
            answerB = "53.400 unit",
            answerC = "52.500 unit",
            answerD = "29.400 unit"
        ),TestQuestion(
            question = "6. Bayangan titik P(5,4) jika didilatasikan terhadap pusat (-2,3) dengan factor skala -4 adalah…",
            answerA = "(-30, -1)",
            answerB = "(-30, 7)",
            answerC = "(-14, -1)",
            answerD = "(-14, -7)"
        ),TestQuestion(
            question = "7. Nilai rata-rata ulangan matematika dari 20 siswa adalah 60. Jika ditambah dengan sejumlah siswa yang memiliki rata-rata 70, maka nilai rata-ratanya menjadi 62. Banyak siswa yang ditambahkan adalah…",
            answerA = "4",
            answerB = "5",
            answerC = "6",
            answerD = "7"
        ),TestQuestion(
            question = "8. Simpangan rata-rata dari data 4,5,6,7,8 adalah…",
            answerA = "6",
            answerB = "4",
            answerC = "1,2",
            answerD = "0,8"
        ),TestQuestion(
            question = "9. Dika menyimpan uang sebesar Rp.2.500.000,00 di bank. Pihak bank memberikan bunga tunggal 1,2% per bulan. Jumlah uang Dika setelah tiga tahun adalah…",
            answerA = "Rp.3.145.000,00",
            answerB = "Rp.3.340.000,00",
            answerC = "Rp.3.580.000,00",
            answerD = "Rp.3.720.000,00"
        ),TestQuestion(
            question = "10. Diketahui balok berukuran panjang 4 cm, lebar 3 cm, dan tinggi 12 cm. Panjang diagonal ruang balok tersebut adalah… cm.",
            answerA = "10",
            answerB = "11,5",
            answerC = "12,5",
            answerD = "13"
        ),
    )

    val STUDY_TIME_CATEGORIES = listOf(
        RangeTime("<2 Hours", 1),
        RangeTime("2-5 Hours", 2),
        RangeTime("5-10 Hours", 3),
        RangeTime(">10 Hours", 4),
    )

    val FREE_TIME_CATEGORIES = listOf(
        RangeTime("<2 Hours", 1),
        RangeTime("2-5 Hours", 2),
        RangeTime("5-8 Hours", 3),
        RangeTime("8-15 Hours", 4),
        RangeTime(">15 Hours", 5),
    )
}