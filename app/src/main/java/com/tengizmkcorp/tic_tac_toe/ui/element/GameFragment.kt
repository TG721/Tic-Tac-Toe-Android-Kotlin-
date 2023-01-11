package com.tengizmkcorp.tic_tac_toe.ui.element

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
            }
            else {
                cellList[pos].oVisibility = true
                cellList[pos].value = VALUES.O
            }
            cellItemAdapter.notifyDataSetChanged()
            numberOfOccupiedCells++
        }
    }

    override fun listeners() {
        binding.apply {

        }
    }
}