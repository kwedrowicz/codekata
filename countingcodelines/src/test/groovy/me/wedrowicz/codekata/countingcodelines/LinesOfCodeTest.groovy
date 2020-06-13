package me.wedrowicz.codekata.countingcodelines

import me.wedrowicz.codekata.countingcodelines.day1.LinesOfCodeKt
import spock.lang.Specification
import spock.lang.Unroll

class LinesOfCodeTest extends Specification {
    @Unroll
    def "should count lines of code of given files"() {
        given:
        def url = this.getClass().getClassLoader().getResource(filePath)
        when:
        def loc = fun(url)
        then:
        loc == expectedLinesOfCode
        where:
        fun | filePath || expectedLinesOfCode
        LinesOfCodeKt.&countLinesOfCode | "Dave.java" || 3
        me.wedrowicz.codekata.countingcodelines.day2.LinesOfCodeKt.&countLinesOfCode | "Dave.java" || 3
        LinesOfCodeKt.&countLinesOfCode | "Hello.java" || 5
        me.wedrowicz.codekata.countingcodelines.day2.LinesOfCodeKt.&countLinesOfCode | "Hello.java" || 5
        me.wedrowicz.codekata.countingcodelines.day2.LinesOfCodeKt.&countLinesOfCode | "StringWithComment.java" || 5
    }
}
