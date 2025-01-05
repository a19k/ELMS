package models;

import java.util.UUID;

public class Employee {
    private final String id;
    private String username;
    private String password;
    private String name;
    private String role;
    private String manager_id;
    private int leaveBalance;

    public Employee(String username, String password, String name, String role, String manager_id, int leaveBalance){
        if(username==null || username.isBlank()){
            throw new IllegalArgumentException("Username cannot be empty!");
        }if(password==null || password.isBlank()){
            throw new IllegalArgumentException("Password cannot be empty!");
        }if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty!");
        }if(role==null || role.isBlank() || !(role.matches("employee") || role.matches("manager") || role.matches("admin"))){
            throw new IllegalArgumentException("Role must be one of the following: employee, manager, admin");
        }if(manager_id==null || manager_id.isBlank()){
            throw new IllegalArgumentException("Manager ID cannot be empty!");
        }if(leaveBalance<0){
            throw new IllegalArgumentException("Leave balance cannot be negative!");
        }

        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.manager_id = manager_id;
        this.leaveBalance = leaveBalance;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role==null || role.isBlank() || !(role.matches("employee") || role.matches("manager") || role.matches("admin")))
            throw new IllegalArgumentException("Role must be one of the following: employee, manager, admin");
        this.role = role;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public void raiseLeaveBalance(int difference){
        this.leaveBalance += difference;
    }

    public void lowerLeaveBalance(int difference){
        this.leaveBalance -= difference;
    }
}
