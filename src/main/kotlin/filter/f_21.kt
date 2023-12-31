package devsh.si.spam.filter

class F21 {
    fun isSpam(message: String): Boolean {
        val re = Regex("\\W")
        val spamIndicators = listOf(
            "상한가",
            "무료거부",
            "수익률",
            "비트코인",
            "투자",
            "예정",
            "단독",
            "체험",
            "연소득",
            "선물거래",
            "시초가",
            "확률",
            "실적",
            "텔레그램",
            "마감"
        )

        return spamIndicators.any { indicator -> message.contains(indicator) } && re.findAll(message).count() / message.length.toDouble() > 0.1
    }
}