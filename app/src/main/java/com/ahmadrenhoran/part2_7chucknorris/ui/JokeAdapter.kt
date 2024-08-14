package com.ahmadrenhoran.part2_7chucknorris.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrenhoran.part2_7chucknorris.data.model.Joke
import com.ahmadrenhoran.part2_7chucknorris.databinding.ItemJokeBinding
import com.bumptech.glide.Glide

class JokeAdapter : ListAdapter<Joke, JokeAdapter.JokeViewHolder>(JokeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }

    class JokeViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Joke) {
            binding.textViewJoke.text = joke.value
            Glide.with(binding.imageViewJoke.context)
                .load(joke.iconUrl)
                .into(binding.imageViewJoke)
        }
    }
}

class JokeDiffCallback : DiffUtil.ItemCallback<Joke>() {
    override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
        return oldItem == newItem
    }
}

