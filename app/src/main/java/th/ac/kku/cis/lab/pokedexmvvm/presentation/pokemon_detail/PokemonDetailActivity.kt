package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import th.ac.kku.cis.lab.pokedexmvvm.R

class PokemonDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        // Get the Intent that started this activity and extract the string
        val message = intent.getIntExtra("POKEMONID", 0)
        Log.d("pokemon", message!!.toString())
        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.tvPokemonDetailName).apply {
            text = message.toString()
        }
    }
}