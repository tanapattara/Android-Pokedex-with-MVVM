package th.ac.kku.cis.lab.pokedexmvvm.data.repository

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import th.ac.kku.cis.lab.pokedexmvvm.data.api.PokemonAPI
import th.ac.kku.cis.lab.pokedexmvvm.data.api.model.PokemonAPIResult

class PokemonRepository constructor(private val pokemonAPI: PokemonAPI) {
    suspend fun getPokemons() = pokemonAPI.getPokemons()

    suspend fun getPokemonsList(limit: Int, offset: Int) = pokemonAPI.getPokemonsList(limit, offset)
    suspend fun getPokemonsInfo(name:String) = pokemonAPI.getPokemonInfo(name)
}