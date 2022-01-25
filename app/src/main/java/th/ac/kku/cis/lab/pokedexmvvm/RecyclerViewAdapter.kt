package th.ac.kku.cis.lab.pokedexmvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import th.ac.kku.cis.lab.pokedexmvvm.data.model.PokemonAPIResult
import th.ac.kku.cis.lab.pokedexmvvm.data.model.Result

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var pokemonList : List<Result> = listOf()
    fun setPokemonList(data: PokemonAPIResult){
        this.pokemonList = data.results
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPokemonName: TextView = itemView.findViewById(R.id.tvPokemonName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,
            parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvPokemonName.text = this.pokemonList.get(position).name
    }

    override fun getItemCount(): Int {
        return this.pokemonList.size
    }



}