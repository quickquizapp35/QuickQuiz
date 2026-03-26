package uk.ac.tees.mad.quickquiz.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.tees.mad.quickquiz.ui.setting.component.AccountInfoCard
import uk.ac.tees.mad.quickquiz.ui.setting.component.AccountSection
import uk.ac.tees.mad.quickquiz.ui.setting.component.LogoutButton
import uk.ac.tees.mad.quickquiz.ui.setting.component.PreferenceSection
import uk.ac.tees.mad.quickquiz.ui.setting.component.SettingsHeader
import uk.ac.tees.mad.quickquiz.ui.setting.component.SettingsTopBar
import uk.ac.tees.mad.quickquiz.ui.theme.Dimens

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.ScreenPadding)
    ) {

        SettingsTopBar()

        Spacer(Modifier.height(Dimens.SpaceXL))

        SettingsHeader()

        Spacer(Modifier.height(Dimens.SpaceXL))

        PreferenceSection()

        Spacer(Modifier.height(Dimens.SpaceXL))

        AccountSection()

        Spacer(Modifier.weight(1f))

        LogoutButton()

        Spacer(Modifier.height(Dimens.SpaceXL))

        AccountInfoCard()

        Spacer(Modifier.height(Dimens.SpaceL))
    }
}


@Composable
@Preview(showBackground = true)
fun SettingScreenPreview(){
    SettingsScreen()
}