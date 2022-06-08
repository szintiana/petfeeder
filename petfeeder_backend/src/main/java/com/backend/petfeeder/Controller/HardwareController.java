package com.backend.petfeeder.Controller;

import com.backend.petfeeder.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HardwareController {
    @Autowired
    @Lazy
    HardwareService hardwareService;

    @GetMapping("/feed")
    public String feed() throws IOException {
        String a = hardwareService.sendMessage("C:\\Users\\szint\\IdeaProjects\\petfeeder\\raspberryPiDocuments\\mqttTest.py");
        System.out.println(a);
        return a;
    }
}
