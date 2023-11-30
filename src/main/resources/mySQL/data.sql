-- Delete the table if already exist
DROP DATABASE IF EXISTS sql11666264;
-- Create a new database
CREATE DATABASE sql11666264;
-- Use our Database
USE sql11666264;


-- Table Formations
CREATE TABLE sql11666264.Formation (
    idFormation INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)NOT NULL,
    promotion VARCHAR(255)NOT NULL,
    CONSTRAINT check_name_promotion CHECK (
    name IN ('ID', 'MIAGE-IF', 'MIAGE-SITN') AND
    promotion IN ('Initiale', 'En Alternance', 'Formation Continue')
    )
);

-- Table Students
CREATE TABLE sql11666264.Student (
    idStudent INT AUTO_INCREMENT PRIMARY KEY,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    idFormation INT NOT NULL,
    FOREIGN KEY (idFormation) REFERENCES Formation(idFormation)
);

-- Table Projects
CREATE TABLE sql11666264.Project (
    idProject INT AUTO_INCREMENT PRIMARY KEY,
    nameSubject VARCHAR(255) NOT NULL,
    topic VARCHAR(255) NOT NULL,
    dateRemisePreview DATE NOT NULL
);

-- Table Pairs
CREATE TABLE sql11666264.Pair (
    idProject INT,
    numberRelative INT,
    student1 INT,
    student2 INT,
    PRIMARY KEY (idProject, numberRelative),
    INDEX (numberRelative),
    FOREIGN KEY (idProject) REFERENCES Project(idProject),
    FOREIGN KEY (student1) REFERENCES Student(idStudent),
    FOREIGN KEY (student2) REFERENCES Student(idStudent)
);

-- Table Grades
CREATE TABLE sql11666264.Grade (
    idProject INT,
    NumberPair INT,
    gradeReport FLOAT NOT NULL,
    gradeOral1 FLOAT NOT NULL,
    gradeOral2 FLOAT NOT NULL,
    dateReturnEffectiveReport DATE,
    PRIMARY KEY (idProject, NumberPair),
    FOREIGN KEY (idProject) REFERENCES Project(idProject),
    FOREIGN KEY (NumberPair) REFERENCES Pair(numberRelative)
);
