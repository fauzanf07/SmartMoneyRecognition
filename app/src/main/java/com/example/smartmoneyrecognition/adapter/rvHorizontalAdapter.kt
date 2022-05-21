package com.example.smartmoneyrecognition.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartmoneyrecognition.data_class.rvData
import com.example.smartmoneyrecognition.databinding.RvItemHorizontalBinding

class rvHorizontalAdapter(
    private val listRvData: ArrayList<rvData>
) : RecyclerView.Adapter<rvHorizontalAdapter.ListViewHolder>() {

    class ListViewHolder(private val binding: RvItemHorizontalBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(images: Int, title: String){
            binding.rvImage.setImageResource(images)
            binding.rvTitle.setText(title)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = RvItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listRvData[position].image,listRvData[position].title)
    }

    override fun getItemCount() = listRvData.size
}