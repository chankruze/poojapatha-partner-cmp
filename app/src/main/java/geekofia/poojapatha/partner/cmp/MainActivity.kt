package geekofia.poojapatha.partner.cmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import geekofia.poojapatha.partner.cmp.navigation.AppNavHost
import geekofia.poojapatha.partner.cmp.ui.theme.PoojaPathaPartnerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PoojaPathaPartnerTheme {
                AppNavHost(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
