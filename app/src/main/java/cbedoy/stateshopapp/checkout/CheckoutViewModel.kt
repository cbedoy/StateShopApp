package cbedoy.stateshopapp.checkout

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import cbedoy.stateshopapp.order.OrderUseCase

class CheckoutViewModel(
    private val orderUseCase: OrderUseCase
): ViewModel(){
    private val _state = MutableStateFlow<CheckoutState>(CheckoutState.LoadingState)
    val state : StateFlow<CheckoutState> get() = _state

    fun handleIntent(intent: CheckoutIntent){
        when(intent){
            is CheckoutIntent.LoadOrder -> {
                _state.value = CheckoutState.ShowOrderState(order = orderUseCase.getCurrentOrder())
            }
            is CheckoutIntent.ConfirmOrder -> {

            }
        }
    }
}