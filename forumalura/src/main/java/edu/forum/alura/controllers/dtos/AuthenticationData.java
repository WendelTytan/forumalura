package edu.forum.alura.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record AuthenticationData(@NotNull
                                 @JsonAlias("login")
                                 String email, @NotNull
                                 @JsonAlias("senha")
                                 String password) {
}
