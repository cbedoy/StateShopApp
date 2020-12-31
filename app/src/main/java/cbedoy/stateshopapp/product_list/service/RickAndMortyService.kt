package cbedoy.stateshopapp.product_list.service

import retrofit2.http.GET
import cbedoy.stateshopapp.dto.RickAndMortyResponse

interface RickAndMortyService {
    @GET("character")
    suspend fun getProducts(): RickAndMortyResponse
}