package th.ac.kku.cis.lab.pokedexmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import th.ac.kku.cis.lab.pokedexmvvm.data.model.PokemonAPIResult

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerViewAdapter = RecyclerViewAdapter()
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =  recyclerViewAdapter

        //get data from api
        val apiInterface = APIInterface.create().getPokemons()

        apiInterface.enqueue(object : Callback<PokemonAPIResult>{
            override fun onResponse(
                call: Call<PokemonAPIResult>,
                response: Response<PokemonAPIResult>
            ) {
                if(response.body() != null){
                    recyclerViewAdapter.setPokemonList(response.body()!!)
                }
            }

            override fun onFailure(call: Call<PokemonAPIResult>, t: Throwable) {

            }

        })
    }
}