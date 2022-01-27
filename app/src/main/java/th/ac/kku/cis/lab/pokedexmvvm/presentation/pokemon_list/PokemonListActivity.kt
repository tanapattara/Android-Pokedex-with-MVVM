package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import th.ac.kku.cis.lab.pokedexmvvm.data.api.PokemonAPI
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository
import th.ac.kku.cis.lab.pokedexmvvm.databinding.ActivityMainBinding
import th.ac.kku.cis.lab.pokedexmvvm.databinding.ActivityPokemonListBinding

class PokemonListActivity : AppCompatActivity() {

    lateinit var viewModel: PokemonListViewModel
    private val adapter = PokemonListAdapter()
    lateinit var binding: ActivityPokemonListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_pokemon_list)
        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = PokemonAPI.getInstance()
        val mainRepository = PokemonRepository(retrofitService)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, PokemonListViewModelFactory(mainRepository)).get(
            PokemonListViewModel::class.java)
        /*
        viewModel.pokemonList.observe(this, {
            adapter.setPokemonData(it)
        })
        */
        viewModel.pokemonListItem.observe(this) {
            adapter.setPokemonItemData(it)
        }
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        //viewModel.getAllPokemon()
        viewModel.loadPokemonListItem()
    }
}