package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository

class PokemonListViewModelFactory constructor(private val repository: PokemonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PokemonListViewModel::class.java)) {
            PokemonListViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}