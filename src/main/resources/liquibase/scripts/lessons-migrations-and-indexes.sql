-- liquibase formatted sql

-- changeset addIndexForSearchStudent:1
CREATE INDEX student_search_index ON student (name);

-- changeset addIndexForSearchColorFaculty:2
CREATE INDEX faculty_color_search_index ON faculty (color);
