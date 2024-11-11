DROP TABLE IF EXISTS locations;

CREATE TABLE locations
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(300),
    address VARCHAR(300),
    phone VARCHAR(50),
    email VARCHAR(255),
    website VARCHAR(300),
    facebook VARCHAR(300),
    instagram VARCHAR(300),
    description VARCHAR(300)
);

INSERT INTO locations (name, address, phone, email, website, facebook, instagram, description)
VALUES ('Fővárosi Állat- és Növénykert', '1146 Budapest, Állatkerti krt. 6-12.',
        '06 1 273 4901', 'allatkert@email.hu', 'https://zoobudapest.com/',
        'https://www.facebook.com/allatkert', 'https://www.instagram.com/zoobudapest',
        'Budapest legnagyobb állatkertje'),
    ('Margitsziget', '1138 Budapest, Margitsziget', '', '',
     'https://hu.wikipedia.org/wiki/Margit-sziget', 'https://www.facebook.com/Margitsziget.oldal?fref=ts',
     '', 'A Margitsziget, mely a helyiek kedvenc városrésze és Budapest zöld szíve, a Duna közepén a Margit híd és az Árpád híd között terül el. Néhány szállodától és sportlétesítménytől eltekintve valójában egy nagy zöld park, árnyas sétányokkal és padokkal, ezért randi- és piknikhelyszínnek is kiváló.');