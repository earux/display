package edg.android.display

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp

/**
 * Interface representing a display with various properties and methods
 * to interact with system bars, orientation, and padding.
 */
@Stable
interface Display {
    val width: Dp

    val height: Dp

    val density: Float

    /**
     * The status bar of the display.
     */
    val statusBar: StatusBar

    /**
     * The navigation bar of the display.
     */
    val navigationBar: NavigationBar

    /**
     * The orientation of the display.
     */
    var orientation: Orientation

    /**
     * Determines the type of the display based on the given measure.
     *
     * @param measure The measure to determine the type ([Measure.WIDTH] or
     * [Measure.HEIGHT]).
     * @return The type of the display ([Type.COMPACT], [Type.MEDIUM], or
     * [Type.EXPANDED]).
     * @sample edg.android.display.DisplaySample.size
     */
    fun size(measure: Measure): Type

    /**
     * Padding for system bars.
     */
    val paddingSystemBars: Padding

    /**
     * Padding for gesture navigation.
     */
    val paddingGesture: Padding

    /**
     * Padding for mandatory gesture navigation.
     */
    val paddingMandatoryGesture: Padding

    /**
     * Padding for display cutouts.
     */
    val paddingCutout: Padding

    /**
     * Enum representing the measure type (WIDTH or HEIGHT).
     */
    enum class Measure {
        WIDTH,
        HEIGHT
    }

    /**
     * Enum representing the type of the display (COMPACT, MEDIUM, or EXPANDED).
     */
    enum class Type {
        COMPACT,

        /**
         * Represents a medium-sized display type.
         */
        MEDIUM,
        EXPANDED
    }

    /**
     * Enum representing the orientation of the display (PORTRAIT or LANDSCAPE).
     */
    enum class Orientation {
        PORTRAIT,
        LANDSCAPE
    }
}