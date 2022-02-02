package com.example.RestaurantCatalog.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Order {

    private long orderId;
    private String name;
    private int qty;
    private double price;
    private String status; //progress,completed
    private String message;

}
