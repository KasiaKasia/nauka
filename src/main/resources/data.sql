-- UCZEN
insert into uczniowie(imie, nazwisko, klasa)
values ('Władek', 'Szeran', 'IA'),
       ('Tomasz', 'Holenderski', 'IB'),
       ('Katarzyna', 'Kowalska', 'IC'),
       ('Nikola', 'Jakowska', 'IIA'),
       ('Natalia', 'Nowak', 'IIIA'),
       ('Adrian', 'Celik', 'IVC');
       
       
-- USERS
insert into users (username, password, non_expired, non_locked, credentials_non_expired, enabled)
    -- password: root
values ('admin', '$2a$12$8jfzYxoSl8YMmL3zllwhh.s66zVWd/Bvw5MxvvB7bQ18R37YA4NE6', true, true, true, true),
       -- password: pass
       ('edszer', '$2a$12$a89eFUCJqs2P6SRuTZIKh.M/dSYWO0UqrwbJq85xGGMCP8nklLNWi', true, true, true, true),
       -- password: piesek
       ('nikosia', '$2a$12$9Us4RW3EtjhzS5Iw6/LjK.6/6S5paZPY1scmfABl5OmRuz0BV.cOa.', true, true, true, true);



insert into authorities (username, authority)
values ('admin', 'ADMIN'),
       ('edszer', 'CUSTOMER'),
       ('nikosia', 'CUSTOMER');