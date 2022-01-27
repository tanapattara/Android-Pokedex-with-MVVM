package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import th.ac.kku.cis.lab.pokedexmvvm.data.api.model.Result
import th.ac.kku.cis.lab.pokedexmvvm.databinding.RecyclerviewItemBinding
import com.bumptech.glide.Glide
import th.ac.kku.cis.lab.pokedexmvvm.data.model.PokemonListItem

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    var pokemonList = mutableListOf<Result>()
    var pokemonListItem = mutableListOf<PokemonListItem>()

    class PokemonListViewHolder(val binding: RecyclerviewItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    fun setPokemonData(data: List<Result>) {
        this.pokemonList = data.toMutableList()
        notifyDataSetChanged()
    }
    fun setPokemonItemData(data: List<PokemonListItem>) {
        this.pokemonListItem = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        return PokemonListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = pokemonListItem[position]
        holder.binding.tvPokemonName.text = pokemon.name
        Glide.with(holder.itemView.context).load(pokemon.imageUrl).into(holder.binding.ivPokemon)
    }

    override fun getItemCount(): Int {
        return pokemonListItem.size
    }

}