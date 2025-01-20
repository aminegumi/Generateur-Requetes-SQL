package compilation_project;

import java.util.*;

public class TableDefinition {
    private String name;
    private List<ColumnDefinition> columns;

    public TableDefinition(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
    }

    public void addColumn(ColumnDefinition column) {
        columns.add(column);
    }

    public String toSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(name).append(" (\n");
        
        for (int i = 0; i < columns.size(); i++) {
            sql.append("    ").append(columns.get(i).toSQL());
            if (i < columns.size() - 1) {
                sql.append(",");
            }
            sql.append("\n");
        }
        
        sql.append(");");
        return sql.toString();
    }
}