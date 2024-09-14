package edg.android.display.inside

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.doOnLayout
import androidx.core.view.updatePadding
import edg.android.display.Display
import edg.android.display.NavigationBar
import edg.android.display.OnChangeVisibility
import edg.android.display.Padding
import edg.android.display.StatusBar
import edg.android.display.WindowClass

class DisplayImpl(
    private val window: Window
) : Display {

    private val windowInsetsCompat = ViewCompat.getRootWindowInsets(
        window.decorView
    )
    private val windowInsetsControllerCompat = WindowCompat.getInsetsController(
        window, window.decorView
    )

    private val windowClass = WindowClass()

    override val statusBar: StatusBar = StatusBarImpl(
        window,
        windowInsetsCompat,
        windowInsetsControllerCompat
    )

    override val navigationBar: NavigationBar = NavigationBarImpl(
        window,
        windowInsetsCompat,
        windowInsetsControllerCompat
    )

    override var paddingSystemBars by mutableStateOf(
        windowInsetsCompat?.getPadding(WindowInsetsCompat.Type.systemBars()) ?: Padding()
    )
        private set

    override var paddingGesture by mutableStateOf(
        windowInsetsCompat?.getPadding(WindowInsetsCompat.Type.systemGestures()) ?: Padding()
    )
        private set

    override var paddingMandatoryGesture by mutableStateOf(
        windowInsetsCompat?.getPadding(WindowInsetsCompat.Type.mandatorySystemGestures())
            ?: Padding()
    )

    override var paddingCutout by mutableStateOf(
        windowInsetsCompat?.getPadding(WindowInsetsCompat.Type.displayCutout()) ?: Padding()
    )
        private set

    override var orientation: Display.Orientation
        get() {
            val tmp = window.context.resources.configuration.orientation
            return if (tmp == Configuration.ORIENTATION_PORTRAIT) {
                Display.Orientation.PORTRAIT
            } else {
                Display.Orientation.LANDSCAPE
            }
        }
        set(value) {
            (window.context as Activity).requestedOrientation = when (value) {
                Display.Orientation.PORTRAIT -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                Display.Orientation.LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }

    override fun size(measure: Display.Measure): Display.Type {
        return when (measure) {
            Display.Measure.WIDTH -> {
                windowClass.width()
            }

            Display.Measure.HEIGHT -> {
                windowClass.height()
            }
        }
    }

    init {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            //@Suppress("DEPRECATION")
            window.decorView.apply {
                systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                systemUiVisibility = systemUiVisibility and
                        View.SYSTEM_UI_FLAG_FULLSCREEN.inv()
                systemUiVisibility = systemUiVisibility and
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION.inv()
            }
        }
        windowInsetsControllerCompat
            .systemBarsBehavior = WindowInsetsControllerCompat
            .BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, insets ->
            val windowInsetsCompat = ViewCompat.onApplyWindowInsets(view, insets)
            paddingSystemBars = windowInsetsCompat.getPadding(
                WindowInsetsCompat.Type.systemBars()
            )
            paddingGesture = windowInsetsCompat.getPadding(
                WindowInsetsCompat.Type.systemGestures()
            )
            paddingCutout = windowInsetsCompat.getPadding(
                WindowInsetsCompat.Type.displayCutout()
            )
            paddingMandatoryGesture = windowInsetsCompat.getPadding(
                WindowInsetsCompat.Type.mandatorySystemGestures()
            )

            (statusBar as OnChangeVisibility).onChange(
                windowInsetsCompat.isVisible(WindowInsetsCompat.Type.statusBars())
            )

            (navigationBar as OnChangeVisibility).onChange(
                windowInsetsCompat.isVisible(WindowInsetsCompat.Type.navigationBars())
            )

            windowInsetsCompat
        }
    }

}