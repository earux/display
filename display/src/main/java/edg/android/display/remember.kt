package edg.android.display

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import edg.android.display.inside.DisplayImpl

@Composable
fun rememberDisplay(): Display {
    val window = (LocalContext.current as Activity).window
    return remember(window) {
        DisplayImpl(window)
    }
}