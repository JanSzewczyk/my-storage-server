-- CREATE USERS password: zaq1@WSXcde3
INSERT INTO users(user_id, email, password)
VALUES ('02346256-44b2-4811-917a-6ad03c56036e', 'jan.szewczyk1997@gmail.com', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm'),
    ('3272d037-bd1f-4cc2-8991-20587bb97048', 'jan.kowalski997@gmail.com', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm');


INSERT INTO owner(owner_id, created_at, email, first_name, last_name, password, phone, updated_at)
VALUES ('02346256-44b2-4811-917a-6ad03c56036e', '2016-05-06 19:53:58.000000', 'jan.szewczyk1997@gmail.com', 'Jan', 'Szewczyk', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48516605828', '2019-08-06 21:54:09.000000'),
       ('3272d037-bd1f-4cc2-8991-20587bb97048', '2020-05-01 14:43:58.000000', 'mietek123@gmail.com', 'Mieczysław', 'Akumulatorski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48531329239', '2020-05-06 21:54:09.000000');