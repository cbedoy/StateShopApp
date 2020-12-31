package cbedoy.stateshopapp.product_list.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import cbedoy.stateshopapp.order.OrderUseCase
import cbedoy.stateshopapp.product_list.ProductListIntent
import cbedoy.stateshopapp.product_list.ProductListState
import cbedoy.stateshopapp.support.SupportState
import cbedoy.stateshopapp.support.SupportUseCase

abstract class BaseProductListViewModel(
    private val orderUseCase: OrderUseCase,
    private val supportUseCase: SupportUseCase,
    private val useCase: BaseProductUseCase,
    private val coroutineScope: CoroutineScope
): ViewModel() {

    private val _productListState = MutableStateFlow<ProductListState>(ProductListState.ShowLoader)
    val productListState : StateFlow<ProductListState> get() = _productListState

    private val _supportState = MutableStateFlow<SupportState>(SupportState.None)
    val supportState: StateFlow<SupportState> get() = _supportState

    fun handleIntent(intent: ProductListIntent){
        when(intent){
            is ProductListIntent.ChooseProductTypeIntent -> {
                //chooseItem(productType = intent.productType)
            }
            is ProductListIntent.AddProduct -> {
                orderUseCase.addProduct(intent.product)
            }
            ProductListIntent.LoadProducts -> {
                loadProducts()
            }
            is ProductListIntent.FilterProduct -> {
                coroutineScope.launch {
                    useCase.filterProductsWith(type = intent.type).collect { state ->
                        _productListState.value = state
                    }
                }
            }
            is ProductListIntent.RequestSupport -> {
                coroutineScope.launch {
                    supportUseCase.userWantsSupport().collect { state ->
                        when(state){
                            is SupportState.None -> TODO()
                            is SupportState.StartChatWith -> {
                                _productListState.value = ProductListState.ShowSupport(
                                    engines = state.engines,
                                    title = state.title,
                                    configuration = state.configuration
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadProducts(count: Int = 600) {
        coroutineScope.launch {
            useCase.getProducts(count).collect { state ->
                _productListState.value = state
            }
        }
    }
}
