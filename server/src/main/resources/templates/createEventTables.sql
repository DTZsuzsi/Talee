

INSERT INTO event (date, name, description, location_id, owner, size, users, tags, status)
VALUES ('2024-11-02', 'Állatkertek éjszakája', 'Éjjel is jöhettek', 1, 'Marianna Molnár', 'BIG',
        ARRAY ['gyerekes', 'kaland'],
        ARRAY ['állatos', 'családos'], 'COMING'),
       ('2025-01-01', 'Örökbefogadási nap', 'fogadjatok örökbe állatot', 1, 'Marianna Molnár', 'BIG',
        ARRAY ['állatos', 'családos'],
        ARRAY ['gyerekes', 'kaland'], 'ENDED'),
       ('2024-12-10', 'Margitszigeti bingóhintó verseny', 'nagy verseny lesz', 2, 'Johanna Ditrói', 'BIG', ARRAY ['romantikázós',
           'haveros'], ARRAY ['sport', 'outdoor'], 'IN_PROGRESS'),
       ('2024-11-15', 'Adventi vásár', 'Karácsonyi hangulat a Vörösmarty téren', 3, 'Ágnes Szabó', 'BIG',
        ARRAY ['családos', 'gyerekes'], ARRAY ['ünnepi', 'kültéri'], 'COMING'),
       ('2024-11-20', 'Forralt bor fesztivál', 'Különleges forralt borok a Gozsdu Udvarban', 4, 'Gergely Kiss',
        'MEDIUM', ARRAY ['barátokkal', 'randevú'], ARRAY ['gasztro', 'kültéri'], 'COMING'),
       ('2024-12-05', 'Jégpálya nyitóbuli', 'Nyitóbuli a Városligeti Műjégpályán', 5, 'Tamás Kovács', 'VERY_BIG',
        ARRAY ['családos', 'barátokkal'], ARRAY ['téli', 'kültéri', 'sport'], 'COMING'),
       ('2025-02-14', 'Valentin-napi túra a Normafánál', 'Romantikus túra a hegyekben', 6, 'Emma Nagy', 'SMALL',
        ARRAY ['romantikus', 'kalandos'], ARRAY ['túra', 'természet'], 'COMING'),
       ('2024-12-31', 'Szilveszteri bulifutás', 'Éjszakai futás a Hősök terétől', 7, 'Zoltán Székely', 'BIG',
        ARRAY ['sportos', 'egyedülálló'], ARRAY ['kültéri', 'futás', 'szórakozás'], 'IN_PROGRESS'),
       ('2024-11-25', 'Street Food hétvége', 'Finomságok a legjobb utcai séfektől', 8, 'Anna Bodor', 'MEDIUM',
        ARRAY ['barátokkal', 'családos'], ARRAY ['gasztro', 'kültéri'], 'COMING'),
       ('2024-12-12', 'Retro filmfesztivál', 'Klasszikusok a Corvin moziban', 9, 'Péter Horváth', 'BIG',
        ARRAY ['filmkedvelő', 'nosztalgikus'], ARRAY ['mozi', 'retro'], 'COMING'),
       ('2025-03-15', 'Március 15. Nemzeti Ünnep', 'Ünnepi megemlékezések a belvárosban', 10, 'Magyarország Kormánya',
        'VERY_BIG', ARRAY ['családos', 'hazafias'], ARRAY ['ünnep', 'kültéri', 'történelem'], 'COMING');

