package cbedoy.stateshopapp.checkout

sealed class CheckoutIntent {
    object LoadOrder: CheckoutIntent()
    object ConfirmOrder : CheckoutIntent()
}