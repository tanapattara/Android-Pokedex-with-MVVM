package th.ac.kku.cis.lab.pokedexmvvm.presentation.pokemon_autoload

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import th.ac.kku.cis.lab.pokedexmvvm.data.model.PokemonListItem
import th.ac.kku.cis.lab.pokedexmvvm.data.repository.PokemonRepository
import th.ac.kku.cis.lab.pokedexmvvm.databinding.RecyclerviewItemBinding

class PokemonAutoLoadAdapter ()
    : RecyclerView.Adapter<PokemonAutoLoadAdapter.PokemonAutoLoadViewHolder>() {

    var pokemonListItem = mutableListOf<PokemonListItem>()

    class PokemonAutoLoadViewHolder(val binding: RecyclerviewItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAutoLoadViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        return PokemonAutoLoadAdapter.PokemonAutoLoadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonAutoLoadViewHolder, position: Int) {
        val pokemon = pokemonListItem[position]
        holder.binding.tvPokemonName.text = pokemon.name
        Glide.with(holder.itemView.context).load(pokemon.imageUrl).into(holder.binding.ivPokemon)
    }

    override fun getItemCount(): Int {
        return pokemonListItem.size
    }
    fun setPokemonItemData(data: List<PokemonListItem>) {
        this.pokemonListItem = data.toMutableList()
        notifyDataSetChanged()
    }
}