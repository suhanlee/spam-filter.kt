package devsh.si.spam.filter
import java.util.regex.Pattern

class F46 {
    fun isSpam(message: String): Boolean {
        // Check for spam keywords
        val spamKeywords = listOf("광고", "핫딜", "편지함으로", "지금 바로", "무료거부", "지원금", "안전거래", "입장코드", "추천주",
            "수익", "주식", "특별한 혜택")

        for (keyword in spamKeywords) {
            if (message.contains(keyword)) {
                return true
            }
        }

        // Check for url patterns
        val urlPattern1 = Pattern.compile("https?://\\S+")
        val urlPattern2 = Pattern.compile("www\\.\\S+")
        val urlMatch1 = urlPattern1.matcher(message).find()
        val urlMatch2 = urlPattern2.matcher(message).find()

        if (urlMatch1 || urlMatch2) {
            if ("원" in message || "계약" in message || "시작" in message || "특별" in message) {
                return true
            }
        }

        // Check for money and percentage patterns
        val moneyPattern = Pattern.compile("\\d{1,3}(,\\d{3})*(\\.\\d{2})?원")
        val moneyMatch = moneyPattern.matcher(message).find()
        val percentagePattern = Pattern.compile("\\d{1,3}(\\.\\d{1,2})?%")
        val percentageMatch = percentagePattern.matcher(message).find()

        return moneyMatch && percentageMatch
    }
}