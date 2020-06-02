package me.wedrowicz.codekata.checkout

interface PricingRule {
    val order: Int
    fun apply(items: MutableMap<Char, Int>): Int
}

class SingleUnitRule(private val sku: Char, private val unitPrice: Int): PricingRule {
    override val order = 0

    override fun apply(items: MutableMap<Char, Int>): Int = items.put(sku, 0)?.let { it * unitPrice } ?: 0
}

class MultipleUnitRule(private val sku: Char, private val packPrice: Int, private val packQuantity: Int): PricingRule {
    override val order = -1

    override fun apply(items: MutableMap<Char, Int>): Int {
        val count = items[sku] ?: 0
        items[sku] = count % packQuantity
        return count / packQuantity * packPrice
    }

}
