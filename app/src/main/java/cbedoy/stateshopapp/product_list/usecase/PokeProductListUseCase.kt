package cbedoy.stateshopapp.product_list.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import cbedoy.stateshopapp.product_list.repository.PokeProductListRepository
import cbedoy.stateshopapp.product_list.ProductListState
import cbedoy.stateshopapp.product_list.base.BaseProductUseCase

class PokeProductListUseCase (
        private val repository: PokeProductListRepository
): BaseProductUseCase(){

    override suspend fun getProducts(count: Int) = flow {
        emit(ProductListState.ShowLoader)

        repository.getProducts(count).collect { product ->
            products.add(product)

            productsDictionary["all"]?.add(product)

            filterProductsWith(
                    product = product,
                    criteria = {
                        product.type.contains("/")
                    },
                    criteriaToEvaluate = {
                        product.type.split("/")
                    }
            )

            emit(ProductListState.LoadedProducts(productList = products.distinctBy { it.name }))
            emit(ProductListState.LoadedTypes(types = productsDictionary.keys.toList()))
            emit(ProductListState.HideLoader)
        }
    }
}