package cbedoy.stateshopapp.support

import kotlinx.coroutines.flow.Flow

class SupportUseCase(
        private val supportChatService: SupportChatService
) {
    fun userWantsSupport(): Flow<SupportState> {
        return supportChatService.startChatWith(
                name = "Bedoy",
                email = "carlos.bedoy@gmail.com",
                phoneNumber = "4494674121",
                departmentName = "Development"
        )
    }
}