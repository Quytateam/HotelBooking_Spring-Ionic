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
public class CreateBlogRequest {

    @NotNull(message="Ảnh đang rỗng")
    @NotEmpty(message="Ảnh đang rỗng")
    private String image;
    
    @NotNull(message="Tiêu đề rỗng")
    @NotEmpty(message = "Tiêu đề rỗng")
    @Size(min=5,max=300,message="Độ dài tiêu đề từ 1-300 ký tự")
    private String title;


    @NotNull(message = "Mô tả rỗng")
    @NotEmpty(message = "Mô tả rỗng")
    @Size(min=5,max=300,message= "Độ dài mô tả từ 1-300 ký tự")
    private String description;

    @NotNull(message = "Danh mục rỗng")
    @NotEmpty(message = "Danh mục rỗng")
    @Schema(description = "ID của danh mục",example="1")
    private long categoryId;

}
