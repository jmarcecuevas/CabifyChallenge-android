package com.marcelocuevas.cabify.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = CabifyColors(
    brand = Shadow5,
    brandSecondary = Ocean3,
    uiBackground = Neutral0,
    uiBorder = Neutral5,
    uiFloated = FunctionalGrey,
    textSecondary = Neutral7,
    textHelp = Neutral6,
    textInteractive = Neutral0,
    textLink = Ocean11,
    iconSecondary = Neutral7,
    iconInteractive = Neutral0,
    iconInteractiveInactive = Neutral1,
    error = FunctionalRed,
    gradient61 = listOf(Shadow4, Ocean3, Shadow2, Ocean3, Shadow4),
    gradient62 = listOf(Rose4, Lavender3, Rose2, Lavender3, Rose4),
    gradient31 = listOf(Shadow2, Ocean3, Shadow4),
    gradient32 = listOf(Rose2, Lavender3, Rose4),
    gradient21 = listOf(Shadow4, Shadow11),
    gradient22 = listOf(Ocean3, Shadow3),
    gradient23 = listOf(Lavender3, Rose2),
    tornado1 = listOf(Shadow4, Ocean3),
    isDark = false
)

private val DarkColorPalette = CabifyColors(
    brand = Shadow1,
    brandSecondary = Ocean2,
    uiBackground = Neutral8,
    uiBorder = Neutral3,
    uiFloated = FunctionalDarkGrey,
    textPrimary = Shadow1,
    textSecondary = Neutral0,
    textHelp = Neutral1,
    textInteractive = Neutral7,
    textLink = Ocean2,
    iconPrimary = Shadow1,
    iconSecondary = Neutral0,
    iconInteractive = Neutral7,
    iconInteractiveInactive = Neutral6,
    error = FunctionalRedDark,
    gradient61 = listOf(Shadow5, Ocean7, Shadow9, Ocean7, Shadow5),
    gradient62 = listOf(Rose11, Lavender7, Rose8, Lavender7, Rose11),
    gradient31 = listOf(Shadow9, Ocean7, Shadow5),
    gradient32 = listOf(Rose8, Lavender7, Rose11),
    gradient21 = listOf(Ocean3, Shadow3),
    gradient22 = listOf(Ocean4, Shadow2),
    gradient23 = listOf(Lavender3, Rose3),
    tornado1 = listOf(Shadow4, Ocean3),
    isDark = true
)

@Composable
fun CabifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.uiBackground.copy(alpha = AlphaNearOpaque)
        )
    }

    ProvideCabifyColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = typography,
            shapes = Shapes,
            content = content
        )
    }
}

object CabifyTheme {
    val colors: CabifyColors
        @Composable
        get() = LocalCabifyColors.current
}

/**
 * Cabify custom Color Palette
 */
@Stable
class CabifyColors(
    gradient61: List<Color>,
    gradient62: List<Color>,
    gradient31: List<Color>,
    gradient32: List<Color>,
    gradient21: List<Color>,
    gradient22: List<Color>,
    gradient23: List<Color>,
    brand: Color,
    brandSecondary: Color,
    uiBackground: Color,
    uiBorder: Color,
    uiFloated: Color,
    interactivePrimary: List<Color> = gradient21,
    interactiveSecondary: List<Color> = gradient22,
    interactiveMask: List<Color> = gradient61,
    textPrimary: Color = brand,
    textSecondary: Color,
    textHelp: Color,
    textInteractive: Color,
    textLink: Color,
    tornado1: List<Color>,
    iconPrimary: Color = brand,
    iconSecondary: Color,
    iconInteractive: Color,
    iconInteractiveInactive: Color,
    error: Color,
    notificationBadge: Color = error,
    isDark: Boolean
) {
    private var gradient61 by mutableStateOf(gradient61)

    private var gradient62 by mutableStateOf(gradient62)

    private var gradient31 by mutableStateOf(gradient31)

    var gradient32 by mutableStateOf(gradient32)

    var gradient21 by mutableStateOf(gradient21)

    private var gradient22 by mutableStateOf(gradient22)

    private var gradient23 by mutableStateOf(gradient23)

    var brand by mutableStateOf(brand)

    var brandSecondary by mutableStateOf(brandSecondary)

    var uiBackground by mutableStateOf(uiBackground)

    var uiBorder by mutableStateOf(uiBorder)

    private var uiFloated by mutableStateOf(uiFloated)

    var interactivePrimary by mutableStateOf(interactivePrimary)

    var interactiveSecondary by mutableStateOf(interactiveSecondary)

    private var interactiveMask by mutableStateOf(interactiveMask)

    var textPrimary by mutableStateOf(textPrimary)

    var textSecondary by mutableStateOf(textSecondary)

    var textHelp by mutableStateOf(textHelp)

    var textInteractive by mutableStateOf(textInteractive)

    private var tornado1 by mutableStateOf(tornado1)

    private var textLink by mutableStateOf(textLink)

    private var iconPrimary by mutableStateOf(iconPrimary)

    var iconSecondary by mutableStateOf(iconSecondary)

    private var iconInteractive by mutableStateOf(iconInteractive)

    private var iconInteractiveInactive by mutableStateOf(iconInteractiveInactive)

    var error by mutableStateOf(error)

    private var notificationBadge by mutableStateOf(notificationBadge)

    private var isDark by mutableStateOf(isDark)

    fun update(other: CabifyColors) {
        gradient61 = other.gradient61
        gradient62 = other.gradient62
        gradient31 = other.gradient31
        gradient32 = other.gradient32
        gradient21 = other.gradient21
        gradient22 = other.gradient22
        gradient23 = other.gradient23
        brand = other.brand
        brandSecondary = other.brandSecondary
        uiBackground = other.uiBackground
        uiBorder = other.uiBorder
        uiFloated = other.uiFloated
        interactivePrimary = other.interactivePrimary
        interactiveSecondary = other.interactiveSecondary
        interactiveMask = other.interactiveMask
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textHelp = other.textHelp
        textInteractive = other.textInteractive
        textLink = other.textLink
        tornado1 = other.tornado1
        iconPrimary = other.iconPrimary
        iconSecondary = other.iconSecondary
        iconInteractive = other.iconInteractive
        iconInteractiveInactive = other.iconInteractiveInactive
        error = other.error
        notificationBadge = other.notificationBadge
        isDark = other.isDark
    }

    fun copy(): CabifyColors = CabifyColors(
        gradient61 = gradient61,
        gradient62 = gradient62,
        gradient31 = gradient31,
        gradient32 = gradient32,
        gradient21 = gradient21,
        gradient22 = gradient22,
        gradient23 = gradient23,
        brand = brand,
        brandSecondary = brandSecondary,
        uiBackground = uiBackground,
        uiBorder = uiBorder,
        uiFloated = uiFloated,
        interactivePrimary = interactivePrimary,
        interactiveSecondary = interactiveSecondary,
        interactiveMask = interactiveMask,
        textPrimary = textPrimary,
        textSecondary = textSecondary,
        textHelp = textHelp,
        textInteractive = textInteractive,
        textLink = textLink,
        tornado1 = tornado1,
        iconPrimary = iconPrimary,
        iconSecondary = iconSecondary,
        iconInteractive = iconInteractive,
        iconInteractiveInactive = iconInteractiveInactive,
        error = error,
        notificationBadge = notificationBadge,
        isDark = isDark,
    )
}

@Composable
fun ProvideCabifyColors(
    colors: CabifyColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalCabifyColors provides colorPalette, content = content)
}

private val LocalCabifyColors = staticCompositionLocalOf<CabifyColors> {
    error("No CabifyColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [CabifyTheme.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)
