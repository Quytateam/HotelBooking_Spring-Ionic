package com.example.ogani.model.request;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateServiceRequest {
    
    @NotNull(message = "Đầu bếp rỗng")
    @Positive(message = "Đầu bếp rỗng")
    @Schema(description = "ID của đầu bếp",example="1")
    private long chefId;

    private String serviceDate;

    private List<CreateServiceDetailRequest> serviceDetail;
}
