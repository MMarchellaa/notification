package com.mihalkovich.notification.service

import com.mihalkovich.notification.dto.ResultsDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Service

@Service
class NotificationService(
    var logger: Logger = LoggerFactory.getLogger(NotificationService::class.java)
) {
    fun manageNotification(resultsDto: ResultsDto){
        sendEmail(resultsDto.email, resultsDto.result)
        sendMessage(resultsDto.telephoneNumber, resultsDto.result)
    }

    fun sendEmail(email: String?, result: Boolean?){
        if (result == true) {
            logger.info("Congratulation email has been send to user: $email")
        }else {logger.info("Sad email has been send to user: $email")}
    }

    fun sendMessage(phone: String?, result: Boolean?){
        if (result == true) {
            logger.info("Congratulation message has been send to user: $phone")
        }else {logger.info("Sad message has been send to user: $phone")}
    }
}