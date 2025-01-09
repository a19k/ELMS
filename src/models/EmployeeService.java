package models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import controller.MongoDBController;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.swing.JTextField;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public EmployeeService() {
        this.database = MongoDBController.getInstance().getDatabase();//db
        this.collection = database.getCollection("Employees");//collection/entity/table
    }

    public void addEmployee(Employee employee) {
        Document employeeDoc = new Document("_id", employee.getId())//converts Employee object into Document
                .append("username", employee.getUsername())
                .append("password", employee.getPassword())
                .append("name", employee.getName())
                .append("role", employee.getRole())
                .append("manager_id", employee.getManager_id())
                .append("leaveBalance", employee.getLeaveBalance());
        collection.insertOne(employeeDoc);
        System.out.println("Employee " + employee.getName() + "  created successfully!");
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (Document doc : collection.find()) {//for every Employee in db as a document, separate into variables
            String id = doc.getString("_id");
            String username = doc.getString("username");
            String password = doc.getString("password");
            String name = doc.getString("name");
            String role = doc.getString("role");
            String manager_id = doc.getString("manager_id");
            int leaveBalance = doc.getInteger("leaveBalance");

            Employee employee = new Employee(username,password,name,role,manager_id,leaveBalance);//create Employee object from variables

            try {//overwrite the auto-assigned id with already existing(real) id
                Field idField = Employee.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(employee, id);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            employees.add(employee);
        }
        System.out.println("Employees fetched");
        return employees;//return the List of all Employees
    }

    public Employee findOneEmployee(String filteringField,String filteringValue){

        Bson filter = Filters.eq(filteringField, filteringValue);//filter by field
        Document doc = collection.find(filter).first();//find first ocurrence

        if(doc==null)return null;//not found

        String id = doc.getString("_id");
        String username = doc.getString("username");
        String password = doc.getString("password");
        String name = doc.getString("name");
        String role = doc.getString("role");
        String manager_id = doc.getString("manager_id");
        int leaveBalance = doc.getInteger("leaveBalance");

        Employee employee = new Employee(username,password,name,role,manager_id,leaveBalance);//create Employee object from variables

        return employee;
    }


    public void updateEmployee(String employeeId, Employee employee) {
        Document updatedData = new Document()
                .append("_id", employeeId)
                .append("username", employee.getUsername())
                .append("password", employee.getPassword())
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