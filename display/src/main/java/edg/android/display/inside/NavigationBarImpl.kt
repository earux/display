package edg.android.display.inside

import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import edg.android.display.NavigationBar
import edg.android.display.OnChangeVisibility

class NavigationBarImpl(
    private val window: Window,
    private val windowInsetsCompat: WindowInsetsCompat?,
    private val windowInsetsControllerCompat: WindowInsetsControllerCompat
) : NavigationBar, OnChangeVisibility {

    private val navigation = WindowInsetsCompat.Type.navigationBars()

    private var _darkIcons by mutableStateOf(
        windowInsetsControllerCompat.isAppearanceLightNavigationBars
    )

    override var isVisible by mutableStateOf(
        windowInsetsCompat?.isVisible(navigation) == true
    )

    override var darkIcons: Boolean
        get() = _darkIcons
        set(value) {
            windowInsetsControllerCompat.isAppearanceLightNavigationBars = value
            _darkIcons = value
        }

    override fun show() {
        windowInsetsControllerCompat.show(navigation)
    }

    override fun hide() {
        windowInsetsControllerCompat.hide(navigation)
    }

    override fun backgroundColor(color: Color) {
        windowInsetsControllerCompat.isAppearanceLightNavigationBars = color.luminance() > 0.5f
        window.navigationBarColor = color.toArgb()
    }

    override fun backgroundColorTransparent(colorRef: Color, contrastEnforced: Boolean) {
        windowInsetsControllerCompat.isAppearanceLightNavigationBars = colorRef.luminance() > 0.5f
        window.navigationBarColor = Color.Transparent.toArgb()
        if (Build.VERSION.SDK_INT >= 29) {
            window.isNavigationBarContrastEnforced = contrastEnforced
        }
    }

    override fun onChange(visible: Boolean) {
        this.isVisible = visible
    }

    private fun getStatusBarBackgroundColor(): Int {
        // Obtener la altura de la barra de estado
        val statusBarHeight = windowInsetsCompat?.getInsets(
            WindowInsetsCompat.Type.statusBars()
        )?.top ?: 0
        // Captura el color de la barra de estado
        val view = window.decorView.rootView
        val bitmap = Bitmap.createBitmap(view.width, statusBarHeight, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(bitmap)
        view.draw(canvas)
        // Obtiene el color desde la parte superior central de la pantalla (área de la barra de estado)
        return bitmap.getPixel(view.width / 2, statusBarHeight / 2)
    }

}