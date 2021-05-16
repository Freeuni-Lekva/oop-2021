# ახალი ბაზის შექმნა
CREATE DATABASE test_db;

# მაიესქიუელისთვის იმის თქმა თუ რომელ ბაზას ვიყენებთ
use test_db;

# მაგიდის ცხრილის შექმნა
CREATE TABLE courses (
	id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT NOT NULL,
    course_name VARCHAR(255) NOT NULL,
    course_credit INT NOT NULL DEFAULT 0,
    course_type VARCHAR(255),
    CONSTRAINT courses_uk UNIQUE (course_id)
);

# სტუდენტების ცხრილის შექმნა
CREATE TABLE students (
	id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    idnumber CHAR(11) NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    register_date DATETIME NOT NULL DEFAULT NOW(),
    CONSTRAINT students_uk1 UNIQUE (student_id),
    CONSTRAINT students_uk2 UNIQUE (idnumber)
);

# სტუდენტების ცხრილში ელემენტების დამატება
insert into students (student_id, idnumber, firstName, lastName) 
values 
(1, '11111111111', 'john', 'doe'),
(2, '11111111112', 'giorgi', 'pirveli'),
(3, '11111111113', 'giorgi', 'meore');

# კურსების ცხრილში ელემენტების დამატება
INSERT INTO courses 
	(course_id, course_name, course_credit, course_type)
VALUES
	(1, 'Linear Algebra', 4, 'MATH'),
	(2, 'Programming Paradigms', 6, 'CS'),
	(3, 'Calculus I', 6, 'MATH'),
	(4, 'OOP', 6, 'CS'),
    (5, 'Quantum Algorithms', 7, 'CS'),
    (6, 'Nand To Tetris', 6, 'CS');

# ცხრილის შექმნა იმის დასამახსოვრებლად რომელი სტუდენტი რომელ კურსს გადის
CREATE TABLE student_courses (
	id INT PRIMARY KEY AUTO_INCREMENT,
    course_id INT NOT NULL,
    student_id INT NOT NULL,
    CONSTRAINT student_courses_uk1 UNIQUE (course_id, student_id),
    CONSTRAINT student_courses_fk1 FOREIGN KEY (course_id)
		REFERENCES courses (id),
	CONSTRAINT student_courses_fk2 FOREIGN KEY (student_id)
		REFERENCES students (id)
)

# სტუდენტებისთვის კურსის დამატება
insert into student_courses (student_id, course_id)
select s.id, c.id from students s inner join courses c
where (s.firstname = 'john' and c.course_credit = 7) or (s.firstname = 'john' and c.course_credit = 4);

# ყველა იმ კურსის დაბრუნება რომელსაც გადის სტუდენტი აიდით 1
select c.* from courses c left join student_courses sc on c.id = sc.course_id where sc.student_id = 1;