package com.example.RestaurantOrderInfo.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity(name = "orderRestaurant")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Order {

    @Id
    @GeneratedValue
    @Column
    private long orderId;

    @Column
    private String name;
    @Column
    private int qty;
    @Column
    private double price;
    @Column
    private String status;//progress,completed
    @Column
    private String message;
}
