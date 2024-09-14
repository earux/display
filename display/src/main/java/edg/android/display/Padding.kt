package edg.android.display

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Padding(
    @Stable
    val top: Dp = 0.dp,
    @Stable
    val bottom: Dp = 0.dp,
    @Stable
    val left: Dp = 0.dp,
    @Stable
    val right: Dp = 0.dp
)