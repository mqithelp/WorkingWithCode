SELECT student.name as Имя
     , age          as Возраст
     , faculty.name AS Факультет
FROM student
         INNER JOIN faculty ON student.faculty_id = faculty.id
order by student.name;


SELECT student.name as Имя
     , age          as Возраст
FROM student
         INNER JOIN avatar ON avatar.student_id = student.id
order by student.name;