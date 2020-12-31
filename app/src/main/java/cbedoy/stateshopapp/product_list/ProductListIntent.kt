package cbedoy.stateshopapp.product_list

import cbedoy.stateshopapp.ProductGenerator
import cbedoy.stateshopapp.model.Product

sealed class ProductListIntent {
    object LoadProducts: ProductListIntent()
    object RequestSupport: ProductListIntent()
    data class ChooseProductTypeIntent(val productType: ProductGenerator.ProductType): ProductListIntent()
    data class AddProduct(val product: Product): ProductListIntent()
    data class FilterProduct(val type: String): ProductListIntent()
}