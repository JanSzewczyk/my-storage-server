
INSERT INTO owner(owner_id, created_at, email, first_name, last_name, password, phone, updated_at)
VALUES ('02346256-44b2-4811-917a-6ad03c56036e', '2016-05-06 19:53:58.000000', 'jan.szewczyk1997@gmail.com', 'Jan', 'Szewczyk', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48516605828', '2019-08-06 21:54:09.000000'),
       ('3272d037-bd1f-4cc2-8991-20587bb97048', '2020-05-01 14:43:58.000000', 'mietek123@gmail.com', 'Mieczysław', 'Akumulatorski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48531329239', '2020-05-06 21:54:09.000000');

INSERT INTO employee(employee_id, address_city, address_country, address_street, address_zip, created_at, email, first_name, last_name, password, phone, updated_at, owner_id)
VALUES ('25324e68-7f35-47f0-b091-2077fd8f8299', 'Turawa', 'Poland', 'ul. Opolska 64', '46-045', '2019-08-16 14:53:09.000000', 'hans@gmail.com', 'Hans', 'Kloss', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48385940285', '2020-01-16 09:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e'),
       ('9b71ea65-e5af-4ac4-a175-e3b375b61d07', 'Łódź', 'Poland', 'ul. Natalii 1', '93-318', '2020-01-19 11:05:09.000000', 'arnold123@gmail.com', 'Arnold', 'Schwarzenegger', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+4858395638', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e')

