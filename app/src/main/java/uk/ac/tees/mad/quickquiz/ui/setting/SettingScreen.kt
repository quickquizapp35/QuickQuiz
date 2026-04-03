package uk.ac.tees.mad.quickquiz.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.quickquiz.ui.setting.component.AccountInfoCard
import uk.ac.tees.mad.quickquiz.ui.setting.component.AccountSection
import uk.ac.tees.mad.quickquiz.ui.setting.component.LogoutButton
import uk.ac.tees.mad.quickquiz.ui.setting.component.PreferenceSection
import uk.ac.tees.mad.quickquiz.ui.setting.component.SettingsHeader
import uk.ac.tees.mad.quickquiz.ui.setting.component.SettingsTopBar
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNavBack:()->Unit
) {
    Box(modifier = Modifier
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

                SettingsHeader()

                Spacer(Modifier.height(Dimens.SpaceL))

                PreferenceSection()

                Spacer(Modifier.height(Dimens.SpaceXL))

                AccountSection()

                Spacer(modifier = Modifier.height(2.dp))

                LogoutButton()

                Spacer(modifier = Modifier.height(2.dp))

                AccountInfoCard()

                Spacer(Modifier.height(Dimens.SpaceL))
            }


        }
    }
}


@Composable
@Preview(showBackground = true)
fun SettingScreenPreview(){
    SettingsScreen(onNavBack = {})
}