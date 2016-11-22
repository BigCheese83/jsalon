@echo off
asadmin create-jdbc-connection-pool --datasourceclassname=org.postgresql.ds.PGConnectionPoolDataSource --restype=javax.sql.ConnectionPoolDataSource --property portNumber=5432:password=qwerty:user=postgres:serverName=localhost:databaseName=salon SalonPGConnectionPool & ^
asadmin ping-connection-pool SalonPGConnectionPool & ^
asadmin create-jdbc-resource --connectionpoolid SalonPGConnectionPool jdbc/salon & ^
asadmin list-jdbc-resources