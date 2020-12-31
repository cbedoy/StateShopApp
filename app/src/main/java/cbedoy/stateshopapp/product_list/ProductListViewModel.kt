package cbedoy.stateshopapp.product_list

import kotlinx.coroutines.CoroutineScope
import cbedoy.stateshopapp.order.OrderUseCase
import cbedoy.stateshopapp.product_list.base.BaseProductListViewModel
import cbedoy.stateshopapp.product_list.usecase.PokeProductListUseCase
import cbedoy.stateshopapp.support.SupportUseCase

class ProductListViewModel(
    supportUseCase: SupportUseCase,
    useCase: PokeProductListUseCase,
    coroutineScope: CoroutineScope,
    orderUseCase: OrderUseCase
): BaseProductListViewModel(
        orderUseCase = orderUseCase,
        supportUseCase = supportUseCase,
        coroutineScope = coroutineScope,
        useCase = useCase) {

}
