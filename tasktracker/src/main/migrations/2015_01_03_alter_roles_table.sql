USE task_tracker;

ALTER TABLE roles 
ADD COLUMN permissions TEXT NOT NULL AFTER name DEFAULT NULL;

UPDATE roles SET permissions = 'create_project create_task create_bug delete_task update_task create_user delete_user update_user' where id = 1;
UPDATE roles SET permissions = 'create_project create_task create_bug delete_task update_task' where id = 2;
UPDATE roles SET permissions = 'create_task update_task' where id = 3;
UPDATE roles SET permissions = 'create_task update_task' where id = 4;
UPDATE roles SET permissions = 'create_bug' where id = 5;