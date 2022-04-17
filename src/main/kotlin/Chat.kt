package ru.netology

data class Chat(
    var chatId: Int = 0,
    val senderId: Int = 0,
    var recipientId: Int = 0,
    val messages: MutableList<Message> = mutableListOf()
    )
{
    data class Message(
        val messageId: Int = 0,
        val chatIdForMessages: Int = 0,
        val senderId: Int = 0,
        val recipientId: Int = 0,
        var text: String = "",
        var isRead: Boolean = false
    )
}