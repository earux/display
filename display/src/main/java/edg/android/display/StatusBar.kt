package edg.android.display

import androidx.compose.ui.graphics.Color

interface StatusBar {
    val isVisible: Boolean

    var darkIcons: Boolean

    fun show()

    fun hide()

    fun backgroundColor(color: Color)

    fun backgroundColorTransparent(colorRef: Color)
}