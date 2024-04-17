-- Create the phone table
CREATE TABLE phone (
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(255),
                        availability BOOLEAN,
                        booked_by VARCHAR(255),
                        booked_at DATETIME
);

-- Insert statements for the phone
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (1, 'Samsung Galaxy S9', true, '', null);
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (2, 'Samsung Galaxy S8', true, '', null);
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (3, 'Samsung Galaxy S8', true, '', null);
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (4, 'Motorola Nexus 6', true, '', null);
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (5, 'Oneplus 9', true, '', null);
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (6, 'Apple iPhone 13', true, '', null);
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (7, 'Apple iPhone 12', true, '', null);
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (8, 'Apple iPhone 11', true, '', null);
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (9, 'iPhone X', true, '', null);
INSERT INTO phone (id, name, availability, booked_by, booked_at) VALUES (10, 'Nokia 3310', true, '', null);
