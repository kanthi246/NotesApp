package com.kanthi.notesapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    // Your app's main brand color
    // Used on: FAB, Buttons, TopAppBar background, active icons
    primary = Purple40,

    // Text/icon color that sits ON TOP of primary
    // If your button is purple → this is the text color ON that button
    // Rule: primary + onPrimary always appear together
    onPrimary = Color.Black,

    // Supporting brand color — less prominent than primary
    // Used on: chips, secondary buttons, badges, toggles
    secondary = PurpleGrey40,

    // Text/icon color that sits ON TOP of secondary
    onSecondary = Color.Black,

    // Accent color for small highlights — least used of the three
    // Used on: selected states, progress indicators, highlighted items
    tertiary = Pink40,

    // Text/icon color that sits ON TOP of tertiary
    onTertiary = Color.Black,

    // The base color of every screen — what you see behind everything
    // Used on: Scaffold background, screen canvas
    background = Color(0xFF705E67),  // near Black

    // Text/icon color that sits ON TOP of the background
    // Your default body text color on any screen
    onBackground = Color(0xFF481818),  // near white

    // Elevated surfaces that sit ON TOP of background
    // Used on: Cards, BottomSheet, Dialogs, TopAppBar
    // Think: background = the floor, surface = a table placed on the floor
    surface = Color(0xFF333132),

    // Text/icon color that sits ON TOP of surface
    // Your text color inside Cards, Dialogs, BottomSheets
    onSurface = Color(0xFFF2F1F6),
)

private val LightColorScheme = lightColorScheme(
// Your app's main brand color
    // Used on: FAB, Buttons, TopAppBar background, active icons
    primary = Purple80,

    // Text/icon color that sits ON TOP of primary
    // If your button is purple → this is the text color ON that button
    // Rule: primary + onPrimary always appear together
    onPrimary = Color.White,

    // Supporting brand color — less prominent than primary
    // Used on: chips, secondary buttons, badges, toggles
    secondary = PurpleGrey80,

    // Text/icon color that sits ON TOP of secondary
    onSecondary = Color.White,

    // Accent color for small highlights — least used of the three
    // Used on: selected states, progress indicators, highlighted items
    tertiary = Pink80,

    // Text/icon color that sits ON TOP of tertiary
    onTertiary = Color.White,

    // The base color of every screen — what you see behind everything
    // Used on: Scaffold background, screen canvas
    background = Color(0xFFFFFBFE),  // near white

    // Text/icon color that sits ON TOP of the background
    // Your default body text color on any screen
    onBackground = Color(0xFFEBE9EF),  // near white

    // Elevated surfaces that sit ON TOP of background
    // Used on: Cards, BottomSheet, Dialogs, TopAppBar
    // Think: background = the floor, surface = a table placed on the floor
    surface = Color(0xFFCCC7CB),

    // Text/icon color that sits ON TOP of surface
    // Your text color inside Cards, Dialogs, BottomSheets
    onSurface = Color(0xFFB9B8BB),
    )

@Composable
fun NotesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}