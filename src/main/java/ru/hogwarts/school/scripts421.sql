ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age > 16);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;