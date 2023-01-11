package com.tengizmkcorp.tic_tac_toe.ui.element

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
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
                binding.xCardTitle.setTextColor(ContextCompat.getColor(requireContext(),
                    R.color.black))
//                binding.oCardTitle.setTypeface(null, Typeface.BOLD);
//                binding.xCardTitle.setTypeface(null, Typeface.NORMAL);
                binding.oCardTitle.setTextColor(ContextCompat.getColor(requireContext(),
                    R.color.white))
                binding.IVOArrow.visibility = View.VISIBLE
                binding.IVXArrow.visibility = View.GONE
            } else {
                cellList[pos].oVisibility = true
                cellList[pos].value = VALUES.O
//                binding.xCardTitle.setTypeface(null, Typeface.BOLD);
//                binding.oCardTitle.setTypeface(null, Typeface.NORMAL);
                binding.xCardTitle.setTextColor(ContextCompat.getColor(requireContext(),
                    R.color.white))
                binding.oCardTitle.setTextColor(ContextCompat.getColor(requireContext(),
                    R.color.black))
                binding.IVXArrow.visibility = View.VISIBLE
                binding.IVOArrow.visibility = View.GONE
            }
            cellItemAdapter.notifyItemChanged(pos)
            numberOfOccupiedCells++
            //check if any player has won
            if (
                (cellList[0].value == cellList[1].value && cellList[1].value == cellList[2].value && cellList[2].value != VALUES.NONE)
                || (cellList[0].value == cellList[3].value && cellList[3].value == cellList[6].value && cellList[6].value != VALUES.NONE)
                || (cellList[2].value == cellList[5].value && cellList[5].value == cellList[8].value && cellList[8].value != VALUES.NONE)
                || (cellList[1].value == cellList[4].value && cellList[4].value == cellList[7].value && cellList[7].value != VALUES.NONE)
                || (cellList[6].value == cellList[7].value && cellList[7].value == cellList[8].value && cellList[8].value != VALUES.NONE)
                || (cellList[3].value == cellList[4].value && cellList[4].value == cellList[5].value && cellList[5].value != VALUES.NONE)
                || (cellList[0].value == cellList[4].value && cellList[4].value == cellList[8].value && cellList[8].value != VALUES.NONE)
                || (cellList[2].value == cellList[4].value && cellList[4].value == cellList[6].value && cellList[6].value != VALUES.NONE)
            ) {
                //check if tie
                result = if (binding.IVOArrow.visibility == View.VISIBLE)
                    "Player 1 won"
                else "Player 2 won"
                binding.RVgame.isClickable = false
                callAlertDialog(result)
            } else if (numberOfOccupiedCells == 9) {
                result = "Tie"
                binding.RVgame.isClickable = false
                callAlertDialog(result)
            }
        }
    }

    private fun callAlertDialog(result: String) {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Game Over")
            .setMessage(result)
            .setNeutralButton("Close", DialogInterface.OnClickListener { _, _ ->
                clearCells()
            })
            .setOnDismissListener {
                clearCells()
            }

        val dialog = builder.create()
        dialog.window!!.attributes.windowAnimations = R.style.DialogBoxAnimation
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun clearCells() {
        for (i in 0..8) cellList[i] = Cell(false, false, VALUES.NONE)
        cellItemAdapter.notifyDataSetChanged()
        binding.xCardTitle.setTextColor(ContextCompat.getColor(requireContext(),
            R.color.white))
        binding.oCardTitle.setTextColor(ContextCompat.getColor(requireContext(),
            R.color.black))
        binding.IVXArrow.visibility = View.VISIBLE
        binding.IVOArrow.visibility = View.GONE
        numberOfOccupiedCells = 0
        result = ""
        binding.RVgame.isClickable = true
    }

}
