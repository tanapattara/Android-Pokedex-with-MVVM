package th.ac.kku.cis.lab.pokedexmvvm.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import th.ac.kku.cis.lab.pokedexmvvm.data.model.PokemonAPIResult

interface PokemonAPI {
    @GET("pokemon/")
    suspend fun getPokemons(): PokemonAPIResult

    companion object {
        var BASE_URL = "https://pokeapi.co/api/v2/"
        fun create() : PokemonAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(PokemonAPI::class.java)
        }
    }

}