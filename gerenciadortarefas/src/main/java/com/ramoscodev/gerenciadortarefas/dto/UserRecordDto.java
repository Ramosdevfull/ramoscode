package com.ramoscodev.gerenciadortarefas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto( @NotBlank String username, @NotNull String password) {
    
}
