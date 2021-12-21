databaseChangeLog = {
    changeSet(author: "okizimo (generated)", id: "1639734226048-2") {
        createTable(tableName: "person_role") {
            column(name: "person_id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "person_rolePK")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "person_rolePK")
            }
        }
    }
    changeSet(author: "okizimo", id: "add Foreign Key Constraint person-role-1") {
        addForeignKeyConstraint(baseColumnNames: "person_id", baseTableName: "person_role", constraintName: "FKhyx1efsls0f00lxs6xd4w2b3j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "person", validate: "true")
    }
    changeSet(author: "okizimo", id: "add Foreign Key Constraint person-role-2") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "person_role", constraintName: "FKs7asxi8amiwjjq1sonlc4rihn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", validate: "true")
    }

}
