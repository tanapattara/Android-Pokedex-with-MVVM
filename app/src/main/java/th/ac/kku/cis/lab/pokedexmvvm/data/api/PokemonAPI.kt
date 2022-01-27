package th.ac.kku.cis.lab.pokedexmvvm.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import th.ac.kku.cis.lab.pokedexmvvm.data.api.model.PokemonAPIResult
import th.ac.kku.cis.lab.pokedexmvvm.data.api.model.PokemonInfo

interface PokemonAPI {
    @GET("pokemon/")
    suspend fun getPokemons(): Response<PokemonAPIResult>

    @GET("pokemon/")
    suspend fun getPokemonsList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset:Int
    ): Response<PokemonAPIResult>

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name:String
    ): Response<PokemonInfo>

    @GET("pokemon/")
    suspend fun getPokemonswithPaging(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset:Int
    ): Response<PokemonAPIResult>

    companion object {
        var BASE_URL = "https://pokeapi.co/api/v2/"
        fun getInstance() : PokemonAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(PokemonAPI::class.java)
        }
    }

}