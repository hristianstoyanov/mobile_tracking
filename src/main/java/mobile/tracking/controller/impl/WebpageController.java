package mobile.tracking.controller.impl;

import mobile.tracking.model.dummy.Message;
import mobile.tracking.model.dummy.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * Created by Hrisi on 1.12.2019 �..
 */
@Controller
public class WebpageController {

    @RequestMapping("/home")
    public String home() {
        return "home.html";
    }

    @RequestMapping("/testChat")
    public String chat() {
        return "test.html";
    }

}
