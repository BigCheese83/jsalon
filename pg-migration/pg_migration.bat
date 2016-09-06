java -jar ./target/jsalon-pg-migration-1.0-jar-with-dependencies.jar ^
--url=jdbc:postgresql://localhost/salon ^
--username=postgres ^
--password=qwerty ^
--changeLogFile=ru/bigcheese/jsalon/db/changelog/db.changelog-master.xml ^
--logLevel=debug  %1 %2