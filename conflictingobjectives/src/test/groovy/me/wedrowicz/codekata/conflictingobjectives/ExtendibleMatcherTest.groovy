package me.wedrowicz.codekata.conflictingobjectives

import spock.lang.Specification

class ExtendibleMatcherTest extends Specification {
    def "should return empty array when element should contains more than elements size"() {
        given:
        def words = ["al", "bums", "albums"]
        def matcher = new ExtendibleStringMatcher(words)
        when:
        def compoundWords = matcher.findCompoundElements(4)
        then:
        compoundWords == []
    }

    def "should find element compound from two strings"() {
        given:
        def words = ["al", "bums", "albums"]
        def matcher = new ExtendibleStringMatcher(words)
        when:
        def compoundWords = matcher.findCompoundElements(2)
        then:
        compoundWords == ["albums"]
    }

    def "should find element compound from 3 numbers"() {
        given:
        def elements = [1,2,3,6]
        def matcher = new ExtendibleIntMatcher(elements)
        when:
        def compoundElements = matcher.findCompoundElements(3)
        then:
        compoundElements == [6]
    }

    def "should find element compund from 3 classes with plus method"() {
        given:
        def elements = [new PlusClass(1),new PlusClass(2),new PlusClass(3),new PlusClass(6)]
        def matcher = new ExtendibleClassMatcher(elements)
        when:
        def compoundElements = matcher.findCompoundElements(3)
        then:
        compoundElements == [new PlusClass(6)]

    }
}
