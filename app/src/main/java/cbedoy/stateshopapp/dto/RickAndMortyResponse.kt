package cbedoy.stateshopapp.dto

data class RickAndMortyResponse(
        val results: List<CharacterResponse> = emptyList()
)

data class CharacterResponse(
        val id: String? = "",
        val name: String? = "",
        val status: String? = "",
        val gender: String? = "",
        val image: String? = "",
        val species: String? = ""
)