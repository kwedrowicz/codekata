package me.wedrowicz.codekata.countingcodelines

import java.net.URL

fun countLinesOfCode(url: URL): Int {
    val text = url.readText()
    var loc = 0
    var countedCharsInThisLine = 0
    var quations = false
    var i = 0

    val whitespaces = listOf(' ')

    while (i < text.length) {
        if (text[i] == '"') {
            quations = !quations
            i++
            continue
        }

        if (text[i] == '/' && text[i + 1] == '*' && !quations) {
            i += 2
            while (!(text[i] == '*' && text[i + 1] == '/')) {
                i++
            }
            i += 2
            continue
        }

        if (text[i] == '/' && text[i + 1] == '/' && !quations) {
            while (text[i] != '\n') {
                i++
            }
            continue
        }

        if (text[i] == '\n') {
            if (countedCharsInThisLine > 0) {
                loc++
                countedCharsInThisLine = 0
            }
            i++
            continue
        }

        if (!whitespaces.contains(text[i])) {
            countedCharsInThisLine++
        }
        i++
    }

    return loc
}
