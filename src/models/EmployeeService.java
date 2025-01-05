package models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import controller.MongoDBController;
import org.bson.Document;

import javax.swing.JTextField;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public EmployeeService() {
        this.database = MongoDBController.getInstance().getDatabase();
        this.collection = database.getCollection("employees");
    }

    public void addEmployee(Employee employee, JTextField passwordTextField) {
        Document employeeDoc = new Document("_id", employee.getId())
                .append("username", employee.getUsername())
                .append("password", passwordTextField.getText())
                .append("name", employee.getName())
                .append("role", employee.getRole())
                .append("manager_id", employee.getManager_id())
                .append("leaveBalance", employee.getLeaveBalance());
        collection.insertOne(employeeDoc);
        System.out.println("Employee " + employee.getName() + "  created successfully!");
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (Document doc : collection.find()) {
            String id = doc.getString("_id");
            String username = doc.getString("username");
            String password = doc.getString("password");
            String name = doc.getString("name");
            String role = doc.getString("role");
            String manager_id = doc.getString("manager_id");
            int leaveBalance = doc.getInteger("leaveBalance");

            Employee employee = new Employee(username,password,name,role,manager_id,leaveBalance);

            try {
                Field idField = Employee.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(employee, id);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            employees.add(employee);
        }
        System.out.println("Employees fetched");
        return employees;
    }


    public void updateEmployee(String employeeId, Employee employee, JTextField passwordTextField) {
        Document updatedData = new Document()
                .append("_id", employeeId)
                .append("username", employee.getUsername())
                .append("password", passwordTextField.getText())
                .append("name", employee.getName())
                .append("role", employee.getRole())
                .append("manager_id", employee.getManager_id())
                .append("leaveBalance", employee.getLeaveBalance());

        collection.updateOne(Filters.eq("_id", employeeId), new Document("$set", updatedData));
        System.out.println("Employee " + employee.getName() + " updated!");
    }

    public void deleteEmployee(String employeeId) {
        collection.deleteOne(new Document("_id", employeeId));
        System.out.println("Employee with ID: " + employeeId + " deleted!");
    }
}