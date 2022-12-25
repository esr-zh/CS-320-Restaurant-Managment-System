package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserRole {
    private HashMap<Integer,String> userRoleTable = new HashMap<>();

    public UserRole() {
        this.userRoleTable.put(1,"customer");
        this.userRoleTable.put(2,"admin");
        this.userRoleTable.put(3,"chef");
        this.userRoleTable.put(4,"waiter");
    }

    public String getUserRole(Integer key){
        System.out.println(key);
        return userRoleTable.get(key);
    }

    public int getUserRole(String value){
        for (Integer item: userRoleTable.keySet()) {
            if (Objects.equals(userRoleTable.get(item), value)) return item;
        }
        return 0;
    }

}
