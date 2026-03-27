package geekofia.poojapatha.partner.cmp.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ── Light colour scheme (warm saffron, bright backgrounds) ───────────────────
private val LightColorScheme = lightColorScheme(
    primary = Saffron40,
    onPrimary = Color.White,
    primaryContainer = Saffron90,
    onPrimaryContainer = Saffron10,
    secondary = WarmBrown40,
    onSecondary = Color.White,
    secondaryContainer = WarmBrown90,
    onSecondaryContainer = WarmBrown10,
    tertiary = Gold40,
    onTertiary = Color.White,
    tertiaryContainer = Gold90,
    onTertiaryContainer = Gold10,
    background = NeutralWarm98,
    onBackground = NeutralWarm20,
    surface = NeutralWarm98,
    onSurface = NeutralWarm20,
    surfaceVariant = Saffron95,
    onSurfaceVariant = NeutralWarm40,
    outline = OutlineLight,
    outlineVariant = Saffron90,
    error = Error40,
    onError = Color.White,
    errorContainer = Error90,
    onErrorContainer = Error10,
)

// ── Dark colour scheme (warm saffron, dark backgrounds) ──────────────────────
private val DarkColorScheme = darkColorScheme(
    primary = Saffron80,
    onPrimary = Saffron20,
    primaryContainer = Saffron30,
    onPrimaryContainer = Saffron90,
    secondary = WarmBrown80,
    onSecondary = WarmBrown20,
    secondaryContainer = WarmBrown30,
    onSecondaryContainer = WarmBrown90,
    tertiary = Gold80,
    onTertiary = Gold20,
    tertiaryContainer = Gold30,
    onTertiaryContainer = Gold90,
    background = NeutralWarm20,
    onBackground = NeutralWarm90,
    surface = NeutralWarm20,
    onSurface = NeutralWarm90,
    surfaceVariant = NeutralWarm40,
    onSurfaceVariant = NeutralWarm80,
    outline = OutlineDark,
    outlineVariant = NeutralWarm40,
    error = Error80,
    onError = Error10,
    errorContainer = Error40,
    onErrorContainer = Error90,
)

// ── Ocean colour scheme (cool teal/blue, dark backgrounds) ───────────────────
private val OceanColorScheme = darkColorScheme(
    primary = OceanTeal80,
    onPrimary = OceanTeal20,
    primaryContainer = OceanTeal30,
    onPrimaryContainer = OceanTeal90,
    secondary = OceanBlue80,
    onSecondary = OceanBlue20,
    secondaryContainer = OceanBlue30,
    onSecondaryContainer = OceanBlue90,
    tertiary = Gold80,
    onTertiary = Gold20,
    tertiaryContainer = Gold30,
    onTertiaryContainer = Gold90,
    background = OceanSurface,
    onBackground = OceanOnSurface,
    surface = OceanSurface,
    onSurface = OceanOnSurface,
    surfaceVariant = OceanVariant,
    onSurfaceVariant = OceanOnSurface,
    outline = OceanOutline,
    outlineVariant = OceanVariant,
    error = Error80,
    onError = Error10,
    errorContainer = Error40,
    onErrorContainer = Error90,
)

@Composable
fun PoojaPathaPartnerTheme(
    themeMode: ThemeMode = ThemeMode.LIGHT,
    content: @Composable () -> Unit,
) {
    val colorScheme = when (themeMode) {
        ThemeMode.LIGHT -> LightColorScheme
        ThemeMode.DARK -> DarkColorScheme
        ThemeMode.OCEAN -> OceanColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                !themeMode.isDark
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
