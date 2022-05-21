package com.example.smartmoneyrecognition.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartmoneyrecognition.data_class.rvJenisUang
import com.example.smartmoneyrecognition.databinding.RvJenisUangBinding

class RvUangIndonesiaAdapter(
    private val listRvData: ArrayList<rvJenisUang>
) : RecyclerView.Adapter<RvUangIndonesiaAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: RvJenisUangBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(title: String, imageFront: Int,imageBack:Int){
            binding.tvTitleRupiah.text = title
            binding.rvImageDepan.setImageResource(imageFront)
            binding.rvImageBelakang.setImageResource(imageBack)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = RvJenisUangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listRvData[position].title,listRvData[position].imageFront,listRvData[position].imageBack)
    }

    override fun getItemCount() = listRvData.size
}