package cbedoy.stateshopapp.order

import cbedoy.stateshopapp.model.Order

sealed class OrderState {
    object None : OrderState()
    data class OrderChangedState(val order: Order) : OrderState()
}