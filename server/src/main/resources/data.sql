INSERT INTO role(name)
VALUES ('user'), ('admin');

INSERT INTO users(username, role_id)
VALUES ('matet', 2), ('zsuzsi', 1), ('marian', 1);

INSERT INTO location (name, address, phone, email, website, facebook, instagram, description)
VALUES ('Fővárosi Állat- és Növénykert', '1146 Budapest, Állatkerti krt. 6-12.',
        '06 1 273 4901', 'allatkert@email.hu', 'https://zoobudapest.com/',
        'https://www.facebook.com/allatkert', 'https://www.instagram.com/zoobudapest',
        'Budapest legnagyobb állatkertje'),
       ('Margitsziget', '1138 Budapest, Margitsziget', '', '',
        'https://hu.wikipedia.org/wiki/Margit-sziget', 'https://www.facebook.com/Margitsziget.oldal?fref=ts',
        '', 'A Margitsziget, mely a helyiek kedvenc városrésze és Budapest zöld szíve, a Duna közepén a Margit híd és az Árpád híd között terül el.');

INSERT INTO event (date, name, description, location_id, owner, size, status)
VALUES ('2024-11-02', 'Állatkertek éjszakája', 'Éjjel is jöhettek', 1, 'Marianna Molnár', 'BIG',
        'COMING'),
       ('2025-01-01', 'Örökbefogadási nap', 'fogadjatok örökbe állatot', 1, 'Marianna Molnár', 'BIG',
        'ENDED'),
       ('2024-12-10', 'Margitszigeti bingóhintó verseny', 'nagy verseny lesz', 2, 'Johanna Ditrói', 'BIG',  'IN_PROGRESS');

-- Insert Tag Categories
INSERT INTO tag_category(name, color)
VALUES
    ('company type', 'green'),
    ('sporty', 'blue'),
    ('culture', 'black'),
    ('romantic', 'red'),
    ('family', 'yellow'),
    ('prices', 'grey'),
    ('gastronomy', 'brown'),
    ('other', 'purple'),
    ('adventure', 'orange'),
    ('education', 'lightblue'),
    ('music', 'pink'),
    ('wellness', 'teal'),
    ('technology', 'silver'),
    ('nature', 'green'),
    ('art', 'purple'),
    ('events', 'gold');

-- Insert Tags (50 tags)
INSERT INTO tag(name, tag_category_id)
VALUES
    ('big', 1),
    ('romantic', 4),
    ('family friendly', 5),
    ('hiking', 2),
    ('outdoor', 2),
    ('educational', 10),
    ('concert', 3),
    ('fitness', 6),
    ('startup', 1),
    ('vegan', 6),
    ('technology expo', 13),
    ('theater', 3),
    ('children', 5),
    ('workshop', 10),
    ('holiday', 5),
    ('romantic dinner', 4),
    ('sports festival', 2),
    ('culinary', 7),
    ('networking', 13),
    ('green space', 6),
    ('comedy', 3),
    ('business conference', 1),
    ('craft beer', 7),
    ('marathon', 2),
    ('art exhibition', 14),
    ('family event', 5),
    ('volunteer', 1),
    ('classical music', 12),
    ('tech talk', 13),
    ('camping', 2),
    ('foodie', 7),
    ('family picnic', 5),
    ('startup pitch', 1),
    ('fashion show', 14),
    ('mental health', 12),
    ('film screening', 14),
    ('mystery event', 14),
    ('museum tour', 14),
    ('holiday market', 7),
    ('pop-up shop', 1),
    ('team building', 1),
    ('charity event', 1),
    ('outdoor movie night', 2),
    ('summer festival', 2),
    ('eco-friendly', 6),
    ('local art', 14),
    ('book reading', 14),
    ('tasting event', 7),
('leadership summit', 1),
('concert in the park', 3);
