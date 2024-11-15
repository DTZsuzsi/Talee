INSERT INTO role(name)
VALUES ('user'), ('admin');

INSERT INTO talee_user(username, role_id)
VALUES ('matet', 2), ('zsuzsi', 1), ('marian', 1);

INSERT INTO location (name, admin_user_id, address, phone, email, website, facebook, instagram, description)
VALUES ('Fővárosi Állat- és Növénykert',1, '1146 Budapest, Állatkerti krt. 6-12.',
        '06 1 273 4901', 'allatkert@email.hu', 'https://zoobudapest.com/',
        'https://www.facebook.com/allatkert', 'https://www.instagram.com/zoobudapest',
        'Budapest legnagyobb állatkertje');




INSERT INTO event (date, name, description, location_id, owner, size, status)
VALUES ('2024-11-02', 'Állatkertek éjszakája', 'Éjjel is jöhettek', 1, 'Marianna Molnár', 'BIG',
        'COMING'),
       ('2025-01-01', 'Örökbefogadási nap', 'fogadjatok örökbe állatot', 1, 'Marianna Molnár', 'BIG',
        'ENDED'),
       ('2024-12-10', 'Margitszigeti bingóhintó verseny', 'nagy verseny lesz', 1, 'Johanna Ditrói', 'BIG',  'IN_PROGRESS');

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
