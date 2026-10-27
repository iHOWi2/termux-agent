# Termux Agent

Android AI chat that is actually an agent: it runs commands in Termux, reads and edits files
inside a single workspace folder (~/agent-workspace), checks results, fixes errors and reports back.

## Install
1. Download the latest APK from **Releases**.
2. Install Termux from F-Droid or GitHub (not Google Play).
3. In Termux: `echo "allow-external-apps = true" >> ~/.termux/termux.properties && termux-reload-settings`
4. Android Settings -> Apps -> Termux Agent -> Permissions -> allow "Run commands in Termux environment".
5. Open the app -> Settings -> enter endpoint, API key and model.

Works with any OpenAI-compatible API (OpenAI, OpenRouter, DeepSeek, Groq, LM Studio, proxies) and native Anthropic.

Author: [iHOWi2](https://github.com/iHOWi2)
