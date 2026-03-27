package geekofia.poojapatha.partner.cmp.ui.theme

/**
 * Three display themes, mirroring the React Native app.
 *
 *  - LIGHT  — warm saffron palette, light backgrounds
 *  - DARK   — warm saffron palette, dark backgrounds
 *  - OCEAN  — cool teal/blue palette, dark backgrounds
 */
enum class ThemeMode {
    LIGHT, DARK, OCEAN;

    /** True for themes that use a dark background — drives status-bar tint. */
    val isDark: Boolean get() = this == DARK || this == OCEAN
}
