package edu.forum.alura.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateDto(
        @NotNull
        @JsonAlias("titulo")
        String title,

        @NotNull
        @JsonAlias("mensagem")
        String content,

        @JsonAlias("status")
        Boolean status
) {

}
