package th.ac.kku.cis.lab.pokedexmvvm.data.api.model

data class PokemonAPIResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)