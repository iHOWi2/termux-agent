package com.ihowi.termuxagent.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.ihowi.termuxagent.data.SettingsStore
import kotlinx.coroutines.launch

enum class Screen { Chat, Settings }

@Composable
fun AppRoot() {
    val context = LocalContext.current
    val store = remember { SettingsStore(context.applicationContext) }
    val settings by store.settings.collectAsState(initial = null)
    var screen by rememberSaveable { mutableStateOf(Screen.Chat) }
    val scope = rememberCoroutineScope()
    val current = settings ?: return

    when (screen) {
        Screen.Chat -> ChatScreen(
            settings = current,
            onOpenSettings = { screen = Screen.Settings },
        )
        Screen.Settings -> {
            BackHandler { screen = Screen.Chat }
            SettingsScreen(
                initial = current,
                onSave = {
                    scope.launch { store.save(it) }
                    screen = Screen.Chat
                },
                onBack = { screen = Screen.Chat },
            )
        }
    }
}
