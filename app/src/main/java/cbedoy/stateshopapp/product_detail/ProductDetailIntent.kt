package cbedoy.stateshopapp.product_detail

import cbedoy.stateshopapp.model.Product

sealed class ProductDetailIntent {
    data class AddProductIntent(val product: Product): ProductDetailIntent()
}