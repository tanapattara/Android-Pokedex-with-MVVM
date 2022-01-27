package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_autoload

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import th.ac.kku.cis.lab.pokedexmvvm.data.model.PokemonListItem
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository

class PokemonAutoLoadViewModel constructor(private val repository: PokemonRepository)  : ViewModel() {
    val pokemonListItem = MutableLiveData<List<PokemonListItem>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val endReached = MutableLiveData<Boolean>()
    private var curPokemon = 0
    private val LIMIT = 20
    var job: Job? = null

    fun loadPokemonListItem(){
        var curPokemonList = listOf<PokemonListItem>()
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            curPokemon = if(pokemonListItem.value == null) 0 else pokemonListItem.value!!.size
            if(curPokemon > 0)
                curPokemonList = pokemonListItem.value!!

            var offset = if(curPokemon == 0) 0 else curPokemon + LIMIT
            val result = repository.getPokemonsList(LIMIT, offset)

            withContext(Dispatchers.Main) {
                if(result.isSuccessful){
                    endReached.value = curPokemon * LIMIT >= result.body()!!.results.count()
                    val pokemonItem = result.body()!!.results.mapIndexed { index, result ->
                        val number = if(result.url.endsWith("/")){
                            result.url.dropLast(1).takeLastWhile { it.isDigit() }
                        }else{
                            result.url.takeLastWhile { it.isDigit() }
                        }
                        val url  = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListItem(result.name, url, number.toInt())
                    }
                    loading.value = false

                    curPokemonList += pokemonItem
                    pokemonListItem.postValue(curPokemonList)
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