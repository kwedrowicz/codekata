package me.wedrowicz.codekata.conflictingobjectives

import spock.lang.Specification

class CompoundWordsTest extends Specification {
    def "should find words compound from two other"() {
        given:
        def words = ["al", "bums", "albums"]
        def matcher = sub.newInstance(words)
        when:
        def compoundWords = matcher.findCompoundWords()
        then:
        compoundWords == ["albums"]
        where:
        sub << [ReadableWordMatcher, FastWordMatcher]
    }
}
