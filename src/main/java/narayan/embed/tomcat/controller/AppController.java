package narayan.embed.tomcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A sample controller for demo
 *
 * @author narayana
 */
@RestController
public class AppController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from App";
    }
}
