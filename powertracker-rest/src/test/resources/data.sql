-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS power_item;

CREATE TABLE power_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(250) NOT NULL,
    model VARCHAR(250) NOT NULL,
    power_size VARCHAR(250) NOT NULL,
    power_type VARCHAR(250) NOT NULL,
    capacity VARCHAR(250) NOT NULL,
    available BOOLEAN NOT NULL,
    location VARCHAR(250) NOT NULL
);

INSERT INTO power_item (brand, model, power_size, power_type, capacity, available, location) VALUES
    ('Duracell', 'Energiser',  'AA', 'Ni-Cd', '2500', TRUE, 'Battery Box'),
    ('Rayovac', 'Hybrio',  'AA', 'Hybrid', '2200', TRUE, 'Battery Box'),
    ('Rayovac', 'Hybrio',  'AA', 'Hybrid', '2200', FALSE, 'Toy Car'),
    ('Rayovac', 'Hybrio',  'AA', 'Hybrid', '2200', FALSE, 'Toy Car'),
    ('Panasonic', 'LongLife',  'AAA', 'Hybrid', '1500', TRUE, 'Battery Box');