package com.example.ogani.model.request;

import java.util.HashSet;
import java.util.Set;

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
public class CreateChefRequest {

    @NotNull(message = "Tên đầu bếp rỗng")
    @NotEmpty(message="Tên đầu bếp rỗng")
    @Schema(description = "Tên đầu bếp",example="Rondan",required=true)
    @Size(min=5,max=50,message="Tên đầu bếp từ 3-50 ký tự")
    private String name;

    @NotNull(message="Tên địa chỉ rỗng")
    @NotEmpty(message="Tên địa chỉ rỗng")
    private String address;

    @NotNull(message = "Đánh giá sao rỗng")
    @NotEmpty(message = "Đánh giá sao rỗng")
    @Schema(description = "Đánh giá sao",example="1.0")
    private float star;

    @NotNull(message = "Số lượng đánh giá rỗng")
    @NotEmpty(message = "Số lượng đánh giá rỗng")
    @Schema(description = "Số lượng đánh giá",example = "12")
    @Size(min=0,message="Số lượng đánh giá lớn hơn 0")
    private long review;

    @NotNull(message="Ảnh đang rỗng")
    @NotEmpty(message="Ảnh đang rỗng")
    private String image;

    @NotNull(message="Thời gian làm việc rỗng")
    @NotEmpty(message="Thời gian làm việc rỗng")
    private String workingTime;

    @NotNull(message = "Giá tiền thuê rỗng")
    @NotEmpty(message = "Giá tiền thuê rỗng")
    @Schema(description = "Giá tiền thuê đầu bếp",example = "12")
    @Size(min=0,message="Giá tiền thuê đầu bếp lớn hơn 0")
    private long price;

    @NotNull(message = "Khoảng cách rỗng")
    @NotEmpty(message = "Khoảng cách rỗng")
    @Schema(description = "Khoảng cách",example="1.0")
    private float distance;

    private Set<Long> dishs = new HashSet<>();
}
