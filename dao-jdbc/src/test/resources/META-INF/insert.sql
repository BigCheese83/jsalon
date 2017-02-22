INSERT INTO discount(id, created, modified, version, name, value, description)
VALUES (nextval('discount_id_seq'), current_timestamp, current_timestamp, 1, 'Серебрянная', 3, NULL);
INSERT INTO discount(id, created, modified, version, name, value, description)
VALUES (nextval('discount_id_seq'), current_timestamp, current_timestamp, 1, 'Золотая', 5, NULL);
INSERT INTO discount(id, created, modified, version, name, value, description)
VALUES (nextval('discount_id_seq'), current_timestamp, current_timestamp, 1, 'Сезонная', 10, NULL);
INSERT INTO discount(id, created, modified, version, name, value, description)
VALUES (nextval('discount_id_seq'), current_timestamp, current_timestamp, 1, 'VIP', 20, NULL);

INSERT INTO post(id, created, modified, version, name, description)
VALUES (nextval('post_id_seq'), current_timestamp, current_timestamp, 1, 'Мастер-универсал', NULL);
INSERT INTO post(id, created, modified, version, name, description)
VALUES (nextval('post_id_seq'), current_timestamp, current_timestamp, 1, 'Парикмахер', NULL);
INSERT INTO post(id, created, modified, version, name, description)
VALUES (nextval('post_id_seq'), current_timestamp, current_timestamp, 1, 'Массажист', NULL);
INSERT INTO post(id, created, modified, version, name, description)
VALUES (nextval('post_id_seq'), current_timestamp, current_timestamp, 1, 'Косметолог', NULL);
INSERT INTO post(id, created, modified, version, name, description)
VALUES (nextval('post_id_seq'), current_timestamp, current_timestamp, 1, 'Стилист', NULL);

INSERT INTO service(id, created, modified, version, category, name, cost, duration, description)
VALUES (nextval('service_id_seq'), current_timestamp, current_timestamp, 1, 'Макияж', 'Макияж', 700.5, 30, NULL);
INSERT INTO service(id, created, modified, version, category, name, cost, duration, description)
VALUES (nextval('service_id_seq'), current_timestamp, current_timestamp, 1, 'Окрашивание волос', 'Колорирование волос',  1900, 20, NULL);
INSERT INTO service(id, created, modified, version, category, name, cost, duration, description)
VALUES (nextval('service_id_seq'), current_timestamp, current_timestamp, 1, 'Женская стрижка', 'Вечерняя прическа', 2200.5, 30, NULL);
INSERT INTO service(id, created, modified, version, category, name, cost, duration, description)
VALUES (nextval('service_id_seq'), current_timestamp, current_timestamp, 1, 'Маски для лица', 'Неинвазивная карбокситерапия', 1500, 40, NULL);
INSERT INTO service(id, created, modified, version, category, name, cost, duration, description)
VALUES (nextval('service_id_seq'), current_timestamp, current_timestamp, 1, 'Уход за волосами', 'Ламинирование волос', 2000, 55, 'краска Loreal');

INSERT INTO post_service(post_id, service_id) VALUES (1, 3);
INSERT INTO post_service(post_id, service_id) VALUES (2, 3);
INSERT INTO post_service(post_id, service_id) VALUES (4, 4);

INSERT INTO address(id, created, modified, version, country, district, city, street, house, section, flat, zip)
VALUES (nextval('address_id_seq'), current_timestamp, current_timestamp, 1, 'Россия', NULL, 'Москва', 'Южно-портовый проезд', '14', NULL, '12', NULL);
INSERT INTO address(id, created, modified, version, country, district, city, street, house, section, flat, zip)
VALUES (nextval('address_id_seq'), current_timestamp, current_timestamp, 1, 'Россия', 'Ленинградская область', 'Санкт-Петербург', 'Ленина', '32A', '1', '121', NULL);
INSERT INTO address(id, created, modified, version, country, district, city, street, house, section, flat, zip)
VALUES (nextval('address_id_seq'), current_timestamp, current_timestamp, 1, 'Россия', 'Москва', 'Москва', 'пр-т Мира', '122', NULL, '37', '131677');
INSERT INTO address(id, created, modified, version, country, district, city, street, house, section, flat, zip)
VALUES (nextval('address_id_seq'), current_timestamp, current_timestamp, 1, 'Россия', 'Московская обл.', 'Королев', 'Ленина', '27', NULL, '101', '141222');

INSERT INTO contact(id, created, modified, version, phone, email, vk, skype, facebook, twitter, icq)
VALUES (nextval('contact_id_seq'), current_timestamp, current_timestamp, 1, '+7(922)543-67-66', 'ivanov@mail.ru', NULL, NULL, NULL, NULL, NULL);
INSERT INTO contact(id, created, modified, version, phone, email, vk, skype, facebook, twitter, icq)
VALUES (nextval('contact_id_seq'), current_timestamp, current_timestamp, 1, '+7(916)453-37-26', 'maria33@yandex.ru', 'maria33', 'mashka33', NULL, NULL, NULL);
INSERT INTO contact(id, created, modified, version, phone, email, vk, skype, facebook, twitter, icq)
VALUES (nextval('contact_id_seq'), current_timestamp, current_timestamp, 1, '+7(919)444-62-62', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO contact(id, created, modified, version, phone, email, vk, skype, facebook, twitter, icq)
VALUES (nextval('contact_id_seq'), current_timestamp, current_timestamp, 1, '+7(922)930-00-66', 'chutov@gmail.com', NULL, 'chutik', 'chute', NULL, NULL);
INSERT INTO contact(id, created, modified, version, phone, email, vk, skype, facebook, twitter, icq)
VALUES (nextval('contact_id_seq'), current_timestamp, current_timestamp, 1, '+7(985)777-00-00', 'shvedov@sberbank.ru', NULL, NULL, 'sanyok', 'sancho', NULL);
INSERT INTO contact(id, created, modified, version, phone, email, vk, skype, facebook, twitter, icq)
VALUES (nextval('contact_id_seq'), current_timestamp, current_timestamp, 1, '+7(916)423-45-78', 'egorova@mail.ru', NULL, NULL, NULL , NULL, NULL);
INSERT INTO contact(id, created, modified, version, phone, email, vk, skype, facebook, twitter, icq)
VALUES (nextval('contact_id_seq'), current_timestamp, current_timestamp, 1, '+7(926)653-34-84', 'bichuk@yandex.ru', NULL, NULL, 'bichuk92' , NULL, NULL);

INSERT INTO passport(id, created, modified, version, series, num, issued_by, issue_date, subdivision, country)
VALUES (nextval('passport_id_seq'), current_timestamp, current_timestamp, 1, '0123', '123456', 'ОВД г.Москвы №113-32', '1995-05-12', '033-235', 'Россия');
INSERT INTO passport(id, created, modified, version, series, num, issued_by, issue_date, subdivision, country)
VALUES (nextval('passport_id_seq'), current_timestamp, current_timestamp, 1, '4567', '456789', 'ОВД г.Калининграда', '1985-01-02', NULL, 'Россия');
INSERT INTO passport(id, created, modified, version, series, num, issued_by, issue_date, subdivision, country)
VALUES (nextval('passport_id_seq'), current_timestamp, current_timestamp, 1, '3568', '001122', 'ОВД г.Санкт-Петербург №153-34', '2000-05-22', '042-112', 'Россия');
INSERT INTO passport(id, created, modified, version, series, num, issued_by, issue_date, subdivision, country)
VALUES (nextval('passport_id_seq'), current_timestamp, current_timestamp, 1, '0123', '102030', 'ОВД г.Калининграда', '1984-06-13', NULL, 'Россия');
INSERT INTO passport(id, created, modified, version, series, num, issued_by, issue_date, subdivision, country)
VALUES (nextval('passport_id_seq'), current_timestamp, current_timestamp, 1, '' , '123456', 'ОВД г.Москвы №113-32', '2003-03-19', NULL, 'Россия');
INSERT INTO passport(id, created, modified, version, series, num, issued_by, issue_date, subdivision, country)
VALUES (nextval('passport_id_seq'), current_timestamp, current_timestamp, 1, '4233' , '4567', 'ОВД г.Москвы №043-32', '2007-05-19', NULL, 'Россия');

INSERT INTO person(id, created, modified, version, surname, name, patronymic, birth_date, passport_id, reg_address_id, live_address_id, contact_id)
VALUES (nextval('person_id_seq'), current_timestamp, current_timestamp, 1, 'Иванов', 'Владислав', 'Сергеевич', '1973-01-23', 1, 1, 1, 1);
INSERT INTO person(id, created, modified, version, surname, name, patronymic, birth_date, passport_id, reg_address_id, live_address_id, contact_id)
VALUES (nextval('person_id_seq'), current_timestamp, current_timestamp, 1, 'Морозова', 'Мария', 'Александровна', '1982-11-01', 5, 3, 3, 2);
INSERT INTO person(id, created, modified, version, surname, name, patronymic, birth_date, passport_id, reg_address_id, live_address_id, contact_id)
VALUES (nextval('person_id_seq'), current_timestamp, current_timestamp, 1, 'Шведов', 'Александр', 'Вячеславович', '1979-10-08', 3, 2, 2, 5);