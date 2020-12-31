package cbedoy.stateshopapp.model

import cbedoy.stateshopapp.formatAmount

data class ProductGroup(
    val products: List<Product> = emptyList()
){
    val description: String
        get() = "${products.size} x ${products.firstOrNull()?.name?:""}"

    val amount: String
        get() = products.sumByDouble { it.amount.toDouble() }.formatAmount()

    val dummyImage: String?
        get() = products.firstOrNull()?.thumbnail
}