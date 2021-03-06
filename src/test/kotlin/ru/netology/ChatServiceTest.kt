package ru.netology

import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    val service = ChatService()

    @Test
    fun addChat() {
        val add = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        //act
        val addedChat1 = service.addChat(1, add)
        // assert
        assertNotNull(addedChat1)
    }

    @Test
    fun addMessageToExistingChat() {
        // arrange
        val addedChat2 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage1 = Chat.Message(
            messageId = 1,
            chatIdForMessages = 1,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = false
        )
        //act
        service.addChat(1, addedChat2)
        val result = service.addMessage(1, addedMessage1)
        assertNotNull(result)
    }

    @Test
    fun addMessageWithNoChat() {
        // arrange
        val addedMessage3 = Chat.Message(
            messageId = 1,
            chatIdForMessages = 1,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = false
        )
        //act
        val result = service.addMessage(1, addedMessage3)
        assertNotNull(result)
    }

    @Test
    fun getChats() {
        // arrange
        val addedChat3 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage4 = Chat.Message(
            messageId = 1,
            chatIdForMessages = 1,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = false
        )
        val addedMessage5 = Chat.Message(
            messageId = 2,
            chatIdForMessages = 1,
            senderId = 1,
            recipientId = 1,
            text = "Love saves the world!",
            isRead = false
        )
        val addedChat4 = Chat(
            chatId = 2,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage6 = Chat.Message(
            messageId = 1,
            chatIdForMessages = 2,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = true
        )
        val addedMessage7 = Chat.Message(
            messageId = 2,
            chatIdForMessages = 2,
            senderId = 1,
            recipientId = 1,
            text = "Love saves the world!",
            isRead = true
        )
        //act
        service.addChat(1, addedChat3)
        service.addMessage(1, addedMessage4)
        service.addMessage(1, addedMessage5)
        service.addChat(1, addedChat4)
        service.addMessage(1, addedMessage6)
        service.addMessage(1, addedMessage7)
        val result = service.getChats(1)
        assertNotNull(result)
    }

    @Test(expected = MessageNotFoundException::class)
    fun shouldThrow() {
        // arrange
        val addedChat5 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        val addedChat6 = Chat(
            chatId = 2,
            senderId = 1,
            recipientId = 1,
        )
        //act
        service.addChat(1, addedChat5)
        service.addChat(1, addedChat6)
        val result = service.getChats(1)
        assertNull(result)
    }

    @Test
    fun getUnreadChatsCount() {
        val addedChat7 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage8 = Chat.Message(
            messageId = 1,
            chatIdForMessages = 1,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = false
        )

        val addedChat8 = Chat(
            chatId = 2,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage9 = Chat.Message(
            messageId = 2,
            chatIdForMessages = 2,
            senderId = 1,
            recipientId = 1,
            text = "Love saves the world!",
            isRead = true
        )
        //act
        service.addChat(1, addedChat7)
        service.addMessage(1, addedMessage8)
        service.addChat(1, addedChat8)
        service.addMessage(1, addedMessage9)
        val result = service.getUnreadChatsCount(1)
        assertEquals(1, result)
    }

    @Test
    fun editMessage() {
        val addedChat9 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage10 = Chat.Message(
            messageId = 1,
            chatIdForMessages = 1,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = false
        )
        val addedMessage11 = Chat.Message(
            messageId = addedMessage10.messageId,
            chatIdForMessages = 1,
            senderId = 1,
            recipientId = 1,
            text = "Love saves the world!",
            isRead = false
        )

        service.addChat(1, addedChat9)
        service.addMessage(1, addedMessage10)
        service.editMessage(addedMessage11)
    }

    @Test
    fun deleteChat() {
        val addedChat10 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage12 = Chat.Message(
            messageId = 1,
            chatIdForMessages = addedChat10.chatId,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = false
        )
        service.addChat(1, addedChat10)
        service.addMessage(1, addedMessage12)
        service.deleteChat(1)
    }

    @Test
    fun deleteMessage() {
        val addedChat11 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage13 = Chat.Message(
            messageId = 1,
            chatIdForMessages = addedChat11.chatId,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = false
        )
        service.addChat(1, addedChat11)
        service.addMessage(1, addedMessage13)
        service.deleteMessage(1, 1)
    }

    @Test(expected = ChatNotFoundException::class)
    fun throwChatNotFound() {
        val addedChat12 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        service.addChat(1, addedChat12)
        service.deleteMessage(3, 1)
    }

    @Test
    fun getMessages() {
        val addedChat13 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage14 = Chat.Message(
            messageId = 1,
            chatIdForMessages = addedChat13.chatId,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = true
        )
        val addedMessage15 = Chat.Message(
            messageId = 2,
            chatIdForMessages = addedChat13.chatId,
            senderId = 1,
            recipientId = 1,
            text = "Love saves the world!",
            isRead = false
        )
        val addedMessage16 = Chat.Message(
            messageId = 3,
            chatIdForMessages = addedChat13.chatId,
            senderId = 1,
            recipientId = 1,
            text = "You can win if you want!",
            isRead = false
        )

        service.addChat(1, addedChat13)
        service.addMessage(1, addedMessage14)
        service.addMessage(1, addedMessage15)
        service.addMessage(1, addedMessage16)
        val result = service.getMessages(1, 3, 2, 1)
        assertNotNull(result)
    }

    @Test(expected = ChatNotFoundException::class)
    fun throwChatNotFoundException() {
        val addedChat14 = Chat(
            chatId = 1,
            senderId = 1,
            recipientId = 1,
        )
        val addedMessage17 = Chat.Message(
            messageId = 1,
            chatIdForMessages = addedChat14.chatId,
            senderId = 1,
            recipientId = 1,
            text = "Hakuna Matata",
            isRead = true
        )
        service.addChat(1, addedChat14)
        service.addMessage(1, addedMessage17)
        service.getMessages(2, 3, 2, 2)
    }
}