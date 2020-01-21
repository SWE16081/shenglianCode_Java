drop database ${dbName};
create database ${dbName} DEFAULT CHARSET utf8 COLLATE utf8_general_ci;;
DROP TABLE IF EXISTS ${dbName}.databasechangelog;
DROP TABLE IF EXISTS ${dbName}.databasechangeloglock;