package cbedoy.stateshopapp.product_detail

import androidx.lifecycle.ViewModel
import cbedoy.stateshopapp.order.OrderUseCase

class ProductDetailViewModel(
    private val orderUseCase: OrderUseCase
) : ViewModel(){
    fun handleIntent(intent: ProductDetailIntent){
        when(intent){
            is ProductDetailIntent.AddProductIntent -> {
                orderUseCase.addProduct(intent.product)
            }
        }
    }
}