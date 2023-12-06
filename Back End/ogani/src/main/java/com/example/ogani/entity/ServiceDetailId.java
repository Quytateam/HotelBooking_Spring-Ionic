package com.example.ogani.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ServiceDetailId implements Serializable{
    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "dish_id")
    private Long dishId;

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    // Thêm setter cho importcouponId
    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }
}
