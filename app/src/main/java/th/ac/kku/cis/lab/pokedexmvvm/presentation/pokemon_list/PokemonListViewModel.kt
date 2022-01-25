package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import th.ac.kku.cis.lab.pokedexmvvm.data.api.model.Result
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository
import th.ac.kku.cis.lab.pokedexmvvm.data.model.PokemonListItem
import kotlinx.coroutines.*

class PokemonListViewModel constructor(private val repository: PokemonRepository)  : ViewModel() {
    val pokemonList = MutableLiveData<List<Result>>()
    val pokemonListItem = MutableLiveData<List<PokemonListItem>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val endReached = MutableLiveData<Boolean>()
    private var curPage = 0
    private val PAGE_SIZE = 20
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
    fun loadPokemonPaginated(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val result = repository.getPokemonsList(PAGE_SIZE, curPage * PAGE_SIZE)
            withContext(Dispatchers.Main) {
                if(result.isSuccessful){
                    endReached.value = curPage * PAGE_SIZE >= result.body()!!.results.count()
                    val pokemonItem = result.body()!!.results.mapIndexed { index, result ->
                        val number = if(result.url.endsWith("/")){
                            result.url.dropLast(1).takeLastWhile { it.isDigit() }
                        }else{
                            result.url.takeLastWhile { it.isDigit() }
                        }
                        val url  = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListItem(result.name, url, number.toInt())
                    }
                    curPage++

                    loading.value = false
                    pokemonListItem.postValue(pokemonItem)
                }else{
                    onError("Error : ${result.message()}")
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