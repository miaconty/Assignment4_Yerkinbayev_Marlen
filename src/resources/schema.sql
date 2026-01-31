CREATE DATABASE IF NOT EXISTS course_reg_db;
USE course_reg_db;

DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS instructors;

CREATE TABLE instructors (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE courses (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         credits INT NOT NULL CHECK (credits > 0),
                         instructor_id INT NOT NULL,
                         course_type VARCHAR(50) NOT NULL,
                         platform_url VARCHAR(255),
                         room_number VARCHAR(50),
                         FOREIGN KEY (instructor_id) REFERENCES instructors(id)
);

INSERT INTO instructors (name, email) VALUES
                                          ('Dr. Smith', 'smith@uni.edu'),
                                          ('Prof. Johnson', 'johnson@uni.edu');