package neilt.mobile.kocmoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import neilt.mobile.kocmoc.ui.theme.KocmocTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KocmocTheme {
                KocmocCanvas()
            }
        }
    }
}

@Composable
fun KocmocCanvas() {
    val points = remember { mutableStateListOf<Point>() }

    val configuration = LocalConfiguration.current
    val maxWidth = configuration.screenWidthDp.dp.toPx()
    val maxHeight = configuration.screenHeightDp.dp.toPx()

    LaunchedEffect(Unit) {
        while (true) {
            points += generateRandomPoint(maxWidth, maxHeight)
            delay(200)
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(color = Color.Black, size = size)

        points.forEach { point ->
            drawCircle(
                color = point.color,
                radius = point.radius,
                center = Offset(point.x, point.y)
            )
        }
    }
}

fun generateRandomPoint(maxWidth: Float, maxHeight: Float): Point {
    val random = Random(System.currentTimeMillis())
    val grayShade = random.nextInt(256)

    return Point(
        x = random.nextFloat() * maxWidth,
        y = random.nextFloat() * maxHeight,
        radius = random.nextFloat() * 10f,
        color = Color(grayShade, grayShade, grayShade)
    )
}

data class Point(
    val x: Float,
    val y: Float,
    val radius: Float,
    val color: Color,
)

@Composable
fun Dp.toPx(): Float {
    return with(LocalDensity.current) { this@toPx.toPx() }
}