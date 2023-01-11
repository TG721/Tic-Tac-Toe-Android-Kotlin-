package com.tengizmkcorp.tic_tac_toe.ui.model

data class Cell (
    var xVisibility: Boolean,
    var oVisibility: Boolean,
    var value: VALUES
        )

enum class VALUES {
    X,
    O,
    NONE
}