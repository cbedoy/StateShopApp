package cbedoy.stateshopapp.product_list.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import cbedoy.stateshopapp.dto.PokeItemResponse
import cbedoy.stateshopapp.dto.PokeProductResponse

interface PokeProductListService {
    @GET("v2/pokemon")
    suspend fun getProducts(@Query("limit")limit: Int): PokeProductResponse

    @GET("v2/pokemon/{productId}")
    suspend fun getProductDetail(@Path("productId") productId: String): PokeItemResponse
}