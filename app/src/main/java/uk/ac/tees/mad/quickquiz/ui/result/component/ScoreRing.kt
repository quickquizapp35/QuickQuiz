package uk.ac.tees.mad.quickquiz.ui.result.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScoreRing(
    score: Int,
    total: Int,
    progress: Float
) {

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        Box(contentAlignment = Alignment.Center) {

            CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(160.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 8.dp,
            trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "$score/$total",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "QUESTIONS",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
