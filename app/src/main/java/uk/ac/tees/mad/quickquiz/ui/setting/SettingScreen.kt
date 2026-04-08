package uk.ac.tees.mad.quickquiz.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.quickquiz.ui.setting.component.AccountInfoCard
import uk.ac.tees.mad.quickquiz.ui.setting.component.LastScoreCard
import uk.ac.tees.mad.quickquiz.ui.setting.component.LogoutButton
import uk.ac.tees.mad.quickquiz.ui.setting.component.PreferenceSection
import uk.ac.tees.mad.quickquiz.ui.setting.component.SettingsTopBar
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNavBack:()->Unit,
    onLogoutClick:()-> Unit,
    viewModel: SettingViewModel = viewModel()
) {
    val uiState by viewModel.settingUiState.collectAsStateWithLifecycle()

    Box(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)){

        Column(modifier = Modifier.fillMaxSize()){

            SettingsTopBar(onNavBack = onNavBack,
                modifier = Modifier
                    .statusBarsPadding())

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Dimens.ScreenPadding)
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(Modifier.height(Dimens.SpaceS))
                PreferenceSection(
                    isHapticEnabled = uiState.isHapticEnabled,
                    isSoundEnabled = uiState.isSoundEnabled,
                    onHapticEnabledChange = viewModel::onHapticChange,
                    onSoundEnabledChange = viewModel::onSoundChange
                )

                Spacer(Modifier.height(Dimens.SpaceXL))
                LogoutButton(
                   onClick = {
                       viewModel.onLogoutClick(onLogoutClick)
                   }
                )
                Spacer(modifier = Modifier.height(2.dp))
                AccountInfoCard(
                    user = uiState.user?:"com.example@gmail.com"
                )
                Spacer(modifier = Modifier.height(2.dp))
                LastScoreCard(score =( uiState.lastScore * 10).toString()+"%")
                Spacer(Modifier.height(Dimens.SpaceL))
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
               horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "QUICKQUIZ V2.4.0",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun SettingScreenPreview(){
    SettingsScreen(onNavBack = {},
        onLogoutClick = {}
    )
}