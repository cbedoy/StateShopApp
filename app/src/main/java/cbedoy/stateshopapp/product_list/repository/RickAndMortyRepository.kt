package cbedoy.stateshopapp.product_list.repository

import kotlinx.coroutines.flow.flow
import cbedoy.stateshopapp.model.Product
import cbedoy.stateshopapp.product_list.service.RickAndMortyService
import java.util.*
import kotlin.random.Random

class RickAndMortyRepository (
        private val service: RickAndMortyService
){
    suspend fun getProducts() = flow {
        service.getProducts().results.map { character ->
            emit(Product(
                    name = character.name?:"",
                    image = character.image?:"",
                    amount = (Random.nextFloat() % 100) * 10,
                    type = listOf(
                            character.species ?: "",
                            character.gender ?: "",
                            character.status ?: ""
                    ).map { it }.joinToString("/"){
                        it.capitalize(Locale.ROOT)
                    }
            ))
        }
    }
}