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
    override fun setup() {
        setupRecycler()
    }

    private fun setupRecycler() {
        for (i in 0..8) {
            cellList.add(Cell(xVisibility = false, oVisibility = false, value = VALUES.NONE))
        }
        cellItemAdapter = CellItemAdapter(cellList)
        val recycler = binding.RVgame
        recycler.layoutManager =
            GridLayoutManager(requireContext(), 3, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = cellItemAdapter
        recycler.addItemDecoration(ItemOffsetDecoration(0))

    }

    override fun listeners() {
        binding.apply {

        }
    }
}