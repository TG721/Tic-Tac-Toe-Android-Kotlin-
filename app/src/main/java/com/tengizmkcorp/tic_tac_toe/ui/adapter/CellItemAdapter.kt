package com.tengizmkcorp.tic_tac_toe.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.tengizmkcorp.tic_tac_toe.databinding.CellItemBinding

class CellItemAdapter(private val size: Int) :
    RecyclerView.Adapter<CellItemAdapter.CellItemViewHolder>() {
    inner class CellItemViewHolder(binding: CellItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellItemViewHolder {
        return CellItemViewHolder(
            CellItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CellItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return size
    }


}