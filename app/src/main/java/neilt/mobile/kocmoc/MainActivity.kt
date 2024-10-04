package neilt.mobile.kocmoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
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
    var points by remember { mutableStateOf(generateRandomPoints()) }

    LaunchedEffect(Unit) {
        while (true) {
            points += generateRandomPoints()
            if (points.size > 1000) {
                points = points.takeLast(1000)
            }
            delay(200)
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas {
            points.forEach { point ->
                drawCircle(
                    color = point.color,
                    radius = point.radius,
                    center = Offset(point.x, point.y)
                )
            }
        }
    }
}

fun generateRandomPoints(): List<Point> {
    val random = Random(System.currentTimeMillis())
    return List(5) {
        Point(
            x = random.nextFloat() * 1080f,
            y = random.nextFloat() * 1920f,
            radius = random.nextFloat() * random.nextInt(20),
            color = Color(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
            )
        )
    }
}

data class Point(
    val x: Float,
    val y: Float,
    val radius: Float,
    val color: Color,
)