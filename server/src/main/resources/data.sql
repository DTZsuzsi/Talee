INSERT INTO role(name)
VALUES ('user'), ('admin');

INSERT INTO users(username, role_id)
VALUES ('matet', 2), ('zsuzsi', 1), ('marian', 1);

INSERT INTO events (date, name, description, location_id, owner, size, status)
VALUES ('2024-11-02', 'Állatkertek éjszakája', 'Éjjel is jöhettek', 1, 'Marianna Molnár', 'BIG',
        'COMING'),
       ('2025-01-01', 'Örökbefogadási nap', 'fogadjatok örökbe állatot', 1, 'Marianna Molnár', 'BIG',
        'ENDED'),
       ('2024-12-10', 'Margitszigeti bingóhintó verseny', 'nagy verseny lesz', 2, 'Johanna Ditrói', 'BIG',  'IN_PROGRESS'),
       ('2024-11-15', 'Adventi vásár', 'Karácsonyi hangulat a Vörösmarty téren', 3, 'Ágnes Szabó', 'BIG',
        'COMING'),
       ('2024-11-20', 'Forralt bor fesztivál', 'Különleges forralt borok a Gozsdu Udvarban', 4, 'Gergely Kiss',
        'MEDIUM',  'COMING'),
       ('2024-12-05', 'Jégpálya nyitóbuli', 'Nyitóbuli a Városligeti Műjégpályán', 5, 'Tamás Kovács', 'VERY_BIG'
       , 'COMING'),
       ('2025-02-14', 'Valentin-napi túra a Normafánál', 'Romantikus túra a hegyekben', 6, 'Emma Nagy', 'SMALL'
       , 'COMING'),
       ('2024-12-31', 'Szilveszteri bulifutás', 'Éjszakai futás a Hősök terétől', 7, 'Zoltán Székely', 'BIG'
       , 'IN_PROGRESS'),
       ('2024-11-25', 'Street Food hétvége', 'Finomságok a legjobb utcai séfektől', 8, 'Anna Bodor', 'MEDIUM'
       , 'COMING'),
       ('2024-12-12', 'Retro filmfesztivál', 'Klasszikusok a Corvin moziban', 9, 'Péter Horváth', 'BIG',
        'COMING'),
       ('2025-03-15', 'Március 15. Nemzeti Ünnep', 'Ünnepi megemlékezések a belvárosban', 10, 'Magyarország Kormánya',
        'VERY_BIG',  'COMING');

INSERT INTO tag(name)
VALUES ('big'), ('romantic'),('family friendly');

INSERT INTO locations (name, address, phone, email, website, facebook, instagram, description)
VALUES ('Fővárosi Állat- és Növénykert', '1146 Budapest, Állatkerti krt. 6-12.',
        '06 1 273 4901', 'allatkert@email.hu', 'https://zoobudapest.com/',
        'https://www.facebook.com/allatkert', 'https://www.instagram.com/zoobudapest',
        'Budapest legnagyobb állatkertje'),
       ('Margitsziget', '1138 Budapest, Margitsziget', '', '',
        'https://hu.wikipedia.org/wiki/Margit-sziget', 'https://www.facebook.com/Margitsziget.oldal?fref=ts',
        '', 'A Margitsziget, mely a helyiek kedvenc városrésze és Budapest zöld szíve, a Duna közepén a Margit híd és az Árpád híd között terül el. Néhány szállodától és sportlétesítménytől eltekintve valójában egy nagy zöld park, árnyas sétányokkal és padokkal, ezért randi- és piknikhelyszínnek is kiváló.');


