package me.wedrowicz.codekata.anagrams

import spock.lang.Specification

import static me.wedrowicz.codekata.anagrams.AnagramsKt.anagrams

class PerfTest extends Specification {
    def "should load large set of words"() {
        given:
        def wordlist = new File("src/test/resources/wordlist.txt").text.split("\n")
        when:
        def anagrams = anagrams(wordlist.toList())
        then:
        anagrams.size() > 0
    }
}
