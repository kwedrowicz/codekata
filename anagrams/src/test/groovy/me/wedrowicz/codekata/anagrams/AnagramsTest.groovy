package me.wedrowicz.codekata.anagrams

import spock.lang.Specification

import static me.wedrowicz.codekata.anagrams.AnagramsKt.anagrams

class AnagramsTest extends Specification {
    def "should place two anagrams in the same set"() {
        given:
        def words = ["kinship", "pinkish"]
        when:
        def anagrams = anagrams(words)
        then:
        anagrams.size() == 1
    }

    def "should place two words which are not anagrams in different sets"() {
        given:
        def words = ["foo", "baz"]
        when:
        def anagrams = anagrams(words)
        then:
        anagrams.size() == 2
    }
}
