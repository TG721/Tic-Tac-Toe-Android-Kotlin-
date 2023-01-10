package com.tengizmkcorp.tic_tac_toe.ui.element

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tengizmkcorp.tic_tac_toe.databinding.FragmentGameBinding
import com.tengizmkcorp.tic_tac_toe.ui.adapter.CellItemAdapter
import com.tengizmkcorp.tic_tac_toe.ui.common.BaseFragment
import com.tengizmkcorp.tic_tac_toe.ui.common.ItemOffsetDecoration

class GameFragment : BaseFragment<FragmentGameBinding>(FragmentGameBinding::inflate) {
    private lateinit var cellItemAdapter: CellItemAdapter
    override fun setup() {
        setupRecycler()
    }

    private fun setupRecycler() {
        cellItemAdapter = CellItemAdapter(9)
        val recycler = binding.RVgame
        recycler.layoutManager = GridLayoutManager (requireContext(),3, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = cellItemAdapter
        recycler.addItemDecoration(ItemOffsetDecoration(0))

    }

    override fun listeners() {
        binding.apply {

        }
    }
}