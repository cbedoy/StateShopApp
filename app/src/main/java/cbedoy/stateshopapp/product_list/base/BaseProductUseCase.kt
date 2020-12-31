package cbedoy.stateshopapp.product_list.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import cbedoy.stateshopapp.model.Product
import cbedoy.stateshopapp.product_list.ProductListState

typealias Criteria = () -> Boolean
typealias CriteriaToEvaluate = () -> List<String>

abstract class BaseProductUseCase {
    private var currentProducts = mutableListOf<Product>()
    private var productsByType = mutableMapOf<String, MutableList<Product>>(
            "all" to mutableListOf()
    )

    val products : MutableList<Product>
        get() = currentProducts

    val productsDictionary
        get() = productsByType

    abstract suspend fun getProducts(count: Int): Flow<ProductListState>

    open fun filterProductsWith(type: String) = flow {
        emit(ProductListState.ShowLoader)

        emit(ProductListState.LoadedProducts(productList = productsByType[type]?: emptyList()))

        emit(ProductListState.HideLoader)
    }


    fun filterProductsWith(product: Product, criteria: Criteria, criteriaToEvaluate: CriteriaToEvaluate){
        if (criteria()){
            val types = criteriaToEvaluate()
            types.forEach { type ->
                if (productsDictionary.containsKey(type)){
                    val productList = productsDictionary[type]
                    productList?.add(product)
                }else{
                    val productList = mutableListOf(product)
                    productsDictionary.put(type, productList)
                }
            }
        }else{
            val productList = mutableListOf(product)
            productsDictionary[product.type] = productList
        }
    }
}