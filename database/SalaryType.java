package database;

import java.util.HashMap;

public class SalaryType {
    private HashMap<Integer,String> userRoleTable = new HashMap<>();

    public SalaryType() {
        this.userRoleTable.put(1,"monthly");
        this.userRoleTable.put(2,"hourly");
    }

    public String getSalaryType(int key){
        return userRoleTable.get(key);
    }
}
