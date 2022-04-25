package ui.composables.leftPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import themes.PanelTheme
import ui.composables.Panel

@Composable
@Preview
fun TopPanel() {
    PanelTheme {
        val gradient = Brush.horizontalGradient(
            colors = listOf(
                MaterialTheme.colors.primary,
                MaterialTheme.colors.secondaryVariant,
                MaterialTheme.colors.secondary,
                MaterialTheme.colors.secondary
            )
        )
        Panel(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 20.dp),
            brush = gradient,
            topOffset = 20.dp
        )
    }
}