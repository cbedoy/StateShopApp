package cbedoy.stateshopapp.support

import android.content.Context
import com.zendesk.service.ErrorResponse
import com.zendesk.service.ZendeskCallback
import kotlinx.coroutines.flow.flow
import zendesk.chat.*


class SupportChatService(private val context: Context){
    init {
        Chat.INSTANCE.init(
            context,
            "1Z6ObnE7iPOfjIUsFXhjVAe1eypvmVaS",
            "a390413c7c12ed7dc79f346315670912bc75a0aea1e5d240"
        )

        val observationScope = ObservationScope()
        Chat.INSTANCE.providers()?.chatProvider()?.observeChatState(observationScope) { state ->
            println("Providers state")
            println(state)
        }
        Chat.INSTANCE.providers()?.accountProvider()?.observeAccount(observationScope) { state ->
            println("Providers state")
            println(state)
        }
        Chat.INSTANCE.providers()?.accountProvider()?.getAccount(object : ZendeskCallback<Account>() {
            override fun onSuccess(acount: Account?) {
                println("onSuccess")
                acount?.departments?.map {

                }
            }

            override fun onError(errorResponse: ErrorResponse?) {
                println("onError")
            }

        })
    }

    fun startChatWith(name: String, email: String, phoneNumber: String, departmentName: String) = flow{
        val visitorInfo = VisitorInfo.builder()
                .withName(name)
                .withEmail(email)
                .withPhoneNumber(phoneNumber).build()

        val chatProvidersConfiguration = ChatProvidersConfiguration.builder()
                .withVisitorInfo(visitorInfo)
                .withDepartment(departmentName)
                .build()

        val chatConfiguration = ChatConfiguration.builder()
                .withAgentAvailabilityEnabled(true)
                .withOfflineFormEnabled(false)
                .withPreChatFormEnabled(true)
                .withChatMenuActions(ChatMenuAction.END_CHAT).build()


        Chat.INSTANCE.chatProvidersConfiguration = chatProvidersConfiguration

        emit(
            SupportState.StartChatWith(
                engines = listOf(ChatEngine.engine()).mapNotNull { it },
                configuration = chatConfiguration,
                title = "Support"
            )
        )
    }
}