package edg.android.display

import android.content.res.Resources
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class WindowClass {
    @Stable
    private var width: Dp

    @Stable
    private var height: Dp

    init {
        val metrics = Resources.getSystem().displayMetrics
        width = (metrics.widthPixels / metrics.density).dp
        height = (metrics.heightPixels / metrics.density).dp
    }

    fun width(): Display.Type {
        return when {
            width.value < 600 -> Display.Type.COMPACT
            width.value < 840 -> Display.Type.MEDIUM
            else -> Display.Type.EXPANDED
        }
    }

    fun height(): Display.Type {
        return when {
            height.value < 480 -> Display.Type.COMPACT
            height.value < 900 -> Display.Type.MEDIUM
            else -> Display.Type.EXPANDED
        }
    }
}