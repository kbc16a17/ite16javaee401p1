package db.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="Records")
public class Record implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer accountId;
    private Date date;
    private Integer minBp;
    private Integer maxBp;
    private Integer heartRate;
    private String notes;
    
    public Record() { }
    public Record(Integer accountId, Date date, Integer minBp, Integer maxBp, Integer heartRate, String notes) {
        this.accountId = accountId;
        this.date = date;
        this.minBp = minBp;
        this.maxBp = maxBp;
        this.heartRate = heartRate;
        this.notes = notes;
    }
    
    public Integer getId() {
        return id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getMinBp() {
        return minBp;
    }

    public void setMinBp(Integer minBp) {
        this.minBp = minBp;
    }

    public Integer getMaxBp() {
        return maxBp;
    }

    public void setMaxBp(Integer maxBp) {
        this.maxBp = maxBp;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public String toCsv() {
        return String.join(",", date.toString(), maxBp.toString(), minBp.toString(), heartRate.toString(), notes);
    }
}
