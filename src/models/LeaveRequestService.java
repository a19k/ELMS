package models;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import controller.MongoDBController;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveRequestService {
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public LeaveRequestService() {
        this.database = MongoDBController.getInstance().getDatabase();//db
        this.collection = database.getCollection("Leave Requests");//collection/entity/table
    }

    public void addLeaveRequest(LeaveRequest leaveRequest) {
        Document leaveRequestDoc = new Document("_id", leaveRequest.getId())//converts LeaveRequest object into Document
                .append("employee_id", leaveRequest.getEmployee_id())
                .append("manager_id", leaveRequest.getManager_id())
                .append("reason", leaveRequest.getReason())
                .append("startDate", leaveRequest.getStartDate())
                .append("endDate", leaveRequest.getEndDate())
                .append("type", leaveRequest.getType())
                .append("status", leaveRequest.getStatus());
        collection.insertOne(leaveRequestDoc);
        System.out.println("Leave Request #" + leaveRequest.getId() + "  created successfully!");
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        for (Document doc : collection.find()) {//for every LeaveRequest in db as a document, separate into variables
            String id = doc.getString("_id");
            String employee_id = doc.getString("employee_id");
            String manager_id = doc.getString("manager_id");
            String reason = doc.getString("reason");
            Date startDate = doc.getDate("startDate");
            Date endDate = doc.getDate("endDate");
            String type = doc.getString("type");
            String status = doc.getString("status");

            LeaveRequest leaveRequest = new LeaveRequest(employee_id,manager_id,reason,startDate,endDate,type,status);//create LeaveRequest object from variables

            try {//overwrite the auto-assigned id with already existing(real) id
                Field idField = LeaveRequest.class.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(leaveRequest, id);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            leaveRequests.add(leaveRequest);
        }
        System.out.println("LeaveRequests fetched");
        return leaveRequests;//return the List of all LeaveRequests
    }

    public LeaveRequest findOneLeaveRequest(String filteringField,String filteringValue){

        Bson filter = Filters.eq(filteringField, filteringValue);//filter by field
        Document doc = collection.find(filter).first();//find first ocurrence

        if(doc==null)return null;//not found

        String id = doc.getString("_id");
        String employee_id = doc.getString("employee_id");
        String manager_id = doc.getString("manager_id");
        String reason = doc.getString("reason");
        Date startDate = doc.getDate("startDate");
        Date endDate = doc.getDate("endDate");
        String type = doc.getString("type");
        String status = doc.getString("status");

        LeaveRequest leaveRequest = new LeaveRequest(employee_id,manager_id,reason,startDate,endDate,type,status);//create LeaveRequest object from variables

        try {//overwrite the auto-assigned id with already existing(real) id
            Field idField = LeaveRequest.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(leaveRequest, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return leaveRequest;
    }


    public void updateLeaveRequest(String leaveRequestId, LeaveRequest leaveRequest) {
        Document updatedData = new Document("_id", leaveRequest.getId())//converts LeaveRequest object into Document
                .append("employee_id", leaveRequest.getEmployee_id())
                .append("manager_id", leaveRequest.getManager_id())
                .append("reason", leaveRequest.getReason())
                .append("startDate", leaveRequest.getStartDate())
                .append("endDate", leaveRequest.getEndDate())
                .append("type", leaveRequest.getType())
                .append("status", leaveRequest.getStatus());

        collection.updateOne(Filters.eq("_id", leaveRequestId), new Document("$set", updatedData));
        System.out.println("Leave Request #" + leaveRequest.getId() + " updated!");
    }

    public void deleteLeaveRequest(String leaveRequestId) {
        collection.deleteOne(new Document("_id", leaveRequestId));
        System.out.println("LeaveRequest #" + leaveRequestId + " deleted!");
    }

}
