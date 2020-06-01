package me.wedrowicz.codekata.conflictingobjectives

import spock.lang.Specification
import spock.lang.Unroll

class PerfTest extends Specification {
    @Unroll
    def "should load small words list using #sub.simpleName implementation"() {
        given:
        def wordlist = new File("src/test/resources/wordlist.txt").text.split("\n").toList().subList(0, 1000)
        def matcher = sub.newInstance(wordlist)
        when:
        def compoundWords = matcher.findCompoundWords()
        then:
        compoundWords.size() > 0
        where:
        sub << [ReadableWordMatcher, FastWordMatcher, ExtendibleStringMatcher]
    }

    @Unroll
    def "should load large words list using #sub.simpleName implementation"() {
        given:
        def wordlist = new File("src/test/resources/wordlist.txt").text.split("\n").toList()
        def matcher = sub.newInstance(wordlist)
        when:
        def compoundWords = matcher.findCompoundWords()
        then:
        compoundWords.size() > 0
        where:
        sub << [FastWordMatcher]
    }
}
