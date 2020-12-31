package cbedoy.stateshopapp.order

import androidx.lifecycle.ViewModel
import cbedoy.stateshopapp.model.Product
import cbedoy.stateshopapp.order.OrderState.OrderChangedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OrderViewModel (
        private val userCase: OrderUseCase
): ViewModel(){

    private val _orderState = MutableStateFlow<OrderState>(OrderState.None)
    val orderState : StateFlow<OrderState> get() = _orderState

    fun addProduct(product: Product){
        _orderState.value = OrderChangedState(userCase.addProduct(product))
    }

    fun removeProduct(product: Product){
        _orderState.value = OrderChangedState(userCase.removeProduct(product))
    }
}