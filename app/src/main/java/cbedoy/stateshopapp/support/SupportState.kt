package cbedoy.stateshopapp.support

import zendesk.chat.ChatConfiguration
import zendesk.messaging.Engine

sealed class SupportState {
    object None: SupportState()
    data class StartChatWith(
            val configuration: ChatConfiguration,
            val engines: List<Engine>,
            val title: String,
            val description: String = ""
    ): SupportState()
}