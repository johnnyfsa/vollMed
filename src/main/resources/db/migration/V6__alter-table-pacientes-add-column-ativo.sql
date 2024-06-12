ALTER TABLE pacientes ADD COLUMN ativo tinyint;
UPDATE pacientes SET ativo =1;
