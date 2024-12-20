CREATE TABLE driver
(
    id          SERIAL PRIMARY KEY,
    age         INTEGER CHECK ( age > 18 ) NOT NULL,
    name        CHAR(50),
    license     boolean,
    id_register BIGINT
);
CREATE TABLE cars
(
    id          SERIAL PRIMARY KEY,
    age         INTEGER CHECK ( age > 18 ) NOT NULL,
    make        CHAR(50),
    model       CHAR(50),
    price       INTEGER CHECK ( price > 0 ),
    id_register BIGINT
);

CREATE TABLE driver_cars
(
    driver_id BIGINT,
    car_id    BIGINT,
    PRIMARY KEY (driver_id, car_id),
    FOREIGN KEY (driver_id) REFERENCES driver (id) ON DELETE CASCADE,
    FOREIGN KEY (car_id) REFERENCES cars (id) ON DELETE CASCADE
);