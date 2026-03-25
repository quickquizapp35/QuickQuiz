package uk.ac.tees.mad.quickquiz.ui.result.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PraiseChip(scoreRatio: Float) {
    val text = when {
        scoreRatio >= 0.8f -> "Great job, keep it up!"
        scoreRatio >= 0.5f -> "Nice effort!"
        else -> "Keep practicing!"
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        AssistChip(
            onClick = {},
            label = { Text(text) },
            leadingIcon = {
                Icon(Icons.Outlined.Star, null)
            }
        )
    }
}
