package geekofia.poojapatha.partner.cmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import geekofia.poojapatha.partner.cmp.navigation.AppNavHost
import geekofia.poojapatha.partner.cmp.ui.theme.PoojaPathaPartnerTheme
import geekofia.poojapatha.partner.cmp.ui.theme.ThemeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val themeViewModel: ThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeMode by themeViewModel.themeMode.collectAsStateWithLifecycle()
            PoojaPathaPartnerTheme(themeMode = themeMode) {
                AppNavHost(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
