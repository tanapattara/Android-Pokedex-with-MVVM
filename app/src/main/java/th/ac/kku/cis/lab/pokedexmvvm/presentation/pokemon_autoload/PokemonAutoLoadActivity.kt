package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_autoload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_list.PokemonListAdapter
import th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_list.PokemonListViewModel

class PokemonAutoLoadActivity : AppCompatActivity() {

    lateinit var viewModel: PokemonAutoLoadViewModel
    private val adapter = PokemonAutoLoadAdapter(this)
    lateinit var binding: ActivityPokemonListBinding
    var loading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_pokemon_auto_load)
        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter

        val retrofitService = PokemonAPI.getInstance()
        val mainRepository = PokemonRepository(retrofitService)
        viewModel = ViewModelProvider(this,
            PokemonAutoLoadViewModelFactory(mainRepository)).get(PokemonAutoLoadViewModel::class.java)
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
        viewModel.loadPokemonListItem()
        binding.recyclerView.apply{
            addOnScrollListener(listener)
        }
    }
    val listener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val itemCount = adapter.itemCount
            var layoutManager:LinearLayoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
            val itemPosition = layoutManager.findLastVisibleItemPosition()
            //Log.d("pokemon", "onScrolled itemCount :" + itemCount + " position " + itemPosition )
            if (!loading && itemCount <= (itemPosition + 1)) {
                loading = true
                Log.d("pokemon", "Loading")
                Handler(Looper.getMainLooper()).postDelayed({
                    loading = false
                    viewModel.loadPokemonListItem()
                }, 3000)
            }
        }
    }
}