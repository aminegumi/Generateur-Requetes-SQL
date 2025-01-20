package compilation_project;

import java.util.List;

public class ColumnDefinition {
    private String name;
    private String type;
    private List<String> constraints;

    public ColumnDefinition(String name, String type, List<String> constraints) {
        this.name = name;
        this.type = type;
        this.constraints = constraints;
    }

    public String toSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append(name).append(" ").append(type);
        
        for (String constraint : constraints) {
            sql.append(" ").append(constraint);
        }
        
        return sql.toString();
    }
}