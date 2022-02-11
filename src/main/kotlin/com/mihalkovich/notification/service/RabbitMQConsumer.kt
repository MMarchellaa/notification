package com.mihalkovich.notification.service

import com.mihalkovich.notification.dto.ResultsDto
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets


@Component
class RabbitMQConsumer(
    val notificationService: NotificationService
) {

    @RabbitListener(queues = ["\${javainuse.rabbitmq.queue}"])
    fun recievedMessage(resultsDto: Message) {
        val message = toMap(String(resultsDto.body, StandardCharsets.UTF_8))
        val result = ResultsDto(message["{\"name\""], message["\"patronymic\""], message["\"surname\""],
            message["\"telephoneNumber\""], message["\"email\""], message["\"result\""]?.substringBefore('}').toBoolean())

        notificationService.manageNotification(result)
    }

    fun toMap(string: String): MutableMap<String, String>{
        val myMap: MutableMap<String, String> = HashMap()
        val pairs = string.split(",").toTypedArray()
        for (i in pairs.indices) {
            val pair = pairs[i]
            val keyValue = pair.split(":").toTypedArray()
            myMap[keyValue[0]] = keyValue[1]
        }
        return myMap
    }
}