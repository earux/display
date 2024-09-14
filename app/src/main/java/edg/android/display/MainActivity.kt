package edg.android.display

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import edg.android.display.ui.theme.DisplayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val display = rememberDisplay()
            DisplayTheme {
                Main(display)
            }
        }
    }
}

@Composable
fun Main(display: Display) {

    val primary = MaterialTheme.colorScheme.primary
    val surface = MaterialTheme.colorScheme.surface

    LaunchedEffect(display, surface) {
        display.statusBar.backgroundColorTransparent(surface)
        display.navigationBar.backgroundColorTransparent(surface)
    }

    var top by remember {
        mutableStateOf(0.dp)
    }
    var bottom by remember {
        mutableStateOf(0.dp)
    }
    var left by remember {
        mutableStateOf(0.dp)
    }
    var right by remember {
        mutableStateOf(0.dp)
    }

    val buttonColorsActive = ButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    )

    val buttonColorsInactive = ButtonColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        contentColor = MaterialTheme.colorScheme.secondary,
        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    )

    val buttonBars = ButtonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    )

    var buttonSystemBars by remember {
        mutableStateOf(buttonColorsInactive)
    }

    var buttonSystemsGesture by remember {
        mutableStateOf(buttonColorsInactive)
    }

    var buttonSystemCutout by remember {
        mutableStateOf(buttonColorsInactive)
    }

    var buttonMandatorySystemGesture by remember {
        mutableStateOf(buttonColorsInactive)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
                .padding(
                    top = 80.dp,
                    start = 50.dp,
                    end = 50.dp,
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        top = display.paddingSystemBars.top
                        bottom = display.paddingSystemBars.bottom
                        left = display.paddingSystemBars.left
                        right = display.paddingSystemBars.right
                        buttonSystemBars = buttonColorsActive
                        buttonSystemsGesture = buttonColorsInactive
                        buttonSystemCutout = buttonColorsInactive
                        buttonMandatorySystemGesture = buttonColorsInactive
                        display.statusBar.backgroundColorTransparent(primary)
                        display.navigationBar.backgroundColorTransparent(primary)
                    },
                    colors = buttonSystemBars,
                    border = null
                ) {
                    Text(text = "System Bars")
                }
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        top = display.paddingGesture.top
                        bottom = display.paddingGesture.bottom
                        left = display.paddingGesture.left
                        right = display.paddingGesture.right
                        buttonSystemBars = buttonColorsInactive
                        buttonSystemsGesture = buttonColorsActive
                        buttonSystemCutout = buttonColorsInactive
                        buttonMandatorySystemGesture = buttonColorsInactive
                        display.statusBar.backgroundColorTransparent(primary)
                        display.navigationBar.backgroundColorTransparent(primary)
                    },
                    colors = buttonSystemsGesture,
                    border = null
                ) {
                    Text(text = "System Gesture")
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        top = display.paddingCutout.top
                        bottom = display.paddingCutout.bottom
                        left = display.paddingCutout.left
                        right = display.paddingCutout.right
                        buttonSystemBars = buttonColorsInactive
                        buttonSystemsGesture = buttonColorsInactive
                        buttonSystemCutout = buttonColorsActive
                        buttonMandatorySystemGesture = buttonColorsInactive
                        //display.statusBar.backgroundColorTransparent(surface)
                        display.navigationBar.backgroundColorTransparent(surface)
                    },
                    colors = buttonSystemCutout,
                    border = null
                ) {
                    Text(text = "System Cutout")
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        top = display.paddingMandatoryGesture.top
                        bottom = display.paddingMandatoryGesture.bottom
                        left = display.paddingMandatoryGesture.left
                        right = display.paddingMandatoryGesture.right
                        buttonSystemBars = buttonColorsInactive
                        buttonSystemsGesture = buttonColorsInactive
                        buttonSystemCutout = buttonColorsInactive
                        buttonMandatorySystemGesture = buttonColorsActive
                        display.statusBar.backgroundColorTransparent(surface)
                        display.navigationBar.backgroundColorTransparent(surface)
                    },
                    colors = buttonMandatorySystemGesture,
                    border = null
                ) {
                    Text(text = "Mandatory System Gesture")
                }

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        top = 0.dp
                        bottom = 0.dp
                        left = 0.dp
                        right = 0.dp
                        buttonSystemBars = buttonColorsInactive
                        buttonSystemsGesture = buttonColorsInactive
                        buttonSystemCutout = buttonColorsInactive
                        buttonMandatorySystemGesture = buttonColorsInactive
                        display.statusBar.backgroundColorTransparent(surface)
                        display.navigationBar.backgroundColorTransparent(surface)
                        display.statusBar.show()
                        display.navigationBar.show()
                    },
                ) {
                    Text(text = "RESET")
                }
                Information(top, bottom, left, right)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (display.statusBar.isVisible) {
                            display.statusBar.hide()
                        } else {
                            display.statusBar.show()
                        }
                    },
                    colors = buttonBars
                ){
                    Text(text = "Status Bar: ${if (display.statusBar.isVisible) "VISIBLE" else "HIDE"}")
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (display.navigationBar.isVisible) {
                            display.navigationBar.hide()
                        } else {
                            display.navigationBar.show()
                        }
                    },
                    colors = buttonBars
                ){
                    Text(text = "Navigation Bar: ${if (display.navigationBar.isVisible) "VISIBLE" else "HIDE"}")
                }
            }
        }
        Content(Padding(top = top, bottom = bottom, left = left, right = right), this)
        /*LineInsets(display.paddingSystemBars, this)
        LineInsets(display.paddingMandatoryGesture, this)
        LineInsets(display.paddingGesture, this)
        LineInsets(display.paddingSystemBars, this)*/

    }
}

@Composable
fun Content(
    padding: Padding,
    scope: BoxScope
) {
    scope.apply {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = padding.top,
                    start = padding.left,
                    end = padding.right,
                    bottom = padding.bottom
                )
                .background(MaterialTheme.colorScheme.surface)
        )
    }
}

@Composable
fun LineInsets(
    padding: Padding,
    scope: BoxScope
) {
    scope.apply {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = padding.top,
                    bottom = padding.bottom,
                    start = padding.left,
                    end = padding.right
                )
                .border(0.5.dp, MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
fun Information(
    top: Dp,
    bottom: Dp,
    left: Dp,
    right: Dp
) {
    Column(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp)
            .fillMaxSize()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row {
            Text("Top:", modifier = Modifier.width(70.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(": ${top.value}  [Dp]", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Row {
            Text("Bottom:", modifier = Modifier.width(70.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(": ${bottom.value}  [Dp]", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Row {
            Text("Left:", modifier = Modifier.width(70.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(": ${left.value}  [Dp]", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Row {
            Text("Right", modifier = Modifier.width(70.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(": ${right.value}  [Dp]", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
