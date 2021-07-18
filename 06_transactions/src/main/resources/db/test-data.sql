INSERT INTO APPLICATION(ID, NAME)
VALUES (1, 'Weather'),
       (2, 'Alarm clock');

INSERT INTO REVIEW(ID, APP_ID, RATING, DESCRIPTION)
VALUES
       (1, 1, 3.8, 'Good enough'),
       (2, 1, 4.5, 'Excellent accuracy'),
       (3, 2, 2.0, 'Slept in because alarm did not go off'),
       (4, 2, 5.0, 'Repeatable alarms are awesome');