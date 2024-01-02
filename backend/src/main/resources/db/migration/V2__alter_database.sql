ALTER TABLE notetaking_note ALTER COLUMN deleted SET NOT NULL;
ALTER TABLE notetaking_note ALTER COLUMN title SET NOT NULL;
ALTER TABLE notetaking_note ALTER COLUMN content SET NOT NULL;
--ALTER TABLE notetaking_note ADD CONSTRAINT content_unique UNIQUE (content);