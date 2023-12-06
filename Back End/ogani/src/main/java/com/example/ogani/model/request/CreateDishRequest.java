package com.example.ogani.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDishRequest {

    @NotNull(message="Ảnh đang rỗng")
    @NotEmpty(message="Ảnh đang rỗng")
    private String image;

    @NotNull(message = "Tên món ăn rỗng")
    @NotEmpty(message="Tên món ăn rỗng")
    @Schema(description = "Tên món ăn",example="Product1",required=true)
    @Size(min=5,max=50,message="Tên món ăn từ 3-50 ký tự")
    private String name;

    @NotNull(message = "Giá tiền rỗng")
    @NotEmpty(message = "Giá tiền rỗng")
    @Schema(description = "Giá món ăn",example = "12")
    @Size(min=0,message="Giá tiền món ăn lớn hơn 0")
    private long price;

    @NotNull(message = "Danh mục rỗng")
    @NotEmpty(message = "Danh mục rỗng")
    @Schema(description = "ID của danh mục",example="1")
    private long categoryId;

    @NotNull(message = "Thời gian rỗng")
    @NotEmpty(message="Thời gian rỗng")
    @Schema(description = "Thời gian",example="Product1",required=true)
    @Size(min=5,max=50,message="Thời gian từ 3-50 ký tự")
    private String time;

    @NotNull(message = "Khoảng cách rỗng")
    @NotEmpty(message = "Khoảng cách rỗng")
    @Schema(description = "Khoảng cách",example="1.0")
    private float distance;

    @NotNull(message = "Số lượng sản phẩm")
    @NotEmpty(message="Số lượng sản phẩm")
    @Schema(description = "Số lượng sản phẩm",example="12")
    @Size(min=0,message="Số lượng sản phẩm từ 0")
    private int quantity;

    @NotNull(message = "Mô tả rỗng")
    @NotEmpty(message="Mô tả rỗng")
    @Schema(description = "Mô tả sản phẩm",example="Đây là sản phẩm thứ 1")
    @Size(min=5,max=1000,message="Mô tả sản phẩm từ 5-1000 ký tự")
    private String description;
}
