package cbedoy.stateshopapp.product_list.repository

import kotlinx.coroutines.flow.flow
import cbedoy.stateshopapp.dto.PokeItemResponse
import cbedoy.stateshopapp.model.Product
import cbedoy.stateshopapp.product_list.service.PokeProductListService
import kotlin.random.Random

class PokeProductListRepository (
        private val service: PokeProductListService
){
    suspend fun getProducts(count: Int) = flow {
        service.getProducts(count).results.forEach { responseItem ->
            responseItem.url.split("/").lastOrNull { it.isNotEmpty() }?.let { productId ->
                val detail = service.getProductDetail(productId)
                with(detail){
                    emit(Product(
                            name = name?.capitalize()?:"",
                            image = sprites?.avatar?:"",
                            type = types?.joinToString(separator = "/", transform = {
                                it.type?.name?:""
                            })?:"",
                            description = prepareMoves(detail)?:"",
                            secondaryDescription = prepareAbilities(detail)?:"",
                            amount = (Random.nextFloat() % 100) * 10
                    ))
                }
            }
        }
    }

    private fun prepareMoves(productDetail: PokeItemResponse) =
        productDetail.moves?.joinToString(
                separator = "\n",
                transform = {
                    "- ${it.move?.name?:""}"
                })

    private fun prepareAbilities(productDetail: PokeItemResponse) =
        productDetail.abilities?.joinToString(
                separator = "\n",
                transform = {
                    "- ${it.ability?.name?:""}"
                })

}