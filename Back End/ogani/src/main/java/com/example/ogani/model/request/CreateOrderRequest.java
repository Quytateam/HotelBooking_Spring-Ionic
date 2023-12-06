package com.example.ogani.model.request;

import java.util.List;

import javax.validation.constraints.Email;
// import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    private long userId;

    private String address;

    @NotNull(message = "Email rỗng")
    @NotEmpty(message = "Email rỗng")
    @Email(message = "Email không đúng định dạng")
    private String email;
     
    private String phone;

    private long totalPrice;

    private List<CreateOrderDetailRequest> orderDetails;
    
}
