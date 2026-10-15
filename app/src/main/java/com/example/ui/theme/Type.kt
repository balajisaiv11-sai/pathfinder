package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.R

val EditorialSerifFont = FontFamily(
  Font(R.font.newsreader, FontWeight.Normal)
)

val EditorialSansFont = FontFamily(
  Font(R.font.plus_jakarta_sans, FontWeight.Normal)
)

val AppTypography = Typography(
  displayLarge = TextStyle(
    fontFamily = EditorialSerifFont,
    fontWeight = FontWeight.Normal,
    fontSize = 38.sp,
    lineHeight = 44.sp,
    letterSpacing = (-0.5).sp
  ),
  displayMedium = TextStyle(
    fontFamily = EditorialSerifFont,
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp,
    lineHeight = 38.sp,
    letterSpacing = (-0.25).sp
  ),
  displaySmall = TextStyle(
    fontFamily = EditorialSerifFont,
    fontWeight = FontWeight.Normal,
    fontSize = 26.sp,
    lineHeight = 32.sp
  ),
  headlineLarge = TextStyle(
    fontFamily = EditorialSerifFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 30.sp
  ),
  headlineMedium = TextStyle(
    fontFamily = EditorialSerifFont,
    fontWeight = FontWeight.Normal,
    fontSize = 21.sp,
    lineHeight = 27.sp
  ),
  headlineSmall = TextStyle(
    fontFamily = EditorialSerifFont,
    fontWeight = FontWeight.Normal,
    fontSize = 19.sp,
    lineHeight = 25.sp
  ),
  titleLarge = TextStyle(
    fontFamily = EditorialSerifFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 24.sp
  ),
  titleMedium = TextStyle(
    fontFamily = EditorialSansFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.1.sp
  ),
  titleSmall = TextStyle(
    fontFamily = EditorialSansFont,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp
  ),
  bodyLarge = TextStyle(
    fontFamily = EditorialSansFont,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 23.sp,
    letterSpacing = 0.2.sp
  ),
  bodyMedium = TextStyle(
    fontFamily = EditorialSansFont,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 21.sp,
    letterSpacing = 0.2.sp
  ),
  bodySmall = TextStyle(
    fontFamily = EditorialSansFont,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 17.sp,
    letterSpacing = 0.3.sp
  ),
  labelLarge = TextStyle(
    fontFamily = EditorialSansFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 13.sp,
    lineHeight = 18.sp,
    letterSpacing = 0.3.sp
  ),
  labelMedium = TextStyle(
    fontFamily = EditorialSansFont,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.4.sp
  ),
  labelSmall = TextStyle(
    fontFamily = EditorialSansFont,
    fontWeight = FontWeight.Normal,
    fontSize = 11.sp,
    lineHeight = 14.sp,
    letterSpacing = 0.5.sp
  )
)
