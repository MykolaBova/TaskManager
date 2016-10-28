# CREATE new database
CREATE SCHEMA `task_manager` ;
DROP TABLE IF EXISTS active_tasks;
DROP TABLE IF EXISTS closed_tasks;

# Create table for active tasks
CREATE TABLE active_tasks (
  id INT NOT NULL AUTO_INCREMENT,
  description VARCHAR(100) NOT NULL ,
  priority VARCHAR(10) NOT NULL ,
  time DATETIME NOT NULL ,
  PRIMARY KEY (id)
);

# Create table for closed tasks
CREATE TABLE closed_tasks (
  id INT NOT NULL AUTO_INCREMENT,
  description VARCHAR(100) NOT NULL ,
  priority VARCHAR(10) NOT NULL ,
  time DATETIME NOT NULL ,
  PRIMARY KEY (id)
);

# Populate tables test data

INSERT INTO closed_tasks (id, description, priority, time) VALUES (
    10, 'Save world', 'HIGH', '2016-10-26 09:00:00'
);
INSERT INTO active_tasks (id, description, priority, time) VALUES
  (11, 'Create test task for ProgForce', 'HIGH', '2016-10-30 15:00:00'),
  (12, 'Do something', 'LOW', '2016-10-20 11:00:00');