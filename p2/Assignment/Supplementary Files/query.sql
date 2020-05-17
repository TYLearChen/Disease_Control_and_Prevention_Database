SELECT I.vName AS "Virus", AVG(I.End_Time - I.Begin_Time) AS "Avg_Recover_Time (Days)"
FROM Infect I
GROUP BY I.vName
LIMIT 10;

SELECT V.iName AS "Research Institute", V.rName AS "Country", R.address AS "Address"
FROM Vaccination AS V JOIN Research_Institute AS R ON V.rName = R.rName AND V.iName = R.iName
WHERE V.vName = 'MERS-CoV'
LIMIT 10;

SELECT sub1.vName AS "Virus", sub1.sName AS "Symptom", (sub1.Count*1. / sub2.Count*1.) AS "P (Virus | Symptom)"
FROM
(
        SELECT I.vName, E.sName, Count(*) AS Count
        FROM Infect I JOIN Exhibit E ON I.Nationality = E.Nationality AND I.National_ID = E.National_ID
        GROUP BY I.vName, E.sName
) sub1 JOIN -- P (A and B)
(
        SELECT E.sName, Count(*) AS Count
        FROM Infect I JOIN Exhibit E ON I.Nationality = E.Nationality AND I.National_ID = E.National_ID
        GROUP BY E.sName
) sub2 ON sub1.sName = sub2.sName -- P(B)
LIMIT 10;

WITH Total (vName, Nationality, Count) AS
(
        SELECT V.vName, I.Nationality, COUNT(*) AS Count
        FROM Virus V JOIN Infect I ON V.vName = I.vName
        GROUP BY V.vName, I.Nationality
), 
Dead (vName, Nationality, Count) AS
(
        SELECT V.vName, I.Nationality, COUNT(*) AS Count
        FROM Virus V JOIN Infect I ON V.vName = I.vName
        WHERE I.state = 'Dead'
        GROUP BY V.vName, I.Nationality
)
SELECT Total.vName AS "Virus", Total.Nationality AS "Nationality", Dead.Count*1. / Total.Count*1. AS "Death Rate"
FROM Total JOIN Dead ON Dead.vName = Total.vName AND Dead.Nationality = Total.Nationality
LIMIT 10;

SELECT I.vName AS "Virus", A.rName AS "Country", MIN(I.Begin_Time) AS "First Outbreak Date"
FROM Infect I JOIN Accommodation A ON (I.Nationality = A.Nationality AND I.National_ID = A.National_ID)
GROUP BY I.vName, A.rName
LIMIT 10;

