package database;

import java.util.HashMap;
import java.util.Objects;

public class DishType {
    private HashMap<Integer,String> userRoleTable = new HashMap<>();

    public DishType() {
        this.userRoleTable.put(1,"main dish");
        this.userRoleTable.put(2,"side dish");
        this.userRoleTable.put(3,"appetizer");
        this.userRoleTable.put(4,"drinks");
        this.userRoleTable.put(5,"dessert");
    }

    public String getDishType(Integer key){
        return userRoleTable.get(key);
    }

    public int getDishType(String value){
        for (Integer item: userRoleTable.keySet()) {
            if (Objects.equals(userRoleTable.get(item), value)) return item;
        }
        return 0;
    }
}
