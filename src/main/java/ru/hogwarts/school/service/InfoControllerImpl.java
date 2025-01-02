package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Service
public class InfoControllerImpl implements InfoControllerInt {

    private final long K = 1_000_000;
    Logger logger = LoggerFactory.getLogger(InfoControllerImpl.class);

    @Override
    public Integer getPort() {
        return 0;
    }

    @Override
    public Long getSumCycle() {
        long sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < K + 1; i++) {
            sum += i;
        }
        long duration = System.currentTimeMillis() - start;
        logger.info("Время выполнения в цикле: " + duration + " мс");

        return sum;
    }

    @Override
    public Long getSumParallel() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, K) // Используем LongStream для работы с long
                .parallel() // Делаем поток параллельным
                .reduce(0L, Long::sum); // Суммируем значения
        long duration = System.currentTimeMillis() - start;
        logger.info("Время выполнения параллельного потока: " + duration + " мс");

        return sum;
    }
}
