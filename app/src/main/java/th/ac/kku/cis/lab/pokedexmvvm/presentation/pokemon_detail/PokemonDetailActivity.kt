package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import th.ac.kku.cis.lab.pokedexmvvm.R
import th.ac.kku.cis.lab.pokedexmvvm.data.api.PokemonAPI
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository
import th.ac.kku.cis.lab.pokedexmvvm.databinding.ActivityPokemonDetailBinding

class PokemonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        val pokemonID = intent.getIntExtra("POKEMONID", 0)

        val binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        val repo = PokemonRepository(PokemonAPI.getInstance())
        var viewModel = ViewModelProvider(this,
            PokemonDetailViewModelFactory(repo),
            ).get(PokemonDetailViewModel::class.java)

        val tvPokemonName = findViewById<TextView>(R.id.tvPokemonDetailName)

        viewModel.pokemonInfo.observe(this) {
            tvPokemonName.text = it.name
        }
        viewModel.loadPokemonInfo(pokemonID.toString())
    }
}