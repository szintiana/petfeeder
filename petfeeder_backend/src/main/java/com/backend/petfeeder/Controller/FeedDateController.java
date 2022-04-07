package com.backend.petfeeder.Controller;

import com.backend.petfeeder.DTO.FeedDateDTO;
import com.backend.petfeeder.Service.FeedDateService;
import com.backend.petfeeder.Utils.MQTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class FeedDateController {
    @Autowired
    @Lazy
    MQTTService mqttService;

    @Autowired
    @Lazy
    FeedDateService feedDateService;

    @PostMapping("addFeedDate")
    public ResponseEntity<FeedDateDTO> add(FeedDateDTO feedDateDTO) {
        FeedDateDTO addedElement = feedDateService.addFeedDate(feedDateDTO);
        if (addedElement != null) {
            return new ResponseEntity<>(feedDateDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @Bean
    public void launchFeedDateController() {
        mqttService.startMqtt();
        mqttService.subscribe("petfeeder");
        runTasks();
    }

    public void runTasks() {
        List<FeedDateDTO> feedDateList = feedDateService.getAll();
        Timer timer = new Timer();
        if (feedDateList != null && !feedDateList.isEmpty()) {
            for (FeedDateDTO feedDateDTO : feedDateList) {
                LocalDateTime dateTime = feedDateDTO.getDate();
                Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                Date date = Date.from(instant);
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        mqttService.publish("petfeederApp", "feed");
                    };
                };
                timer.schedule(timerTask, date);
            }
        }
    }
}
