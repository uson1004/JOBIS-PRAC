package com.example.jobis_compose_prac.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jobis_compose_prac.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val PretendardFontFamily = FontFamily(
    Font(R.font.pretendard_thin, FontWeight.Thin),                 // Thin
    Font(R.font.pretendard_extralight, FontWeight.ExtraLight),     // ExtraLight
    Font(R.font.pretendard_light, FontWeight.Light),               // Light
    Font(R.font.pretendard_regular, FontWeight.Normal),            // Regular
    Font(R.font.pretendard_medium, FontWeight.Medium),             // Medium
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),         // SemiBold
    Font(R.font.pretendard_bold, FontWeight.Bold),                 // Bold
    Font(R.font.pretendard_extrabold, FontWeight.ExtraBold),       // ExtraBold
)

val WantedSansFontFamily = FontFamily(
    Font(R.font.wantedsans_regular, FontWeight.Normal),        // Regular
    Font(R.font.wantedsans_medium, FontWeight.Medium),         // Medium
    Font(R.font.wantedsans_semibold, FontWeight.SemiBold),     // SemiBold
    Font(R.font.wantedsans_bold, FontWeight.Bold),             // Bold
    Font(R.font.wantedsans_extrabold, FontWeight.ExtraBold),   // ExtraBold
    Font(R.font.wantedsans_black, FontWeight.Black),           // Black
    Font(R.font.wantedsans_extrablack, FontWeight.ExtraBold)   // ExtraBlack

)