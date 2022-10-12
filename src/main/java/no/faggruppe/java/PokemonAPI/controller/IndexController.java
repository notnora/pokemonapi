package no.faggruppe.java.PokemonAPI.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/")
public class IndexController {
    @GetMapping("/api/index")
    public String hello() {
        log.info("Hello, Kanto!");
        return "Hello, Kanto!";
    }
}
