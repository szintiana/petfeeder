package com.backend.petfeeder.Controller;

import com.backend.petfeeder.DTO.FeedDateDTO;
import com.backend.petfeeder.Service.FeedDateService;
import com.backend.petfeeder.Utils.MQTTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeedDateController {
    @Autowired
    @Lazy
    MQTTUtil mqttUtil;

    @Autowired
    @Lazy
    FeedDateService feedDateService;

    @PostMapping("addFeedDate/{email}/{petName}/{date}")
    public ResponseEntity<FeedDateDTO> addFeedDate(@PathVariable("email") String email,
                                                   @PathVariable("petName") String petName,
                                                   @PathVariable("date") String date) {
        FeedDateDTO addedElement = feedDateService.addFeedDate(email, petName, date);
        if (addedElement != null) {
            return new ResponseEntity<>(addedElement, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @DeleteMapping("removeFeedDate/{email}/{petName}/{date}")
    public void removeFeedDate(@PathVariable("email") String email, @PathVariable("petName") String petName,
                               @PathVariable("date") LocalDateTime date) {
        feedDateService.removeFeedDate(email, date, petName);
    }

    @Bean
    public void launchFeedDateController()  {
        mqttUtil.startMqtt();
        mqttUtil.subscribe("petfeeder");
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
                        mqttUtil.publish("petfeederApp", "feed");
                    };
                };
                timer.schedule(timerTask, date);
            }
        }
    }
}
