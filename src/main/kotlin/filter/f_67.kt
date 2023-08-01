package devsh.si.spam.filter

class F67 {
    fun isSpam(message: String): Boolean {
        // Check for excessive use of special characters
        val specialCharCount = Regex("[!@#$%^&*()_=+\\[\\]{}<>:;\"'|\\\\,.?]").findAll(message).count()
        if (specialCharCount.toDouble() / message.length > 0.1) {
            return true
        }

        // Check for presence of financial numbers and shortening of amounts
        if (Regex("\\d{1,3}(,|\\.)\\d{3}").containsMatchIn(message) || Regex("\\d{1,3}(만원|천원)으로").containsMatchIn(message)) {
            return true
        }

        // Check for presence of URLs containing suspicious domain names
        val suspiciousDomains = listOf("bit.ly", "me2.kr", "han.gl", "openkakao.")
        for (domain in suspiciousDomains) {
            if (domain in message.toLowerCase()) {
                return true
            }
        }

        // Check for excessive use of up arrow character
        val upArrowCount = message.count { it == '↑' }
        if (upArrowCount.toDouble() / message.length > 0.05) {
            return true
        }

        return false
    }
}