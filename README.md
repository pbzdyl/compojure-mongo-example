# clj-api

## DB setup

### MongoDB

`brew install mongodb`
and start it.

### PostgreSQL

`brew install postgresql`
and start it.

Create DB:

`createdb -Eutf8 docusign-contexts`

## Config

Change import at the top in  `src/clj_api/handler.clj` to choose persistence implementation.

Change `db-choice` in `src/clj_api/db_config` to choose between H2, HSQLDB and PostgreSQL.

## Usage

### Run the application locally

`lein ring server`

### Run the tests

`lein test`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`

## License

Copyright © Piotrek Bzdyl
