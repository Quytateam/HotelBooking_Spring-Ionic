package com.example.ogani.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest {

    private long userId;

    private long chefId;

    @NotNull(message="Tiêu đề rỗng")
    @NotEmpty(message = "Tiêu đề rỗng")
    @Size(min=5,max=300,message="Độ dài tiêu đề từ 1-300 ký tự")
    private String title;

    @NotNull(message = "Mô tả rỗng")
    @NotEmpty(message = "Mô tả rỗng")
    @Size(min=5,max=300,message= "Độ dài mô tả từ 1-300 ký tự")
    private String description;

    private String image;

    private String startTime;

    private String endTime;

    @NotNull(message = "Email rỗng")
    @NotEmpty(message = "Email rỗng")
    @Email(message = "Email không đúng định dạng")
    private String email;
     
    @NotNull(message="Số điện thoại rỗng")
    @NotEmpty(message="Số điện thoại rỗng")
    private String phone;

    private String address;
}
