package th.ac.kku.cis.lab.pokedexmvvm.data.repository

import th.ac.kku.cis.lab.pokedexmvvm.data.api.PokemonAPI

class PokemonRepository constructor(private val pokemonAPI: PokemonAPI) {
    suspend fun getPokemons() = pokemonAPI.getPokemons()
}