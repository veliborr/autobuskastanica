CREATE USER IF NOT EXISTS userstanica IDENTIFIED BY 'root';

DROP DATABASE IF EXISTS autobuskastanica;
CREATE DATABASE autobuskastanica DEFAULT CHARACTER SET utf8;

USE autobuskastanica;

GRANT ALL ON autobuskastanica.* TO 'userstanica'@'%';

FLUSH PRIVILEGES;