package com.ihowi.termuxagent.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

enum class Provider(val title: String) {
    OPENAI("OpenAI-совместимый"),
    ANTHROPIC("Anthropic"),
}

data class AgentSettings(
    val provider: Provider = Provider.OPENAI,
    val baseUrl: String = "https://api.openai.com/v1",
    val apiKey: String = "",
    val model: String = "",
) {
    val isConfigured: Boolean
        get() = apiKey.isNotBlank() && baseUrl.isNotBlank() && model.isNotBlank()
}

data class Preset(val name: String, val provider: Provider, val url: String, val model: String)

val Presets = listOf(
    Preset("OpenAI", Provider.OPENAI, "https://api.openai.com/v1", "gpt-4o-mini"),
    Preset("OpenRouter", Provider.OPENAI, "https://openrouter.ai/api/v1", "anthropic/claude-sonnet-4"),
    Preset("DeepSeek", Provider.OPENAI, "https://api.deepseek.com/v1", "deepseek-chat"),
    Preset("Groq", Provider.OPENAI, "https://api.groq.com/openai/v1", "llama-3.3-70b-versatile"),
    Preset("Anthropic", Provider.ANTHROPIC, "https://api.anthropic.com", "claude-sonnet-4-5"),
    Preset("LM Studio", Provider.OPENAI, "http://127.0.0.1:1234/v1", "local-model"),
)

class SettingsStore(private val context: Context) {
    private object Keys {
        val provider = stringPreferencesKey("provider")
        val baseUrl = stringPreferencesKey("base_url")
        val apiKey = stringPreferencesKey("api_key")
        val model = stringPreferencesKey("model")
    }

    val settings: Flow<AgentSettings> = context.dataStore.data.map { p ->
        val defaults = AgentSettings()
        AgentSettings(
            provider = p[Keys.provider]?.let { name -> Provider.entries.firstOrNull { it.name == name } }
                ?: defaults.provider,
            baseUrl = p[Keys.baseUrl] ?: defaults.baseUrl,
            apiKey = p[Keys.apiKey]?.let { KeyCipher.decrypt(it) } ?: "",
            model = p[Keys.model] ?: defaults.model,
        )
    }

    suspend fun save(s: AgentSettings) {
        context.dataStore.edit {
            it[Keys.provider] = s.provider.name
            it[Keys.baseUrl] = s.baseUrl.trim()
            it[Keys.apiKey] = KeyCipher.encrypt(s.apiKey.trim())
            it[Keys.model] = s.model.trim()
        }
    }
}
