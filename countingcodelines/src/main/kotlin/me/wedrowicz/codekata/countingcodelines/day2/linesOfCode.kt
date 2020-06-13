package me.wedrowicz.codekata.countingcodelines.day2

import java.net.URL
import kotlin.text.RegexOption.DOT_MATCHES_ALL

fun countLinesOfCode(url: URL): Int = url.readText().trimComments().trimEmptyLines().countLines()

private fun String.trimComments() = """//[^\n]*|/\*.*?\*/""".toRegex(DOT_MATCHES_ALL).replace(this, "")
private fun String.trimEmptyLines() = """\n\s*\n""".toRegex().replace(this, "\n").trim()
private fun String.countLines() = this.count { it == '\n' } + 1
