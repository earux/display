package edg.android.display.inside

import android.content.res.Resources
import android.os.Build
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import edg.android.display.Padding

internal fun WindowInsetsCompat.getPadding(typeMask: Int): Padding {
    val density = Resources.getSystem().displayMetrics.density
    val insets = getInsets(typeMask)
    return Padding(
        top = (insets.top / density).dp,
        bottom = (insets.bottom / density).dp,
        left = (insets.left / density).dp,
        right = (insets.right / density).dp
    )
}

@RequiresApi(Build.VERSION_CODES.R)
internal fun WindowInsets.getPadding(typeMask: Int): Padding {
    val density = Resources.getSystem().displayMetrics.density
    val insets = getInsets(typeMask)
    return Padding(
        top = (insets.top / density).dp,
        bottom = (insets.bottom / density).dp,
        left = (insets.left / density).dp,
        right = (insets.right / density).dp
    )
}