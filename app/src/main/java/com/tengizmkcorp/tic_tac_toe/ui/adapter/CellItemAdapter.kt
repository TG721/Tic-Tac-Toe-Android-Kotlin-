package com.tengizmkcorp.tic_tac_toe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.tengizmkcorp.tic_tac_toe.databinding.CellItemBinding
import com.tengizmkcorp.tic_tac_toe.ui.model.Cell

class CellItemAdapter(private val items: MutableList<Cell>) :
    RecyclerView.Adapter<CellItemAdapter.CellItemViewHolder>() {
    inner class CellItemViewHolder(private val binding: CellItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cell) {
            if (!item.xVisibility)
            binding.IVX.visibility = View.INVISIBLE
            else  binding.IVX.visibility = View.VISIBLE
            if (!item.oVisibility)
                binding.IVO.visibility = View.INVISIBLE
            else  binding.IVO.visibility = View.VISIBLE
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
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


}