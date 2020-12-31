package cbedoy.stateshopapp.product_list.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import cbedoy.stateshopapp.model.Product
import cbedoy.stateshopapp.product_list.ProductListState
import cbedoy.stateshopapp.product_list.repository.RickAndMortyRepository
import cbedoy.stateshopapp.product_list.base.BaseProductUseCase

class RickAndMortyUseCase (
        private val repository: RickAndMortyRepository
): BaseProductUseCase() {
    override suspend fun getProducts(count: Int) = flow {
        emit(ProductListState.ShowLoader)

        repository.getProducts().collect { product ->
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

            emit(ProductListState.LoadedProducts(productList = products.distinctBy { it.name } as MutableList<Product>))
            emit(ProductListState.LoadedTypes(types = productsDictionary.keys.toList()))

            emit(ProductListState.HideLoader)
        }
    }
}