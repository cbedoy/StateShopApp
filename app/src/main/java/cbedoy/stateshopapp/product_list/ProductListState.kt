package cbedoy.stateshopapp.product_list

import cbedoy.stateshopapp.model.Product
import zendesk.chat.ChatConfiguration
import zendesk.messaging.Engine

sealed class ProductListState {
    object ShowLoader : ProductListState()
    object HideLoader : ProductListState()
    data class LoadedProducts(val productList: List<Product>) : ProductListState()
    data class LoadedTypes(val types: List<String>): ProductListState()
    data class ShowSupport(
            val configuration: ChatConfiguration,
            val engines: List<Engine>,
            val title: String,
            val description: String = ""
    ): ProductListState()
}