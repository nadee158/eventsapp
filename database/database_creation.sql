DROP DATABASE IF EXISTS sportsman_db;
CREATE DATABASE  sportsman_db
DEFAULT CHARACTER SET  =UTF8
DEFAULT COLLATE =UTF8_GENERAL_CI ;

CREATE USER 'sportsman_user'@'%' IDENTIFIED BY 'sportsman_user';
GRANT ALL ON sportsman_db.* TO 'sportsman_user'@'%';