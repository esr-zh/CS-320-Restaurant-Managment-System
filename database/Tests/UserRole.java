package database.Tests;

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

    public String getUserRole(int key){
        return userRoleTable.get(key);
    }

}
