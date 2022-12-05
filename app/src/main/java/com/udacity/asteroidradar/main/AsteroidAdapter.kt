package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidListItemBinding

class AsteroidAdapter : RecyclerView.Adapter<AsteroidAdapter.ViewHolder>() {

    var asterList = listOf<Asteroid>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder (private val binding: AsteroidListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Asteroid) {
            binding.asterid = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AsteroidListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //var view: View = LayoutInflater.from(parent.context).inflate(R.layout.asteroid_list_item, parent, false)
        return  ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asterList[position])
    }

    override fun getItemCount() = asterList.size
}