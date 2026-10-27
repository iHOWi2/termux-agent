package com.ihowi.termuxagent.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

object AgentColors {
    val Background = Color(0xFF1F1E1D)
    val Surface = Color(0xFF262624)
    val SurfaceHigh = Color(0xFF30302E)
    val Outline = Color(0xFF3D3C39)
    val Accent = Color(0xFFD97757)
    val Text = Color(0xFFECEAE4)
    val TextMuted = Color(0xFFA3A19B)
    val Success = Color(0xFF7FB77E)
    val Danger = Color(0xFFE5675F)
    val Code = Color(0xFF181817)
}

private val Colors = darkColorScheme(
    primary = AgentColors.Accent,
    onPrimary = Color(0xFF1F1E1D),
    secondary = AgentColors.SurfaceHigh,
    onSecondary = AgentColors.Text,
    background = AgentColors.Background,
    onBackground = AgentColors.Text,
    surface = AgentColors.Surface,
    onSurface = AgentColors.Text,
    surfaceVariant = AgentColors.SurfaceHigh,
    onSurfaceVariant = AgentColors.TextMuted,
    surfaceContainer = AgentColors.Surface,
    surfaceContainerHigh = AgentColors.SurfaceHigh,
    surfaceContainerHighest = AgentColors.SurfaceHigh,
    outline = AgentColors.Outline,
    outlineVariant = AgentColors.Outline,
    error = AgentColors.Danger,
)

val MonoStyle = TextStyle(fontFamily = FontFamily.Monospace, fontSize = 13.sp, lineHeight = 19.sp)

private val AppTypography = Typography().let { t ->
    t.copy(
        bodyLarge = t.bodyLarge.copy(fontSize = 16.sp, lineHeight = 24.sp),
        bodyMedium = t.bodyMedium.copy(fontSize = 14.sp, lineHeight = 21.sp),
    )
}

@Composable
fun AgentTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Colors, typography = AppTypography, content = content)
}
