package devsh.si.spam.filter
import java.util.regex.Pattern

class F49 {

    fun isSpam(message: String): Boolean {
        val spamPatterns = arrayOf(
            "\\d{1,2}%", // Percentage discounts
            "코드[:\\:]?\\w*",
            "무료거부", // Unsubscribe keyword in Korean
            "(http(s)?://)?(bit\\.ly|me2\\.kr|vo\\.la|dokdo\\.in|tdeal\\.kr|" +
                    "openkak(talk)?\\.at|kakaos?\\.co|buly\\.kr|(vvd\\.bz))\\/\\S*", // Spam URL shorteners
            "=BBQ\\+피자\\+활쿱", // Spam message
            "(광고)" // Advertising indicator
        )

        // Combine all spam patterns into a single regex pattern
        // TODO: 정규식 에러 코드
//        val spamPatternRe = Pattern.compile(spamPatterns.joinToString(separator = "|"), Pattern.CASE_INSENSITIVE)

        return false
//        return spamPatternRe.matcher(message).find()
    }
}