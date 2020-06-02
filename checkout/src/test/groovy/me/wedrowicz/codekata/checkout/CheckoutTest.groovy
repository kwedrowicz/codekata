package me.wedrowicz.codekata.checkout

import spock.lang.Specification

class CheckoutTest extends Specification {

    def static RULES = [
            new SingleUnitRule('A' as char, 50),
            new SingleUnitRule('B' as char, 30),
            new SingleUnitRule('C' as char, 20),
            new SingleUnitRule('D' as char, 15),
            new MultipleUnitRule('A' as char, 130, 3),
            new MultipleUnitRule('B' as char, 45, 2)
    ]

    def "should calculate total price of checkout"() {
        expect:
        price(products) == expectedPrice
        where:
        products || expectedPrice
        ""       || 0
        "A"      || 50
        "AB"     || 80
        "CDBA"   || 115
        "AA"     || 100
        "AAA"    || 130
        "AAAA"   || 180
        "AAAAA"  || 230
        "AAAAAA" || 260
        "AAAB"   || 160
        "AAABB"  || 175
        "AAABBD" || 190
        "DABABA" || 190
    }

    def "should calculate price during adding item into checkout"() {
        given:
        def co = new Checkout(RULES)
        expect:
        co.total() == 0
        co.scan("A" as char); co.total() == 50
        co.scan("B" as char); co.total() == 80
        co.scan("A" as char); co.total() == 130
        co.scan("A" as char); co.total() == 160
        co.scan("B" as char); co.total() == 175
    }


    private static def price(String goods) {
        def co = new Checkout(RULES)
        goods.split("").toList().stream().filter { it != "" }.each { co.scan(it as char) }
        co.total()
    }
}
