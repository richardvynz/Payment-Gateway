package com.example.gateway.DTOs.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {

    @NotNull @Schema(example = "richardvynz@gmail.com")
    private String email;
    @NotNull @Schema(example = "vincent")
    private String firstName;
    @NotNull @Schema(example = "richard")
    private String lastName;
    @NotNull @Schema(example = "1234")
    private String password;
    @NotNull @Schema(example = "1234")
    private String confirmPassword;
}
