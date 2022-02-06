package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import th.ac.kku.cis.lab.pokedexmvvm.data.api.model.PokemonInfo
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository
import th.ac.kku.cis.lab.pokedexmvvm.databinding.ActivityPokemonDetailBinding

class PokemonDetailViewModel (private val repository: PokemonRepository)  : ViewModel() {

    val pokemonInfo = MutableLiveData<PokemonInfo>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null

    fun loadPokemonInfo(pokemonId: String){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val result = repository.getPokemonsInfo(pokemonId)
            withContext(Dispatchers.Main) {
                if(result.isSuccessful){
                    pokemonInfo.postValue(result.body()!!)
                    loading.value = false
                }else{
                    onError("Error : ${result.message()}")
                }
            }
        }
    }

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
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