CREATE TABLE driver
(
    id          BIGSERIAL PRIMARY KEY,
    age         INTEGER CHECK ( age > 18 ) NOT NULL,
    name        VARCHAR(50),
    license     boolean
);
CREATE TABLE cars
(
    id          BIGSERIAL PRIMARY KEY,
    age         INTEGER CHECK ( age > 18 ) NOT NULL,
    make        VARCHAR(50),
    model       VARCHAR(50),
    price       INTEGER CHECK ( price > 0 )
);

CREATE TABLE driver_cars
(
    driver_id BIGINT,
    car_id    BIGINT,
    PRIMARY KEY (driver_id, car_id),
    FOREIGN KEY (driver_id) REFERENCES driver (id) ON DELETE CASCADE,
    FOREIGN KEY (car_id) REFERENCES cars (id) ON DELETE CASCADE
);