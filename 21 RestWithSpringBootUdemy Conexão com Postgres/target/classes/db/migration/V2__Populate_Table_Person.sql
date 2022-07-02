INSERT INTO person (id, address, first_name, gender, last_name) VALUES
    (1, 'Rio de Janeiro - Rio de Janeiro - Brasil', 'Felippe', 'Male', 'Amaral'),
    (2, 'Guarulhos - São Paulo - Brasil', 'Gabriela', 'Female', 'Silva'),
    (3, 'Anapolis - São Paulo - Brasil', 'Flávio', 'Male', 'Silva'),
    (4, 'Guarulhos - São Paulo - Brasil', 'Fernanda', 'Female', 'Cardoso da Silva'),
    (5, 'Guarulhos - São Paulo - Brasil', 'Pedro', 'Male', 'Paulo'),
    (6, 'Guarulhos - São Paulo - Brasil', 'Marcos', 'Male', 'Paulo');
    
ALTER SEQUENCE person_seq RESTART WITH 7;