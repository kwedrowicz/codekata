package me.wedrowicz.codekata.bloomfilter

import spock.lang.Specification
import spock.lang.Unroll

class BloomFilterTest extends Specification {

    @Unroll
    def "should contains added element using #sub.simpleName implementation"() {
        given:
        def word = "foo"
        def bloomFilter = sub.newInstance(1_000_000, 5)
        when:
        bloomFilter.add(word)
        then:
        bloomFilter.contains(word)
        where:
        sub << [BloomFilterArrayImplementation, BloomFilterBitSetImplementation, BloomFilterBitOperatorImplementation]
    }

    @Unroll
    def "should not contains other element using #sub.simpleName implementation"() {
        given:
        def word = "foo"
        def bloomFilter = sub.newInstance(1_000_000, 5)
        when:
        bloomFilter.add(word)
        then:
        !bloomFilter.contains("baz")
        where:
        sub << [BloomFilterArrayImplementation, BloomFilterBitSetImplementation, BloomFilterBitOperatorImplementation]
    }
}
