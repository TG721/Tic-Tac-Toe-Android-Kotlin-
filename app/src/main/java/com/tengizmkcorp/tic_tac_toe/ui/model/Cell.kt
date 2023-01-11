package com.tengizmkcorp.tic_tac_toe.ui.model

data class Cell (
    val xVisibility: Boolean,
    val oVisibility: Boolean,
    val value: VALUES
        )

enum class VALUES {
    X,
    O,
    NONE
}