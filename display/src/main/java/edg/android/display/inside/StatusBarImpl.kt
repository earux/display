package edg.android.display.inside

import android.view.Window
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import edg.android.display.OnChangeVisibility
import edg.android.display.StatusBar

class StatusBarImpl(
    private val window: Window,
    private val windowInsetsCompat: WindowInsetsCompat?,
    private val windowInsetsControllerCompat: WindowInsetsControllerCompat
) : StatusBar, OnChangeVisibility {

    private val status = WindowInsetsCompat.Type.statusBars()

    private var _backgroundColor by mutableStateOf(
        Color(window.statusBarColor)
    )

    private var _darkIcons by mutableStateOf(
        windowInsetsControllerCompat.isAppearanceLightStatusBars
    )

    override var isVisible by mutableStateOf(
        windowInsetsCompat?.isVisible(status) == true
    )

    override var darkIcons: Boolean
        get() = _darkIcons
        set(value) {
            windowInsetsControllerCompat.isAppearanceLightStatusBars = value
            _darkIcons = value
            //val i = windowInsetsCompat?.getInsets(WindowInsetsCompat.Type.systemBars())
        }

    override fun show() {
        windowInsetsControllerCompat.show(status)
        //windowInsetsCompat?.getPadding(WindowInsetsCompat.Type.statusBars())
    }

    override fun hide() {
        windowInsetsControllerCompat.hide(status)
    }

    override fun backgroundColor(color: Color) {
        windowInsetsControllerCompat.isAppearanceLightStatusBars = color.luminance() > 0.5f
        window.statusBarColor = color.toArgb()
        _backgroundColor = color
    }

    override fun backgroundColorTransparent(colorRef: Color) {
        windowInsetsControllerCompat.isAppearanceLightStatusBars = colorRef.luminance() > 0.5f
        window.statusBarColor = Color.Transparent.toArgb()
        _backgroundColor = Color(window.statusBarColor)
    }

    override fun onChange(visible: Boolean) {
        this.isVisible = visible
    }

}