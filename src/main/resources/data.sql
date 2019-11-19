-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS inventory;

CREATE TABLE inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(250) NOT NULL,
    model VARCHAR(250) NOT NULL,
    power_size VARCHAR(250) NOT NULL,
    power_type VARCHAR(250) NOT NULL,
    capacity VARCHAR(250) NOT NULL,
    available CHAR(1) NOT NULL,
    location VARCHAR(250) NOT NULL
);

INSERT INTO inventory (brand, model, power_size, power_type, capacity, available, location) VALUES
    ('Duracell', 'Energiser',  'AA', 'Ni-Cd', '2500', 'Y', 'Battery Box'),
    ('Rayovac', 'Hybrio',  'AA', 'Hybrid', '2200', 'Y', 'Battery Box'),
    ('Rayovac', 'Hybrio',  'AA', 'Hybrid', '2200', 'N', 'Toy Car'),
    ('Rayovac', 'Hybrio',  'AA', 'Hybrid', '2200', 'N', 'Toy Car'),
    ('Panasonic', 'LongLife',  'AAA', 'Hybrid', '1500', 'Y', 'Battery Box');