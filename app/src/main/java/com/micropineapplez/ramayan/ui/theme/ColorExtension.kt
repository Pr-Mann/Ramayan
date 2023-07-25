package com.micropineapplez.ramayan.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

object ColorExtension {
    /**
     * Returns a [Color] for a given hexValue
     *
     * @param hexValue: [String] containing the hex value
     *
     * Throws: IllegalArgumentException - if this String cannot be parsed.
     */
    fun Color.Companion.fromHex(hexValue: String): Color = Color(hexValue.toColorInt())
}