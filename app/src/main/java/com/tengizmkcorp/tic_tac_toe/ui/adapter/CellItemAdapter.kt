package com.tengizmkcorp.tic_tac_toe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.tengizmkcorp.tic_tac_toe.databinding.CellItemBinding
import com.tengizmkcorp.tic_tac_toe.ui.model.Cell

import android.os.Handler
import android.os.Looper
import android.widget.Toast

class CellItemAdapter(
    private val items: MutableList<Cell>,
    private val clickFun: (item: Cell, pos: Int) -> Unit
) : RecyclerView.Adapter<CellItemAdapter.CellItemViewHolder>() {

    private var isClickEnabled = true
    private val clickHandler = Handler(Looper.getMainLooper())

    inner class CellItemViewHolder(private val binding: CellItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cell) {
            val pos = bindingAdapterPosition
            if (!item.xVisibility)
                binding.IVX.visibility = View.INVISIBLE
            else binding.IVX.visibility = View.VISIBLE
            if (!item.oVisibility)
                binding.IVO.visibility = View.INVISIBLE
            else binding.IVO.visibility = View.VISIBLE

            binding.root.setOnClickListener {
                if (isClickEnabled) {
                    isClickEnabled = false
                    clickFun(item, pos)

                    // Re-enable click after 0.5 seconds
                    clickHandler.postDelayed({
                        isClickEnabled = true
                    }, 300)
                } else if(!item.xVisibility && !item.oVisibility) {
                    Toast.makeText(binding.root.context, "Not so fast", Toast.LENGTH_SHORT).show()
                }
            }
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
