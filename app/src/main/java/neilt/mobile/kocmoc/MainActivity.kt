package neilt.mobile.kocmoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import neilt.mobile.kocmoc.ui.theme.KocmocTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KocmocScreen()
        }
    }
}

@Composable
fun KocmocScreen() {
    KocmocTheme {

    }
}

@Composable
fun KocmocScreenPreview() {
    KocmocScreen()
}