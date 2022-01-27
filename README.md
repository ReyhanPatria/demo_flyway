# demo_flyway
This is demo project for showing how to work with Flyway as an extension to the Maven build tool, the project itself is build using Spring Boot.


## Setup
Before using the Flyway extension to Maven, we'll need to add the plugin to the `pom.xml`. In this project the `pom.xml` have been set up to include it.

```
<plugin>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-maven-plugin</artifactId>
  <version>8.4.2</version>
</plugin>
```

Flyway needs some configuration before you can use it, in this demo we'll separate it to a different `.conf` file but if you want you can configure Flyway right in the 
`pom.xml`. To define the external configuration file we'll need to tell the plugin where it's located.

```
<plugin>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-maven-plugin</artifactId>
  <version>8.4.2</version>
  
  <configuration>
    <configFiles>
      <configFile>
        src/main/resources/flyway.conf
      </configFile>
    </configFiles>
  </configuration>
</plugin>
```

Flyway supports connection to lots of the most popular databases which can be viewed [here](https://flywaydb.org/documentation/). 

For enabling Flyway to interact with your database, Flyway provides custom connector dependencies, you can check your own database connector in the Flyway's documentation. 
For this demo project we'll be using Flyway's custom MySQL dependency to enable Flyway to interact with our MySQL database, which we'll be adding as a dependency to the 
Flyway plugin.

```
<plugin>
  <groupId>org.flywaydb</groupId>
  <artifactId>flyway-maven-plugin</artifactId>
  <version>8.4.2</version>
  
  <configuration>
    <configFiles>
      <configFile>
        src/main/resources/flyway.conf
      </configFile>
    </configFiles>
  </configuration>
  
  <dependencies>
    <dependency>
      <groupId>org.flywaydb</groupId>
      <artifactId>flyway-mysql</artifactId>
      <version>8.4.2</version>
    </dependency>
  </dependencies>
</plugin>
```


## Configuration File
Some basic configuration for Flyway needs to be set before you can use the tool. The most basic configuration is the connection to the database and the location of the 
migration scripts.
```
# Connection configs
flyway.driver=com.mysql.cj.jdbc.Driver
flyway.url=jdbc:mysql://localhost/
flyway.user=root
flyway.password=

# Location of migration files
flyway.locations=filesystem:src/main/resources/db/migration
```

Notice in the connection configuration we didn't specify which database or schema we'll be using, that is because Flyway could be used to affect multiple schemas at once. 
So we'll need to specify which schemas we'll be affecting.
```
# Create a new schema/database if it doesn't already exist
flyway.createSchemas=true
flyway.schemas=demo_flyway
```

The `flyway.createSchema` config lets Flyway to create the schema(s) if it doesn't already exist.


## Using Flyway
### Migrations
To start using Flyway you need to write some migration scripts first and put it in the locations folder that you set in the configuration.
Flyway uses convention to name the migration scripts, you can see the details [here](https://flywaydb.org/documentation/concepts/migrations#naming).
Migration scripts in Flyway is written in the native SQL for the database product that you're using.


### Applying Migrations
To apply the migration scripts you can use the `mvn flyway:migrate` command. It will automatically apply all the migration scripts in order of the version numbering.


### Check Database State
To check the current database state you can use the `mvn flyway:info` command. Which will produces a table of ran scripts and the state of those scripts.
```
+-----------+---------+---------------------------------+--------+---------------------+---------+
| Category  | Version | Description                     | Type   | Installed On        | State   |       
+-----------+---------+---------------------------------+--------+---------------------+---------+
|           |         | << Flyway Schema Creation >>    | SCHEMA | 2022-01-27 09:36:04 | Success |
| Versioned | 1       | Create User Table               | SQL    | 2022-01-27 09:36:04 | Success |
| Versioned | 1.1     | Insert User Data                | SQL    | 2022-01-27 09:36:04 | Success |
| Versioned | 1.2     | Create Product Table            | SQL    | 2022-01-27 09:36:04 | Success |
| Versioned | 1.3     | Insert Product Data             | SQL    | 2022-01-27 09:36:04 | Success |
| Versioned | 1.4     | Create Transactions Table       | SQL    | 2022-01-27 09:36:05 | Success |
| Versioned | 1.5     | Insert Transaction Data         | SQL    | 2022-01-27 09:36:05 | Success |
| Versioned | 1.6     | Insert Transaction Details Data | SQL    | 2022-01-27 09:36:05 | Success |
+-----------+---------+---------------------------------+--------+---------------------+---------+
```


### In Case of Failure
If one or more scripts failed to run then database state will look like 
```
+-----------+---------+---------------------------------+--------+---------------------+---------+
| Category  | Version | Description                     | Type   | Installed On        | State   |
+-----------+---------+---------------------------------+--------+---------------------+---------+
|           |         | << Flyway Schema Creation >>    | SCHEMA | 2022-01-27 10:01:28 | Success |
| Versioned | 1       | Create User Table               | SQL    | 2022-01-27 10:01:28 | Success |
| Versioned | 1.1     | Insert User Data                | SQL    | 2022-01-27 10:01:28 | Success |
| Versioned | 1.2     | Create Product Table            | SQL    | 2022-01-27 10:01:28 | Success |
| Versioned | 1.3     | Insert Product Data             | SQL    | 2022-01-27 10:01:28 | Success |
| Versioned | 1.4     | Create Transactions Table       | SQL    | 2022-01-27 10:01:28 | Failed  |
| Versioned | 1.5     | Insert Transaction Data         | SQL    |                     | Pending |
| Versioned | 1.6     | Insert Transaction Details Data | SQL    |                     | Pending |
+-----------+---------+---------------------------------+--------+---------------------+---------+
```

To reset the failed migration you'll need to run the `mvn flyway:repair` command to change the `Failed` state of the script to be `Pending`. The you can run the migrate command
again.


### Cleaning The Database
Cleaning the database is basicly resetting the database and dropping the schema entirely, which you can use the `mvn flyway:clean` command.
