INSERT INTO role(name)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO talee_user(username, password)
VALUES ('matet', '$2a$10$0F2KZTUFjkzR9.BOrEWZQ.V30PbhYHi1BhktPgDZJh0n9YJorH/A6'), ('zsuzsi', '$2a$10$0F2KZTUFjkzR9.BOrEWZQ.V30PbhYHi1BhktPgDZJh0n9YJorH/A6'), ('mariann', '$2a$10$0F2KZTUFjkzR9.BOrEWZQ.V30PbhYHi1BhktPgDZJh0n9YJorH/A6');

INSERT INTO location (name, admin_user_id, address, phone, email, website, facebook, instagram, description)
VALUES
    ('Margitszigeti Szabadtéri Színpad', 2, '1138 Budapest, Margitsziget',
     '06 1 555 1234', 'szinpad@margitsziget.hu', 'https://margitszigetiszinhaz.hu/',
     'https://www.facebook.com/margitszigetiszinhaz', 'https://www.instagram.com/margitszigetiszinhaz',
     'Színházi és kulturális rendezvények egyedülálló szabadtéri helyszíne'),

    ('Budai Vár', 3, '1014 Budapest, Szent György tér',
     '06 1 555 5678', 'info@budaivar.hu', 'https://www.budaivar.hu/',
     'https://www.facebook.com/budaivar', 'https://www.instagram.com/budaivar',
     'Történelmi látványosságok és rendezvények helyszíne'),

    ('Városliget', 1, '1146 Budapest, Városliget',
     '06 1 444 9876', 'info@varosliget.hu', 'https://www.varosliget.hu/',
     'https://www.facebook.com/varosliget', 'https://www.instagram.com/varosliget',
     'Budapest zöld szíve, amely számos szabadtéri programnak ad otthont'),

    ('Duna Korzó', 3, '1056 Budapest, Belgrád rakpart',
     '06 1 777 1111', 'dunakorzofesztival@gmail.com', NULL,
     'https://www.facebook.com/dunakorzofesztival', NULL,
     'Budapest egyik legismertebb promenádja a Duna partján'),

    ('Gellért-hegy', 1, '1118 Budapest, Gellérthegy',
     '06 1 888 6543', 'info@gellerthegy.hu', NULL,
     'https://www.facebook.com/gellerthegy', NULL,
     'Kirándulási és kulturális rendezvények népszerű helyszíne'),

    ('Budapest Park', 2, '1095 Budapest, Soroksári út 60.',
     '06 1 999 0000', 'park@budapest.hu', 'https://www.budapestpark.hu/',
     'https://www.facebook.com/budapestpark', 'https://www.instagram.com/budapestpark',
     'Európa legnagyobb szabadtéri szórakozóhelye'),

    ('Hősök tere', 1, '1146 Budapest, Hősök tere',
     '06 1 345 4321', 'info@hosoktere.hu', NULL,
     'https://www.facebook.com/hosoktere', NULL,
     'Történelmi és turisztikai jelentőségű tér Budapest szívében'),

    ('Bazilika', 3, '1051 Budapest, Szent István tér 1.',
     '06 1 543 2100', 'info@bazilika.hu', 'https://www.bazilika.hu/',
     'https://www.facebook.com/bazilika', 'https://www.instagram.com/bazilika',
     'A Szent István Bazilika és a körülötte rendezett események'),

    ('Andrássy út', 1, '1061 Budapest, Andrássy út',
     '06 1 678 5432', 'info@andrassyut.hu', NULL,
     'https://www.facebook.com/andrassyut', NULL,
     'Budapest elegáns sugárútja, fesztiválok és rendezvények helyszíne'),

    ('Puskás Aréna', 2, '1146 Budapest, Ifjúság útja 5.',
     '06 1 123 4567', 'arena@puskasarena.hu', 'https://www.puskasarena.hu/',
     'https://www.facebook.com/puskasarena', 'https://www.instagram.com/puskasarena',
     'Magyarország legnagyobb sport- és rendezvénycsarnoka');


INSERT INTO opening_hours (day_of_week, opening_time, closing_time, location_id)
VALUES ('MONDAY', '09:00', '16:00', 1),
       ('TUESDAY', '09:00', '16:00', 1),
       ('WEDNESDAY', '09:00', '16:00', 1),
       ('THURSDAY', '09:00', '16:00', 1),
       ('FRIDAY', '09:00', '16:00', 1),
       ('SATURDAY', '09:00', '16:00', 1),
       ('SUNDAY', '09:00', '16:00', 1);

INSERT INTO event (date, name, description, location_id, owner, size, status)
VALUES
    ('2024-11-20', 'Margitszigeti Jégpálya Megnyitó', 'Téli szezonnyitó a Margitszigeten', 7, 'Gábor Varga', 'MEDIUM', 'COMING'),
    ('2025-02-14', 'Romantikus séta a Budai Várban', 'Valentin-napi program pároknak', 2, 'Anita Kovács', 'SMALL', 'PLANNED'),
    ('2024-12-24', 'Karácsonyi Vásár', 'Hangulatos vásár a Városligetben', 3, 'Dóra Tóth', 'BIG', 'COMING'),
    ('2024-12-31', 'Szilveszteri Tűzijáték a Dunán', 'Fergeteges tűzijáték Budapest felett', 4, 'Máté Kiss', 'HUGE', 'COMING'),
    ('2025-01-10', 'Hófesztivál a Gellért-hegyen', 'Szánkózás, hóemberépítés és forró italok', 5, 'János Szabó', 'MEDIUM', 'PLANNED'),
    ('2024-11-30', 'Téli Koncertsorozat', 'Különleges fellépők a Budapest Parkban', 6, 'Krisztina Lakatos', 'BIG', 'COMING'),
    ('2025-03-15', 'Március 15-i Ünnepség', 'Nemzeti megemlékezés a Hősök terén', 7, 'Katalin Kovács', 'HUGE', 'PLANNED'),
    ('2024-12-06', 'Mikulásvárás a Bazilikánál', 'Gyerekprogram Mikulással és karácsonyi hangulattal', 8, 'Júlia Farkas', 'SMALL', 'COMING'),
    ('2024-12-17', 'Andrássy Úti Karácsonyi Fények', 'Ünnepi fények és vásár az Andrássy úton', 9, 'Péter Nagy', 'BIG', 'COMING'),
    ('2025-05-01', 'Munka Ünnepe a Margitszigeten', 'Május 1-jei piknik és koncertek', 1, 'Andrea Kiss', 'BIG', 'PLANNED'),
    ('2024-12-22', 'Budai Adventi Körút', 'Különleges vezetett séta a Budai Várban', 2, 'Nóra Széles', 'SMALL', 'COMING'),
    ('2025-06-01', 'Nyári Piknik a Városligetben', 'Kültéri program családoknak és barátoknak', 3, 'Gergely Fekete', 'BIG', 'PLANNED'),
    ('2025-07-04', 'Duna-parti Jazz Est', 'Különleges jazz koncert a Duna Korzón', 4, 'Tamás Varga', 'MEDIUM', 'PLANNED'),
    ('2024-11-18', 'Őszi Éjszakai Túra a Gellért-hegyen', 'Vezetett túra éjszakai kilátással', 5, 'Zsófia Molnár', 'SMALL', 'ENDED'),
    ('2025-08-15', 'Budapest Park Nyárzáró', 'Élő zene és táncest', 6, 'Bence Fodor', 'BIG', 'PLANNED'),
    ('2025-10-23', 'Nemzeti Ünnepi Felvonulás', 'Ünnepség a Hősök terén', 7, 'Levente Nagy', 'HUGE', 'PLANNED'),
    ('2024-12-20', 'Bazilika Adventi Koncert', 'Klasszikus zenei est a Bazilikában', 8, 'Miklós Horváth', 'MEDIUM', 'COMING'),
    ('2025-09-10', 'Andrássy Divatbemutató', 'Elegáns divatbemutató az Andrássy úton', 9, 'Emma Nagy', 'MEDIUM', 'PLANNED'),
    ('2025-03-21', 'Tavaszi Jótékonysági Koncert', 'Jótékonysági esemény a Margitszigeten', 1, 'Dorina Balázs', 'MEDIUM', 'PLANNED'),
    ('2025-06-15', 'Művészeti Est a Budai Várban', 'Kortárs művészeti kiállítás és koncert', 2, 'István Fekete', 'MEDIUM', 'PLANNED'),
    ('2025-07-20', 'Városligeti Grillfesztivál', 'Finom ételek és zenei programok', 3, 'Orsolya Takács', 'BIG', 'PLANNED'),
    ('2025-08-08', 'Nyári Esték a Dunán', 'Hajós borkóstoló és zene', 4, 'Zoltán Veres', 'SMALL', 'PLANNED'),
    ('2024-11-12', 'Őszi Filmfesztivál', 'Szabadtéri filmvetítések a Gellért-hegyen', 5, 'Katalin Varga', 'MEDIUM', 'COMING'),
    ('2025-10-30', 'Halloween Party a Budapest Parkban', 'Rémisztő hangulat és koncertek', 6, 'Edina Nagy', 'BIG', 'PLANNED'),
    ('2025-12-01', 'Adventi Kórusfesztivál', 'Kórusok fellépése a Hősök terén', 7, 'Mária Horváth', 'MEDIUM', 'PLANNED'),
    ('2025-12-24', 'Karácsony a Bazilikánál', 'Ünnepi hangulatú vásár és koncert', 8, 'József Kiss', 'BIG', 'PLANNED'),
    ('2024-11-25', 'Andrássy Úti Gasztrofesztivál', 'Különleges ételek és italok kóstolója', 9, 'Boglárka Tóth', 'MEDIUM', 'COMING');

-- Insert Tag Categories
INSERT INTO tag_category(name, color)
VALUES  ('company', '#ff7f50'),         -- Coral
('sporty', '#b497d6'),        -- Lavender
('culture', '#f9e550'),       -- Light Yellow
('romantic', '#ff00ff'),         -- Magenta
('family', '#ff6600'),         -- Bright Orange
('prices', '#d3d3d3'),     -- Soft Gray
('gastronomy', '#5e2d98'),          -- Dark Purple
('other', '#30d5c8');      -- Turquoise


-- Tags for category id 1 ('company type')
INSERT INTO tag(name, tag_category_id) VALUES
                                           ('big', 1),
                                           ('corporate', 1),
                                           ('startup', 1),
                                           ('enterprise', 1),
                                           ('local business', 1),
                                           ('eco-friendly', 1),
                                           ('community', 1),
                                           ('non-profit', 1),
                                           ('technology', 1),
                                           ('small business', 1);

-- Tags for category id 2 ('sporty')
INSERT INTO tag(name, tag_category_id) VALUES
                                           ('outdoor', 2),
                                           ('adventure', 2),
                                           ('fitness', 2),
                                           ('athletic', 2),
                                           ('wellness', 2),
                                           ('training', 2),
                                           ('hiking', 2),
                                           ('gym', 2),
                                           ('cardio', 2),
                                           ('yoga', 2);

-- Tags for category id 3 ('culture')
INSERT INTO tag(name, tag_category_id) VALUES
                                           ('museum', 3),
                                           ('art', 3),
                                           ('exhibition', 3),
                                           ('history', 3),
                                           ('literature', 3),
                                           ('festival', 3),
                                           ('theatre', 3),
                                           ('cinema', 3),
                                           ('heritage', 3),
                                           ('classical music', 3);

-- Tags for category id 4 ('romantic')
INSERT INTO tag(name, tag_category_id) VALUES
                                           ('dinner date', 4),
                                           ('sunset', 4),
                                           ('picnic', 4),
                                           ('candlelight', 4),
                                           ('weekend getaway', 4),
                                           ('honeymoon', 4),
                                           ('date night', 4),
                                           ('cozy', 4),
                                           ('flowers', 4),
                                           ('proposal', 4);

-- Tags for category id 5 ('family')
INSERT INTO tag(name, tag_category_id) VALUES
                                           ('family friendly', 5),
                                           ('kids', 5),
                                           ('petting zoo', 5),
                                           ('amusement park', 5),
                                           ('picnic', 5),
                                           ('crafts', 5),
                                           ('board games', 5),
                                           ('playground', 5),
                                           ('camping', 5),
                                           ('outdoor fun', 5);

-- Tags for category id 6 ('prices')
INSERT INTO tag(name, tag_category_id) VALUES
                                           ('free entry', 6),
                                           ('discount', 6),
                                           ('promo', 6),
                                           ('tickets', 6),
                                           ('membership', 6),
                                           ('subscription', 6),
                                           ('bundle', 6),
                                           ('sale', 6),
                                           ('early bird', 6),
                                           ('VIP access', 6);

-- Tags for category id 7 ('gastronomy')
INSERT INTO tag(name, tag_category_id) VALUES
                                           ('fine dining', 7),
                                           ('street food', 7),
                                           ('organic', 7),
                                           ('local cuisine', 7),
                                           ('wine tasting', 7),
                                           ('brunch', 7),
                                           ('food festival', 7),
                                           ('vegetarian', 7),
                                           ('seafood', 7),
                                           ('dessert', 7);

-- Tags for category id 8 ('other')
INSERT INTO tag(name, tag_category_id) VALUES
                                           ('networking', 8),
                                           ('online event', 8),
                                           ('fundraiser', 8),
                                           ('photography', 8),
                                           ('volunteering', 8),
                                           ('workshop', 8),
                                           ('DIY', 8),
                                           ('meetup', 8),
                                           ('webinar', 8),
                                           ('social gathering', 8);

-- INSERT INTO event_users (event_id, user_id) VALUES (1, 1), (1, 2);

INSERT INTO tag_category(name, color) VALUES('company reportType', 'green'), ('sporty', 'blue'), ('culture', 'black'),('romantic', 'red'), ('family', 'yellow'),('prices', 'grey'),('gastronomy', 'brown'), ('other', 'purple');
INSERT INTO tag(name, tag_category_id) VALUES ('big',1), ('romantic',4),('family friendly',5), ('hiking',2);

-- Reports for issues and content for testing purposes c:

INSERT INTO report (title, description, report_type, issued, solved, created_at, updated_at)
VALUES
    ('Missing Event Details',
     'The event "Állatkertek éjszakája" does not have a full description or schedule. Please update with more information.',
     'CONTENT',
     true,
     false,
     '2024-10-15 10:30:00',
     '2024-10-15 10:30:00'),

    ('Error in Location Address',
     'The address for Margitsziget is incorrect on the event page. It displays "1146 Budapest" instead of "1138 Budapest".',
     'BUG',
     true,
     true,
     '2024-09-20 14:00:00',
     '2024-10-01 09:00:00'),

    ('New Activity Suggestion',
     'Suggest adding a family picnic event on Margitsziget with food vendors and outdoor games to make it more family-friendly.',
     'CONTENT',
     false,
     false,
     '2024-11-05 16:45:00',
     '2024-11-05 16:45:00');
