package th.ac.kku.cis.lab.pokedexmvvm.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import th.ac.kku.cis.lab.pokedexmvvm.databinding.ActivityMainBinding
import th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_autoload.PokemonAutoLoadActivity
import th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_list.PokemonListActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRecyclerList.setOnClickListener {
            val intent = Intent(this, PokemonListActivity::class.java)
            startActivity(intent)
        }
        binding.buttonRecyclerPagingView.setOnClickListener {
            val intent = Intent( this, PokemonAutoLoadActivity::class.java)
            startActivity(intent)
        }

    }
}