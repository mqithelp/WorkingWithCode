package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoControllerInt;

@RestController
@RequestMapping("get")
public class InfoController {
    private final InfoControllerInt infoControllerInt;

    public InfoController(InfoControllerInt infoControllerInt) {
        this.infoControllerInt = infoControllerInt;
    }

    @GetMapping("/port")
    public Integer port() {
        return infoControllerInt.getPort();
    }

    @GetMapping("sum")
    public Long getSumParallel() {
        infoControllerInt.getSumCycle();
        return infoControllerInt.getSumParallel();
    }
}
