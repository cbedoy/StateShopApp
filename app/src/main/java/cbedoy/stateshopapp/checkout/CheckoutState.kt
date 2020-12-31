package cbedoy.stateshopapp.checkout

import cbedoy.stateshopapp.model.Order

sealed class CheckoutState {
    object LoadingState : CheckoutState()
    data class ShowOrderState(val order: Order) : CheckoutState()
}