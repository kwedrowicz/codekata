package me.wedrowicz.codekata.checkout

class Checkout(pricingRules: List<PricingRule>) {
    private val pricingRules = pricingRules.sortedBy { it.order }
    private val items: MutableMap<Char, Int> = mutableMapOf()

    fun scan(item: Char) {
        items.merge(item, 1, Int::plus)
    }

    fun total(): Int {
        val itemsCopy = items.toMutableMap()
        var price = 0

        pricingRules.forEach {
            price += it.apply(itemsCopy)
        }

        return price
    }
}
