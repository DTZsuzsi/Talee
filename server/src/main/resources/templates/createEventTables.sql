

INSERT INTO event (date, name, description, location_id, owner, size, status)
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

--INSERT INTO event_user(event_id, user_id) VALUES (1,2), (1,3), (2,1), (2,2), (3,3), (3,1);
--INSERT INTO event_tag(event_id, tag_id) VALUES (1,2), (1,3), (2,1), (2,2), (3,3), (3,1);


