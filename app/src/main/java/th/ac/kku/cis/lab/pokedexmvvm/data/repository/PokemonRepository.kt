package th.ac.kku.cis.lab.pokedexmvvm.data.repository

import th.ac.kku.cis.lab.pokedexmvvm.data.api.PokemonAPI

class PokemonRepository constructor(private val pokemonAPI: PokemonAPI) {
    suspend fun getPokemons() = pokemonAPI.getPokemons()

    suspend fun getPokemonsList(limit: Int, offset: Int) = pokemonAPI.getPokemonsList(limit, offset)
    suspend fun getPokemonsInfo(name:String) = pokemonAPI.getPokemonInfo(name)
}