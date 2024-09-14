package edg.android.display

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
interface NavigationBar {
    @Stable
    val isVisible: Boolean

    @Stable
    var darkIcons: Boolean

    //var darkContent: Boolean

    //val contrastEnforced: Boolean

    @Stable
    fun show()

    fun hide()

    //var colorContent: Color

    fun backgroundColor(color: Color)

    fun backgroundColorTransparent(colorRef: Color, contrastEnforced: Boolean = false)
}