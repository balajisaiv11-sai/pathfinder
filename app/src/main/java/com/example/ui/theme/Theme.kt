package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
  primary = TerracottaAccent,
  onPrimary = PaperWhite,
  primaryContainer = TerracottaContainer,
  onPrimaryContainer = TerracottaDark,
  secondary = InkForestPrimary,
  onSecondary = PaperWhite,
  secondaryContainer = SageSurface,
  onSecondaryContainer = InkForestDeep,
  tertiary = GoldMuted,
  onTertiary = PaperWhite,
  tertiaryContainer = GoldMutedLight,
  onTertiaryContainer = InkForestDeep,
  background = IvoryBackground,
  onBackground = InkForestDeep,
  surface = PaperWhite,
  onSurface = InkForestDeep,
  surfaceVariant = SageSurface,
  onSurfaceVariant = InkForestMuted,
  outline = BorderNeutral,
  outlineVariant = BorderLight
)

private val DarkColorScheme = darkColorScheme(
  primary = DarkTerracotta,
  onPrimary = PaperWhite,
  primaryContainer = DarkTerracottaContainer,
  onPrimaryContainer = DarkInkText,
  secondary = SageSurface,
  onSecondary = DarkBackground,
  secondaryContainer = DarkSurfaceVariant,
  onSecondaryContainer = DarkInkText,
  tertiary = DarkGold,
  onTertiary = DarkBackground,
  tertiaryContainer = DarkSurfaceVariant,
  onTertiaryContainer = DarkInkText,
  background = DarkBackground,
  onBackground = DarkInkText,
  surface = DarkSurface,
  onSurface = DarkInkText,
  surfaceVariant = DarkSurfaceVariant,
  onSurfaceVariant = DarkInkTertiary,
  outline = DarkBorder,
  outlineVariant = DarkBorder
)

@Composable
fun PathfinderTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}
