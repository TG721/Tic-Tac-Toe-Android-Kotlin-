package com.tengizmkcorp.tic_tac_toe.ui.element

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.tengizmkcorp.tic_tac_toe.databinding.FragmentMainBinding
import com.tengizmkcorp.tic_tac_toe.ui.common.BaseFragment


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    override fun setup() {
        binding.apply {
            val spannableStringTitle = SpannableString("Tic-Tac-Toe")
            val fcsRed: ForegroundColorSpan = ForegroundColorSpan(Color.RED)
            val fcsBlue: ForegroundColorSpan = ForegroundColorSpan(Color.BLUE)
            spannableStringTitle.setSpan(fcsRed, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableStringTitle.setSpan(fcsBlue, 8, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            TVTitle.text = spannableStringTitle
        }
    }
}