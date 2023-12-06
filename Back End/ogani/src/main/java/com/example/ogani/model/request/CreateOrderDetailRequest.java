package com.example.ogani.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDetailRequest {

    private long dishId;

    @NotNull(message = "Số lượng món ăn rỗng")
    @NotEmpty(message = "Số lượng món ăn rỗng")
    @Size(min = 1,message="Số lượng món ăn từ 1 trở lên")
    private int quantity;
}
