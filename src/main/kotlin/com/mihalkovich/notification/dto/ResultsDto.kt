package com.mihalkovich.notification.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResultsDto(
    @JsonProperty
    var name: String? = null,
    @JsonProperty
    var patronymic: String? = null,
    @JsonProperty
    var surname: String? = null,
    @JsonProperty
    var telephoneNumber: String? = null,
    @JsonProperty
    var email: String? = null,
    @JsonProperty
    var result: Boolean? = false
): java.io.Serializable