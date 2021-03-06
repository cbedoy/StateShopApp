package cbedoy.stateshopapp.dto

import com.google.gson.annotations.SerializedName

data class PokeProductResponse(
        val results: List<PokeProductResponseItem> = emptyList()
)

data class PokeProductResponseItem(
        val name: String = "",
        val url: String = ""
)

data class PokeItemResponse(
        val id: String? = "",
        val name: String? = "",
        val url: String? = "",
        val weight: Int? = 0,
        val sprites: PokeSpriteResponse? = PokeSpriteResponse(),
        val types: List<PokeTypesResponse>? = emptyList(),
        val moves: List<PokeMovesResponse>? = emptyList(),
        val abilities: List<PokeAbilitiesResponse>? = emptyList(),
)

data class PokeAbilitiesResponse(
        val ability: PokeAbilityResponse? = PokeAbilityResponse()
)

data class PokeAbilityResponse(
        val name: String? = ""
)

data class PokeSpriteResponse(
        @SerializedName("front_default")
        val avatar: String? = ""
)

data class PokeMovesResponse(
        val move: PokeMoveResponse? = PokeMoveResponse()
)

data class PokeMoveResponse(
        val name: String? = ""
)

data class PokeTypesResponse(
        val type: PokeTypeResponse? = PokeTypeResponse()
)

data class PokeTypeResponse(
        val name: String? = ""
)