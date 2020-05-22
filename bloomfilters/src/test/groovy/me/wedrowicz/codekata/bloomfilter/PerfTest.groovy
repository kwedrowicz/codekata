package me.wedrowicz.codekata.bloomfilter

import spock.lang.Specification
import spock.lang.Unroll

class PerfTest extends Specification {
    @Unroll
    def "should load large words list using #sub.simpleName implementation"() {
        given:
        def wordlist = new File("src/test/resources/wordlist.txt").text.split("\n")
        def bloomFilter = sub.newInstance(1_000_000, 5)
        when:
        wordlist.each { bloomFilter.add(it) }
        then:
        bloomFilter.contains(wordlist[0])
        where:
        sub << [BloomFilterArrayImplementation, BloomFilterBitSetImplementation, BloomFilterBitOperatorImplementation]
    }
}
