package devsh.si.spam.filter

class F50 {
    fun isSpam(message: String): Boolean {
        // Check for common spam phrases and patterns
        val spamPhrases: List<String> = listOf(
            "적중", "광고", "수익", "최대할인", "할인율", "이월상품", "우대", "적립",
            "월요일부터", "코드", "무료거부", "확인해주세요", "관찰해보시면", "빠르고 신속하게", "적립금",
            "참가하기", "입장하시여", "상한가", "바랍니다", "비밀번호", "월요일", "관전", "최신 종목 추천",
            "zxc.com", "명-가", "관전o", "https://me2.kr", "이번주"
        )

        // Check for URLs, phone numbers, and other common spam elements
        val urlPattern = Regex("https?://\\S+|bit\\.ly/\\S+|www\\.[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\.~#?&//=]*)|상승2019.com")
        val phonePattern = Regex("\\d{2,4}-\\d{2,4}-\\d{2,4}|\\d{10,13}")

        // Detect if the message has any spam elements
        for (phrase in spamPhrases) {
            if (message.contains(phrase)) {
                return true
            }
        }

        if (urlPattern.containsMatchIn(message) || phonePattern.containsMatchIn(message)) {
            return true
        }

        // If none of the spam elements are present, the message is not spam
        return false
    }
}