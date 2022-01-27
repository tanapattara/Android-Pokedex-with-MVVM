package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_autoload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository
import th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_list.PokemonListViewModel

class PokemonAutoLoadViewModelFactory constructor(private val repository: PokemonRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PokemonAutoLoadViewModel::class.java)) {
            PokemonAutoLoadViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}