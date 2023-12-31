package devsh.si.spam.filter

class F3 {
    fun isSpam(message: String): Boolean {
        val spamKeywords = arrayOf("광고", "추천주", "공개", "주주", "무료체험", "상한가", "이윤", "마감", "VIP", "빠르게", "현황", "me2.kr", "클릭", "정보방", "지난주", "dokdo.in", "안녕하세요", "알려드립니다", "단타정보", "수익률", "운영", "수익", "openkakao.io", "무료거부", "사활", "https://")

        val content = message.toLowerCase()
        for (keyword in spamKeywords) {
            if (keyword.toLowerCase() in content) {
                return true
            }
        }

        return false
    }
}