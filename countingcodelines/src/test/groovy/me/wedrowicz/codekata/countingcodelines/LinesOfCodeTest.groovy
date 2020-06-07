package me.wedrowicz.codekata.countingcodelines

import spock.lang.Specification
import spock.lang.Unroll

import static me.wedrowicz.codekata.countingcodelines.LinesOfCodeKt.countLinesOfCode

class LinesOfCodeTest extends Specification {
    @Unroll
    def "should count lines of code of given files"() {
        given:
        def url = this.getClass().getClassLoader().getResource(filePath)
        when:
        def loc = countLinesOfCode(url)
        then:
        loc == expectedLinesOfCode
        where:
        filePath || expectedLinesOfCode
        "Dave.java" || 3
        "Hello.java" || 5
    }
}
