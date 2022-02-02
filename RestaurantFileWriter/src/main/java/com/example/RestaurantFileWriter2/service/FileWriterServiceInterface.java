package com.example.RestaurantFileWriter2.service;

import com.example.RestaurantFileWriter2.model.Order;

import java.io.IOException;

public interface FileWriterServiceInterface {
    void writeFileMessage(Order order) throws IOException;
}
