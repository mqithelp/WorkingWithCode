-- liquibase formatted sql

-- changeset addIndexForSearchStudent:1 runOnChange=true
CREATE INDEX student_name_index ON student (name);

-- changeset addIndexForSearchColorFaculty:2 runOnChange=true
CREATE INDEX faculty_color_index ON faculty (color);

-- changeset addIndexForNameFaculty:3 runOnChange=true
CREATE INDEX faculty_name_index ON faculty (name);
