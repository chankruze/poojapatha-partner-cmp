package geekofia.poojapatha.partner.cmp.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import geekofia.poojapatha.partner.cmp.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    companion object {
        private val KEY_AUTH_TOKEN = stringPreferencesKey("auth_token")
        private val KEY_LANGUAGE = stringPreferencesKey("language")
        private val KEY_THEME_MODE = stringPreferencesKey("theme_mode")
    }

    // ── Auth Token ────────────────────────────────────────────────────────────

    val authToken: Flow<String?> = dataStore.data.map { it[KEY_AUTH_TOKEN] }

    suspend fun setAuthToken(token: String) {
        dataStore.edit { it[KEY_AUTH_TOKEN] = token }
    }

    suspend fun clearAuthToken() {
        dataStore.edit { it.remove(KEY_AUTH_TOKEN) }
    }

    // ── Language ──────────────────────────────────────────────────────────────

    val language: Flow<String> = dataStore.data.map { it[KEY_LANGUAGE] ?: "en" }

    suspend fun setLanguage(languageCode: String) {
        dataStore.edit { it[KEY_LANGUAGE] = languageCode }
    }

    // ── Theme Mode ────────────────────────────────────────────────────────────

    val themeMode: Flow<ThemeMode> = dataStore.data.map { prefs ->
        prefs[KEY_THEME_MODE]
            ?.runCatching { ThemeMode.valueOf(this) }
            ?.getOrNull()
            ?: ThemeMode.LIGHT
    }

    suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { it[KEY_THEME_MODE] = mode.name }
    }
}
