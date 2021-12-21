databaseChangeLog = {

    changeSet(author: "okizimo (generated)", id: "1639734226048-4") {
        createTable(tableName: "twit") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "twitPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "header", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "content", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "person_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }
    changeSet(author: "okizimo (generated)", id: "add Foreign Key onstraint twit") {
        addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "twit", constraintName: "FKlhdnp3l0yxx3ukk35fx1jw0x8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", validate: "true")
    }
}
