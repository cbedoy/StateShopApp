package cbedoy.stateshopapp.model

import cbedoy.stateshopapp.CONFIG_FEE
import cbedoy.stateshopapp.CONFIG_TIP
import java.util.*

data class Order(
    val products: MutableList<Product> = mutableListOf(),
    val groupProducts: MutableList<ProductGroup> = mutableListOf(),
    val date: Date = Date(),
    val breakdownPrice: MutableList<BreakdownPrice> = mutableListOf()
){
    val total : Double
        get() = products.sumByDouble { it.amount.toDouble() }
    val fee : Double
        get() = total * CONFIG_FEE
    val tip: Double
        get() = total * CONFIG_TIP
    val totalToPayWithTip: Double
        get() = total + fee + tip
    val totalToPayWithoutTip: Double
        get() = total + fee
}