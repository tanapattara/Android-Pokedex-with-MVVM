package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_autoload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import th.ac.kku.cis.lab.pokedexmvvm.data.api.PokemonAPI
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository
import th.ac.kku.cis.lab.pokedexmvvm.databinding.ActivityPokemonListBinding

class PokemonAutoLoadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_pokemon_auto_load)
        val adapter = PokemonAutoLoadAdapter()
        val binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter

        val retrofitService = PokemonAPI.getInstance()
        val mainRepository = PokemonRepository(retrofitService)
        val viewModel: PokemonAutoLoadViewModel = ViewModelProvider(this,
            PokemonAutoLoadViewModelFactory(mainRepository)).get(PokemonAutoLoadViewModel::class.java)
        viewModel.pokemonListItem.observe(this) {
            Log.d("pokemon", "setPokemonItemData")
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
        viewModel.loadPokemonListItem()
    }

}