package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository
import th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_autoload.PokemonAutoLoadViewModel

class PokemonDetailViewModelFactory constructor(private val repository: PokemonRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            PokemonDetailViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}