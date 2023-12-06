package com.example.ogani.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateServiceDetailRequest {
    @NotNull(message = "Món ăn rỗng")
    @Positive(message = "Món ăn rỗng")
    @Schema(description = "ID của Món ăn",example="1")
    private long dishId;

    private String time;
}
