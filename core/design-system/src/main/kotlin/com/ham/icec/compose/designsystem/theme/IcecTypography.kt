package com.ham.icec.compose.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ham.icec.compose.designsystem.R

private val pretendard =
    FontFamily(
        Font(
            R.font.pretendard_bold,
            FontWeight.Bold,
            FontStyle.Normal
        ),
        Font(
            R.font.pretendard_medium,
            FontWeight.Medium,
            FontStyle.Normal
        ),
        Font(
            R.font.pretendard_regular,
            FontWeight.Normal,
            FontStyle.Normal
        )
    )

@Immutable
data class IcecTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val t1: TextStyle,
    val t2: TextStyle,
    val sbt1: TextStyle,
    val sbt2: TextStyle,
    val b1: TextStyle,
    val b2: TextStyle,
    val b3: TextStyle,
) {
    companion object {
        fun defaultTypography(): IcecTypography = IcecTypography(
            h1 =
            TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = (-0.04).em,
                lineHeight = 28.sp,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            h2 =
            TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                letterSpacing = (-0.04).em,
                lineHeight = 26.sp,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            t1 =
            TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                letterSpacing = (-0.04).em,
                lineHeight = 22.sp,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            t2 =
            TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                letterSpacing = (0.02).em,
                lineHeight = 20.sp,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            sbt1 =
            TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = (0.02).em,
                lineHeight = 20.sp,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            sbt2 =
            TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                letterSpacing = (0.04).sp,
                lineHeight = 16.sp,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            b1 =
            TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                letterSpacing = (0.02).em,
                lineHeight = 20.sp,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            b2 =
            TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                letterSpacing = (0.04).em,
                lineHeight = 16.sp,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
            b3 =
            TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                letterSpacing = (0.04).em,
                lineHeight = 14.sp,
                lineHeightStyle =
                LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Proportional,
                    trim = LineHeightStyle.Trim.None
                )
            ),
        )
    }
}
