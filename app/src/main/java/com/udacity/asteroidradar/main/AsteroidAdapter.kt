package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidListItemBinding

class AsteroidAdapter(private val clickListener: AsteroidListener) :
    ListAdapter<Asteroid, RecyclerView.ViewHolder>(AsteroidDiffCallback()) {

    var asterList = listOf<Asteroid>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder (private val binding: AsteroidListItemBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: Asteroid) {
//            binding.asterid = item
//            binding.executePendingBindings()
//        }
        fun bind(item: Asteroid, clickListener: AsteroidListener) {
            binding.asterid = item
            //binding.clickListener = clickListener
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

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(asterList[position])
//    }

    override fun getItemCount() = asterList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val asteroidItem = asterList[position]
        holder as ViewHolder
        holder.bind(asteroidItem, clickListener)
    }
}
class AsteroidDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }
}
class AsteroidListener(val clickListener: (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}
