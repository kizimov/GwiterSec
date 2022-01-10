databaseChangeLog = {

    changeSet(author: "okizimo", id: "Create person_person table") {
        createTable(tableName: "person_person") {
            column(name: "person_subscriptions_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "person_id", type: "BIGINT")
        }
    }
    changeSet(author: "okizimo", id: "person_person table-1") {
        addForeignKeyConstraint(baseColumnNames: "person_subscriptions_id",
                                baseTableName: "person_person",
                                constraintName: "FK6n3h2k2e1ef3bck2xwmeqk6u1",
                                deferrable: "false",
                                initiallyDeferred: "false",
                                referencedColumnNames: "id",
                                referencedTableName: "person",
                                validate: "true")
    }

    changeSet(author: "okizimo", id: "person_person table-2") {
        addForeignKeyConstraint(baseColumnNames: "person_id",
                                baseTableName: "person_person",
                                constraintName: "FKb0usqkpsjgv42fmsu4e0h2gyl",
                                deferrable: "false",
                                initiallyDeferred: "false",
                                referencedColumnNames: "id",
                                referencedTableName: "person",
                                validate: "true")
    }
}
