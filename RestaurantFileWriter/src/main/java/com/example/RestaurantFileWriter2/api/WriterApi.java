package com.example.RestaurantFileWriter2.api;

import com.example.RestaurantFileWriter2.model.Order;
import com.example.RestaurantFileWriter2.service.FileWriterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
public class WriterApi {

    @Autowired
    private FileWriterServiceInterface service;

    @PostMapping("/write")
    public void orderWrite(@RequestBody Order order) throws IOException {
        service.writeFileMessage(order);
    }
}
