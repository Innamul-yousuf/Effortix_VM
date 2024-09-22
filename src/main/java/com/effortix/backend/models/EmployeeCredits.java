package com.effortix.backend.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "employee_credits")
public class EmployeeCredits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ecID")
    private Long ecID;

    @Column(name = "EId", nullable = false)
    private Long EId;

    @Column(name = "Date", nullable = false)
    private Date date;

    @Column(name = "Credits", nullable = false)
    private Double credits;

    @Column(name = "TimePeriod")
    private String timePeriod;

    @Column(name = "IssueID")
    private Long issueID;

    @Column(name = "TId")
    private Long TId;

    @Column(name = "CreditType")
    private String creditType;

    @ManyToOne
    @JoinColumn(name = "EId", insertable = false, updatable = false)
    private Employee employee;

   

    @ManyToOne
    @JoinColumn(name = "TId", insertable = false, updatable = false)
    private Ticket ticket;

    // Getters and Setters

    public Long getEcID() {
        return ecID;
    }

    public void setEcID(Long ecID) {
        this.ecID = ecID;
    }

    public Long getEId() {
        return EId;
    }

    public void setEId(Long EId) {
        this.EId = EId;
    }


    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Long getIssueID() {
        return issueID;
    }

    public void setIssueID(Long issueID) {
        this.issueID = issueID;
    }

    public Long getTId() {
        return TId;
    }

    public void setTId(Long TId) {
        this.TId = TId;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}