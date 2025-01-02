package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfoControllerImpl implements InfoControllerInt {

    @Value("${server.port}")
    private Integer portServers;

    @Override
    public Integer getPort() {

        return portServers;
    }
}
