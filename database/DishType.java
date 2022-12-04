package database;

import java.util.HashMap;

public class DishType {
    private HashMap<Integer,String> userRoleTable = new HashMap<>();

    public DishType() {
        this.userRoleTable.put(1,"main dish");
        this.userRoleTable.put(2,"side dish");
        this.userRoleTable.put(3,"appetizer");
        this.userRoleTable.put(4,"drinks");
        this.userRoleTable.put(5,"dessert");
    }

    public String getDishType(int key){
        return userRoleTable.get(key);
    }
}
