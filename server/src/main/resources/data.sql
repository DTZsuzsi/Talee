INSERT INTO role(name)
VALUES ('user'), ('admin');

INSERT INTO talee_user(username, role_id)
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

INSERT INTO tag_category(name, color) VALUES('company type', 'green'), ('sporty', 'blue'), ('culture', 'black'),('romantic', 'red'), ('family', 'yellow'),('prices', 'grey'),('gastronomy', 'brown'), ('other', 'purple');
INSERT INTO tag(name, tag_category_id) VALUES ('big',1), ('romantic',4),('family friendly',5), ('hiking',2);