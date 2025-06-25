package at.technikum.energycommunities.restapi;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "Backend funktiert!";
    }
}
