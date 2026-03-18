package uk.ac.tees.mad.quickquiz.ui.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.Animation
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Extension
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.LiveTv
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.TheaterComedy
import androidx.compose.material.icons.outlined.Toys
import androidx.compose.ui.graphics.vector.ImageVector
import uk.ac.tees.mad.quickquiz.domain.model.QuizCategory


object CategoryUiMapper {

    private data class CategoryUiConfig(
        val description: String,
        val icon: ImageVector
    )

    private val categoryConfigMap = mapOf(

        9 to CategoryUiConfig("Everyday facts & trivia", Icons.Outlined.Lightbulb),
        20 to CategoryUiConfig("Gods & legends", Icons.Outlined.AutoAwesome),
        22 to CategoryUiConfig("World & places", Icons.Outlined.Public),
        23 to CategoryUiConfig("Past events", Icons.Outlined.History),
        24 to CategoryUiConfig("Power & governance", Icons.Outlined.AccountBalance),

        10 to CategoryUiConfig("Fiction & literature", Icons.Outlined.MenuBook),
        11 to CategoryUiConfig("Movies & cinema", Icons.Outlined.Movie),
        12 to CategoryUiConfig("Songs & artists", Icons.Outlined.MusicNote),
        13 to CategoryUiConfig("Stage & performances", Icons.Outlined.TheaterComedy),
        14 to CategoryUiConfig("TV shows & series", Icons.Outlined.LiveTv),
        15 to CategoryUiConfig("Gaming & esports", Icons.Outlined.SportsEsports),
        16 to CategoryUiConfig("Tabletop fun", Icons.Outlined.Extension),
        29 to CategoryUiConfig("Comics & heroes", Icons.Outlined.CollectionsBookmark),
        31 to CategoryUiConfig("Anime & manga", Icons.Outlined.Animation),
        32 to CategoryUiConfig("Animated worlds", Icons.Outlined.Toys),

        17 to CategoryUiConfig("Biology & nature", Icons.Outlined.Science),
        18 to CategoryUiConfig("Tech & computing", Icons.Outlined.Computer),
        19 to CategoryUiConfig("Numbers & logic", Icons.Outlined.Calculate),
        30 to CategoryUiConfig("Modern devices", Icons.Outlined.Devices),

        21 to CategoryUiConfig("Games & athletes", Icons.Outlined.SportsSoccer),
        25 to CategoryUiConfig("Art & creativity", Icons.Outlined.Palette),
        26 to CategoryUiConfig("Famous personalities", Icons.Outlined.Star),
        27 to CategoryUiConfig("Wildlife & species", Icons.Outlined.Pets),
        28 to CategoryUiConfig("Cars, planes & more", Icons.Outlined.DirectionsCar)
    )


    fun map(category: QuizCategory): UiCategory {
        val config = categoryConfigMap[category.id]
            ?: CategoryUiConfig(
                description = "General knowledge",
                icon = Icons.Outlined.Category
            )

        return UiCategory(
            id = category.id,
            name = category.name,
            description = config.description,
            iconRes = config.icon
        )
    }
}
