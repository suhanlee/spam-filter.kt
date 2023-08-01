package devsh.si.spam

import java.io.File
import java.nio.file.Files

data class Response(
    val input_texts: List<String>,
    val voted_spam_fraction: List<Double>,
    val decisions: List<Boolean>,
    val num_functions: Int
)

typealias CheckerFunction = (String) -> Boolean

fun tandemExecution(functions: List<CheckerFunction>, txt: String): Double {
    val results = functions.map { func -> if (func(txt)) 1.0 else 0.0 }
    return results.average()
}

fun preprocess(texts: List<String>): List<String> {
    val headers = listOf("[Web발신]", "[국외발신]", "[국제발신]")
    val headersPattern = headers.joinToString(separator = "|") { Regex.escape(it) }
    val urlPattern = "https?://(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_+.~#?&//=]*)"

    val processedTexts = texts.map { txt ->
        txt.replace(headersPattern.toRegex(), "").replace(urlPattern.toRegex(), "")
    }

    return processedTexts
}

fun filterFunc(
    inputTexts: List<String>,
    decisionOnly: Boolean = false,
    threshHold: Double = 0.35 // affects performance. do not configure this.
): Any {
    // load checkers
    val indivisualCheckers = mutableListOf<CheckerFunction>()
    val classNames = Array(72) { index -> "devsh.si.spam.filter.F$index" }

    classNames.forEach {
        val clazz = Class.forName(it)
        val instance = clazz.getDeclaredConstructor().newInstance()
        val checkerFunction = Class.forName(it)
            .getDeclaredMethod("isSpam", String::class.java)
            .let { method ->
                { txt: String -> method.invoke(instance, txt) as Boolean }
            }
        indivisualCheckers.add(checkerFunction)
    }

    val pInputTexts = preprocess(inputTexts)
    val votedSpamRatio = pInputTexts.map { txt -> tandemExecution(indivisualCheckers, txt) }
    val decisions = votedSpamRatio.map { r -> r >= threshHold }
    val numFunctions = indivisualCheckers.size

    return if (decisionOnly) {
        decisions
    } else {
        val response = Response(
            input_texts = inputTexts,
            voted_spam_fraction = votedSpamRatio,
            decisions = decisions,
            num_functions = numFunctions
        )
        return response
    }
}

fun anyTrue(arr: Array<Boolean>): Boolean {
    for (element in arr) {
        if (element) {
            return true
        }
    }
    return false
}

fun filterFuncDecisionOnly(inputTexts: List<String>, threshHold: Double = 0.35): Boolean {
    // load checkers
    val indivisualCheckers = mutableListOf<CheckerFunction>()
    val classNames = Array(72) { index -> "devsh.si.spam.filter.F$index" }

    classNames.forEach {
        val clazz = Class.forName(it)
        val instance = clazz.getDeclaredConstructor().newInstance()
        val checkerFunction = Class.forName(it)
            .getDeclaredMethod("isSpam", String::class.java)
            .let { method ->
                { txt: String -> method.invoke(instance, txt) as Boolean }
            }
        indivisualCheckers.add(checkerFunction)
    }

    val pInputTexts = preprocess(inputTexts)
    val votedSpamRatio = pInputTexts.map { txt -> tandemExecution(indivisualCheckers, txt) }
    val decisions = votedSpamRatio.map { r -> r >= threshHold }
    val trueDecisions = decisions.filter { d -> d }
    return trueDecisions.isNotEmpty()
}

fun prettyPrint(obj: Any): String {
    val properties = obj.javaClass.declaredFields
        .map { it.isAccessible = true; it.name to it.get(obj) }
        .toMap()
    val stringBuilder = StringBuilder()
    properties.forEach { (name, value) ->
        stringBuilder.appendLine("$name=$value")
    }
    return stringBuilder.toString()
}

fun main(args: Array<String>) {
    val inputMessageCsv = "3_inputmsgs.csv"
    val file = File(inputMessageCsv)
    val inputTexts = Files.readAllLines(file.toPath())

    println(prettyPrint(filterFunc(inputTexts = inputTexts)))
    println(filterFuncDecisionOnly(inputTexts = inputTexts))
}