package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import th.ac.kku.cis.lab.pokedexmvvm.data.api.model.Result
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository
import kotlinx.coroutines.*

class PokemonListViewModel constructor(private val repository: PokemonRepository)  : ViewModel() {
    val pokemonList = MutableLiveData<List<Result>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    fun getAllPokemon() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getPokemons()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val response_data = response.body()
                    pokemonList.postValue(response_data!!.results)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }
    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}