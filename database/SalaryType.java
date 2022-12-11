package database;

import java.util.HashMap;
import java.util.Objects;

public class SalaryType {
    private HashMap<Integer,String> userRoleTable = new HashMap<>();

    public SalaryType() {
        this.userRoleTable.put(1,"monthly");
        this.userRoleTable.put(2,"hourly");
    }

    public String getSalaryType(int key){
        return userRoleTable.get(key);
    }

    public int getSalaryType(String value){
        for (Integer item: userRoleTable.keySet()) {
            if (Objects.equals(userRoleTable.get(item), value)) return item;
        }
        return 0;
    }
}
