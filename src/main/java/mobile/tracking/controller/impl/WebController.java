package mobile.tracking.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Hrisi on 1.12.2019 ã..
 */
@Controller
public class WebController {

    @RequestMapping("/home")
    public String home() {
        return "home.html";
    }
}
