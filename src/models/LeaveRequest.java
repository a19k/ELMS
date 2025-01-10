package models;

import java.util.Date;
import java.util.UUID;

public class LeaveRequest {
    private final String id;
    private String employee_id;
    private String manager_id;
    private String reason;
    private Date startDate;
    private Date endDate;
    private String type;
    private String status;

    public static String TYPE_SICK="sick";
    public static String TYPE_VACATION="vacation";
    public static String TYPE_EMERGENCY="emergency";
    public static String TYPE_PARENTAL="parental";
    public static String TYPE_UNPAID="unpaid";
    public static String TYPE_MISC="misc";

    public static String STATUS_WAITING="waiting";
    public static String STATUS_APPROVED="approved";
    public static String STATUS_DENIED="denied";

    public LeaveRequest(String employee_id, String manager_id, String reason, Date startDate, Date endDate){
        if(employee_id==null || employee_id.isBlank()){
            throw new IllegalArgumentException("Employee ID cannot be empty!");
        }if(manager_id==null || manager_id.isBlank()){
            throw new IllegalArgumentException("Manager ID cannot be empty!");
        }if(reason==null || reason.isBlank()){
            throw new IllegalArgumentException("Reason cannot be empty!");
        }if(startDate==null){
            throw new IllegalArgumentException("Start date cannot be empty!");
        }if(startDate.before(new Date())){
            throw new IllegalArgumentException("Start date cannot earlier than now!");
        }if(endDate==null){
            throw new IllegalArgumentException("End date cannot be empty!");
        }if(endDate.before(startDate)){
            throw new IllegalArgumentException("End date cannot be earlier than Start date!");
        }

        this.id = UUID.randomUUID().toString();
        this.employee_id = employee_id;
        this.manager_id = manager_id;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;

        this.status = STATUS_WAITING;
    }

    public LeaveRequest(String employee_id, String manager_id, String reason, Date startDate, Date endDate, String type, String status){
        if(employee_id==null || employee_id.isBlank()){
            throw new IllegalArgumentException("Employee ID cannot be empty!");
        }if(manager_id==null || manager_id.isBlank()){
            throw new IllegalArgumentException("Manager ID cannot be empty!");
        }if(reason==null || reason.isBlank()){
            throw new IllegalArgumentException("Reason cannot be empty!");
        }if(startDate==null){
            throw new IllegalArgumentException("Start date cannot be empty!");
        }if(startDate.before(new Date())){
            throw new IllegalArgumentException("Start date cannot earlier than now!");
        }if(endDate==null){
            throw new IllegalArgumentException("End date cannot be empty!");
        }if(endDate.before(startDate)){
            throw new IllegalArgumentException("End date cannot be earlier than Start date!");
        }

        this.id = UUID.randomUUID().toString();
        this.employee_id = employee_id;
        this.manager_id = manager_id;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type=type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getManager_id() {
        return manager_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(type==null || type.isBlank() || !(type.matches(TYPE_SICK) || type.matches(TYPE_EMERGENCY) || type.matches(TYPE_VACATION) || type.matches(TYPE_PARENTAL) || type.matches(TYPE_UNPAID) || type.matches(TYPE_MISC)))
            throw new IllegalArgumentException("Type must be one of the following: sick, emergency, vacation, parental, unpaid, misc");
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status==null || status.isBlank() || !(status.matches(STATUS_WAITING) || status.matches(STATUS_APPROVED) || status.matches(STATUS_DENIED)))
            throw new IllegalArgumentException("Status must be one of the following: waiting, appproved, denied");
        this.status = status;
    }

    public String[] toStringArray() {
        return new String[]{
                id,
                employee_id,
                manager_id,
                reason,
                startDate.toString(),
                endDate.toString(),
                type,
                status
                };
    }
}
