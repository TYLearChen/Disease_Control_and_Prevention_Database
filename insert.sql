INSERT INTO closed_border(r1rname, r2rname, begin_time, end_time)
VALUES  ('Nigeria', 'Iran', '2010-05-12', '2011-10-08'),
    ('Nigeria', 'Taiwan', '2009-12-12', '2011-11-08'),
    ('Nigeria', 'United States', '2010-09-12', '2011-12-08');

INSERT INTO Symptom(sname, description)
VALUES  ('Joint and muscle aches', 'Aches are a common symptom, especially in your muscles and joints.'),
    ('Weakness', 'The state or condition of lacking strength.'),
    ('Lack of appetite', 'A decreased appetite occurs when you have a reduced desire to eat.'),
    ('Abdominal pain', 'Abdominal pain is pain that occurs between the chest and pelvic regions.');

INSERT INTO patient(nationality, national_id, name, gender, age)
VALUES  ('Germany', 'XG203594', 'Aje Muller', 'F', '28'),
    ('Germany', 'XG203510', 'Machiel Konner', 'M', '56'),
    ('Germany', 'XG204594', 'Annita Meiyer', 'F', '34');

INSERT INTO accommodation(nationality, national_id, iname, rname, begin_time)
VALUES  ('Germany', 'XG203594', 'Winsen Hospital Germany', 'Germany', '2020-01-01'),
    ('Germany', 'XG203510', 'Winsen Hospital Germany', 'Germany', '2020-01-01'),
    ('Germany', 'XG204594', 'Florida Hospital Orlando', 'Germany', '2020-01-01');
	
INSERT INTO incur(vname, sname)
VALUES  ('SARS-CoV', 'Fever'),
    ('COVID-19', 'Fever'), 
    ('Ebola virus', 'Fever'),
    ('Zika virus', 'Headache'),
    ('MERS-CoV', 'Cough');