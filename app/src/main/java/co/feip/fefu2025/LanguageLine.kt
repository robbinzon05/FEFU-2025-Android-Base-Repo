package co.feip.fefu2025

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

@Composable
fun LanguageLine(
    modifier: Modifier = Modifier,
    languages: List<LanguageData>
) {
    Box(
        modifier = modifier
            .padding(5.dp)
    ) {
        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
        ) {
            val totalWidth = size.width
            var currentStart = 0f

            for (language in languages) {
                val segmentWidth = totalWidth * (language.percent / 100f)

                drawRect(
                    color = Color(language.color),
                    topLeft = Offset(currentStart, 0f),
                    size = Size(segmentWidth, size.height)
                )

                currentStart += segmentWidth + 1.5.dp.toPx()
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun LanguageLinePreview() {
    FEFU2025AndroidBaseRepoTheme {
        val languages = listOf<LanguageData>(
            LanguageData("Python",57f, 0xFF3572A5),
            LanguageData("Rust",30f, 0xFFDEA584),
            LanguageData("C++",13f, 0xFFF34B7D)
        )
        LanguageLine(
            modifier = Modifier,
            languages = languages
        )
    }
}
