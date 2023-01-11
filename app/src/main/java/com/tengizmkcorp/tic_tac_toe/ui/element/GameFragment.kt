package com.tengizmkcorp.tic_tac_toe.ui.element

import android.graphics.Typeface
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tengizmkcorp.tic_tac_toe.R
import com.tengizmkcorp.tic_tac_toe.databinding.FragmentGameBinding
import com.tengizmkcorp.tic_tac_toe.ui.adapter.CellItemAdapter
import com.tengizmkcorp.tic_tac_toe.ui.common.BaseFragment
import com.tengizmkcorp.tic_tac_toe.ui.common.ItemOffsetDecoration
import com.tengizmkcorp.tic_tac_toe.ui.model.Cell
import com.tengizmkcorp.tic_tac_toe.ui.model.VALUES

class GameFragment : BaseFragment<FragmentGameBinding>(FragmentGameBinding::inflate) {
    private lateinit var cellItemAdapter: CellItemAdapter
    private var cellList = mutableListOf<Cell>()
    private var numberOfOccupiedCells = 0
    private var gameOver = false
    private var result = ""
    override fun setup() {
        setupRecycler()
    }

    private fun setupRecycler() {
        for (i in 0..8) {
            cellList.add(Cell(xVisibility = false, oVisibility = false, value = VALUES.NONE))
        }
        cellItemAdapter = CellItemAdapter(cellList) { item, pos ->
            placeTheMove(item, pos)
        }
        val recycler = binding.RVgame
        recycler.layoutManager =
            GridLayoutManager(requireContext(), 3, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = cellItemAdapter
        recycler.addItemDecoration(ItemOffsetDecoration(0))

    }

    private fun placeTheMove(item: Cell, pos: Int) {
        if (item.value == VALUES.NONE) {
            if (numberOfOccupiedCells % 2 == 0) {
                cellList[pos].xVisibility = true
                cellList[pos].value = VALUES.X
                binding.xCardTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//                binding.oCardTitle.setTypeface(null, Typeface.BOLD);
//                binding.xCardTitle.setTypeface(null, Typeface.NORMAL);
                binding.oCardTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                binding.IVOArrow.visibility = View.VISIBLE
                binding.IVXArrow.visibility = View.GONE
            }
            else {
                cellList[pos].oVisibility = true
                cellList[pos].value = VALUES.O
//                binding.xCardTitle.setTypeface(null, Typeface.BOLD);
//                binding.oCardTitle.setTypeface(null, Typeface.NORMAL);
                binding.xCardTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                binding.oCardTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                binding.IVXArrow.visibility = View.VISIBLE
                binding.IVOArrow.visibility = View.GONE
            }
            cellItemAdapter.notifyDataSetChanged()
            numberOfOccupiedCells++
            //check if any player has won
            if(
                (cellList[0].value==cellList[1].value && cellList[1].value == cellList[2].value)
                || (cellList[0].value==cellList[3].value && cellList[3].value == cellList[6].value)
                || (cellList[2].value==cellList[5].value && cellList[5].value == cellList[8].value)
                || (cellList[1].value==cellList[4].value && cellList[4].value == cellList[7].value)
                || (cellList[6].value==cellList[7].value && cellList[7].value == cellList[8].value)
                || (cellList[3].value==cellList[4].value && cellList[4].value == cellList[5].value)
                || (cellList[0].value==cellList[4].value && cellList[4].value == cellList[8].value)
                || (cellList[2].value==cellList[5].value && cellList[5].value == cellList[6].value)
            ) {
                result = if(binding.IVOArrow.visibility == View.VISIBLE)
                    "Player 1 won"
                else "Player 2 won"
            }
            else if(numberOfOccupiedCells==9)
            {
                result = "Game over, Tie"
            }
        }
    }

    override fun listeners() {
        binding.apply {

        }
    }
}