
INSERT INTO owner(owner_id, created_at, email, first_name, last_name, password, phone, updated_at)
VALUES ('02346256-44b2-4811-917a-6ad03c56036e', '2016-05-06 19:53:58.000000', 'jan.szewczyk1997@gmail.com', 'Jan', 'Szewczyk', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48516605828', '2019-08-06 21:54:09.000000'),
       ('3272d037-bd1f-4cc2-8991-20587bb97048', '2020-05-01 14:43:58.000000', 'mietek123@gmail.com', 'Mieczysław', 'Akumulatorski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48531329239', '2020-05-06 21:54:09.000000');

INSERT INTO storage(storage_id, address_city, address_country, address_street, address_zip, created_at, name, surface, updated_at, owner_id)
VALUES ('3a78e3ed-bd55-4a09-b7f8-77fded2ff23e', 'Kraków', 'Poland', 'ul Warszawska 21', '31-476', '2020-05-11 22:13:02.202000', 'Old workshop', 312, '2020-05-11 22:13:02.202000', '02346256-44b2-4811-917a-6ad03c56036e');

INSERT INTO employee(employee_id, address_city, address_country, address_street, address_zip, created_at, email, first_name, last_name, password, phone, updated_at, owner_id, storage_id)
VALUES ('25324e68-7f35-47f0-b091-2077fd8f8299', 'Turawa', 'Poland', 'ul. Opolska 64', '46-045', '2019-08-16 14:53:09.000000', 'hans@gmail.com', 'Hans', 'Kloss', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48385940285', '2020-01-16 09:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('9b71ea65-e5af-4ac4-a175-e3b375b61d07', 'Łódź', 'Poland', 'ul. Natalii 1', '93-318', '2020-01-19 11:05:09.000000', 'arnold123@gmail.com', 'Arnold', 'Schwarzenegger', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+4858395638', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('eb0a075c-1713-46a9-9969-3a034cfac5b2', 'Szczecin', 'Poland', 'ul. Janosika 8705', '71-424', '2020-01-19 11:05:09.000000', 'zygfrydDbrowski@tieinstructions.pl', 'Zygfryd', 'Dąbrowski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48706143569', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('b39cb663-3526-4bef-b96c-4a2fd8748459', 'Wrocław', 'Poland', 'ul. Żubrza 9921', '54-229', '2020-01-19 11:05:09.000000', 'eytaOlszewska@oilguides.pl', 'Edyta', 'Olszewska', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48214749744', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('09e40da5-4c8d-4207-9bec-daa280b581ed', 'Gdańsk', 'Poland', 'ul. Minogi 3437', '80-840', '2020-01-19 11:05:09.000000', 'walentyWojciechowski@instantfever.pl', 'Walenty', 'Wojciechowski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48217556941', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('90474b39-7914-4c30-b239-07e893aa0460', 'Wrocław', 'Poland', 'Pl. Świętego Krzysztofa 574', '50-056', '2020-01-19 11:05:09.000000', 'hieronimCzerwinski@airlinemail.pl', 'Hieronim', 'Czerwinski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48438774976', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('554e24e2-2ac3-479c-adfe-b953ac722952', 'Kraków', 'Poland', 'ul. Profesora Seweryna Tadeusza 8091', '30-632', '2020-01-19 11:05:09.000000', 'lukaszWiniewski@maternityconsultant.pl', 'Łukasz', 'Wiśniewski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48642547498', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('752bb64c-5559-478a-9312-0b98bc2d38b8', 'Szczecin', 'Poland', 'ul. Żurawia 6759', '71-694', '2020-01-19 11:05:09.000000', 'korneliaKrl@domainboulevard.pl', 'Kornelia', 'Król', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48415491000', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('2b8a1645-a7ac-4f0b-ac88-bf85e1dc2193', 'Szczecin', 'Poland', 'ul. Melisy 6467', '70-887', '2020-01-19 11:05:09.000000', 'zdzisawJasiski@cdhunting.pl', 'Zdzisław', 'Jasiński', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48438369606', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('c72cf743-bd25-425e-877b-b8ddde009161', 'Gdynia', 'Poland', 'ul. Południowa 8267', '81-008', '2020-01-19 11:05:09.000000', 'joasiaJaboska@funnytan.pl', 'Joasia', 'Jabłońska', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48558082695', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('798006c2-1a6a-4e7e-9b73-f5eaa685afa6', 'Łódź', 'Poland', 'ul. Krasnoludków 5535', '91-504', '2020-01-19 11:05:09.000000', 'albinaZajc@streamstorm.pl', 'Albina', 'Zając', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48424345895', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('37bda1ad-0acd-4b14-8802-44ce69ce4e23', 'Poznań', 'Poland', 'ul. Iłłakowiczówny Kazimiery 1030', '60-789', '2020-01-19 11:05:09.000000', 'mioszWojciechowski@februaryspecials.pl', 'Miłosz', 'Wojciechowski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48924900279', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('b3f9a72c-cf22-4995-84ec-d3a19ec718c2', 'Białystok', 'Poland', 'ul. Warsztatowa 1231', '15-637', '2020-01-19 11:05:09.000000', 'korneliuszWieczorek@petsden.pl', 'Korneliusz', 'Wieczorek', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48315662050', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('943feb14-b3bb-4a12-bf4a-ed9a33ff11af', 'Łódź', 'Poland', 'ul. Żytnia 3592', '91-003', '2020-01-19 11:05:09.000000', 'mirosawaKalinowska@opiniontrades.pl', 'Mirosława', 'Kalinowska', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48444987999', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('e7af1eb8-1aea-4114-b124-c0dbcf0426b3', 'Bydgoszcz', 'Poland', 'ul. Karolewska 377', '85-420', '2020-01-19 11:05:09.000000', 'walerianTomaszewski@cameraupdate.pl', 'Walerian', 'Tomaszewski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48396837240', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('6df1328c-1731-4fcb-9bb3-a40b8992dc59', 'Bydgoszcz', 'Poland', 'Al. Kardynała Wyszyńskiego Stefana 2241', '85-604', '2020-01-19 11:05:09.000000', 'dawidSzczepaski@dealerspiff.pl', 'Dawid', 'Szczepański', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48449316732', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('ba52597a-826b-472f-92b9-8550b5f8749c', 'Kraków', 'Poland', 'ul. Kosynierów 2273', '31-572', '2020-01-19 11:05:09.000000', 'lechosawaDuda@weathertheme.pl', 'Lechosława', 'Duda', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48523457973', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('9dbc9259-0adb-4602-84b2-35d4f8f45cba', 'Łąka', 'Poland', 'ul. Turystyczna 7759', '43-241', '2020-01-19 11:05:09.000000', 'wodzisawKozowski@elderwebsite.pl', 'Włodzisław', 'Kozłowski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48467523330', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('b8bf86ec-3d3b-4d27-8eb3-5e31a4b5cbfe', 'Białystok', 'Poland', 'ul. Niedźwiedzia 7141', '15-531', '2020-01-19 11:05:09.000000', 'oliwiaDbrowski@cashcentric.pl', 'Oliwia', 'Dąbrowski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48637353397', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('23473808-3221-4e7f-b85d-05f6fa566327', 'Dąbrowa Górnicza', 'Poland', 'ul. Zakładowa 8091', '41-303', '2020-01-19 11:05:09.000000', 'wawrzyniecOstrowski@tuxconnect.pl', 'Wawrzyniec', 'Ostrowski', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48954775031', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('02604b88-e2f3-4784-a71c-697ec4428e2f', 'Wrocław', 'Poland', 'ul. Ryżowa 4828', '54-518', '2020-01-19 11:05:09.000000', 'klaudiaMaciejewska@easternclimate.pl', 'Klaudia', 'Maciejewska', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48539409370', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('d5528aba-806c-4336-beb9-044e5c0704d5', 'Łódź', 'Poland', 'ul. Centralna 8348', '91-503', '2020-01-19 11:05:09.000000', 'klaraZawadzka@copcameras.pl', 'Klara', 'Zawadzka', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48618876974', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('fe566314-c4cd-4bc6-b64f-7bcd0034b727', 'Jelenia Góra', 'Poland', 'ul. Wolności 5015', '58-501', '2020-01-19 11:05:09.000000', 'czesawaKamiska@unlimitedwifi.pl', 'Czesława', 'Kamińska', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48741785446', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('84c01f07-1b42-406f-b2ce-ac2245466636', 'Poznań', 'Poland', 'ul. Bułgarska 5088', '60-378', '2020-01-19 11:05:09.000000', 'krysiaWiniewska@mobilehumidifier.pl', 'Krysia', 'Wiśniewska', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48401492468', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null),
       ('5897cf9c-4f01-407d-8b56-dc11fbd68ee6', 'Opole', 'Poland', 'ul. Szaniawskiego Jerzego 6266', '45-532', '2020-01-19 11:05:09.000000', 'judytaSzczepaska@coldforum.pl', 'Judyta', 'Szczepańska', '$2a$10$jXXKMhNsw/l7FFuzKsfHAOH.qv881bSrQF8L.pQ/2QprSU8jzwyxm', '+48902285765', '2020-02-21 13:34:09.000000', '02346256-44b2-4811-917a-6ad03c56036e', null);

