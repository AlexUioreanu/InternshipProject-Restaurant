package com.example.RestaurantFileWriter2.service;

import com.example.RestaurantFileWriter2.model.Order;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
@NoArgsConstructor
public class FileWriterService implements FileWriterServiceInterface {


    @Value("${demo.application.write.paths}")
    private String fileLocation;
    private BufferedWriter bw;

    @Override
    public void writeFileMessage(Order order) throws IOException {
        bw = new BufferedWriter(new FileWriter(fileLocation, true));
        bw.write("Message recieved from queue : ");
        bw.newLine();
        bw.write(String.valueOf(order));
        bw.newLine();
        bw.close();
    }
}

