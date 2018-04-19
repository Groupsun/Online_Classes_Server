package com.scut.server.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class StudentCourser {

    private String courser_no;
    private String courser_name;
    private String courser_description;
    private Date courser_begin_date;
    private Date courser_end_date;
    private int courser_status;
    private String student_openid;

    public StudentCourser() {
    }

    public String getCourser_no() {
        return courser_no;
    }

    public void setCourser_no(String courser_no) {
        this.courser_no = courser_no;
    }

    public String getCourser_name() {
        return courser_name;
    }

    public void setCourser_name(String courser_name) {
        this.courser_name = courser_name;
    }

    public String getCourser_description() {
        return courser_description;
    }

    public void setCourser_description(String courser_description) {
        this.courser_description = courser_description;
    }

    public Date getCourser_begin_date() {
        return courser_begin_date;
    }

    public void setCourser_begin_date(Date courser_begin_date) {
        this.courser_begin_date = courser_begin_date;
    }

    public Date getCourser_end_date() {
        return courser_end_date;
    }

    public void setCourser_end_date(Date courser_end_date) {
        this.courser_end_date = courser_end_date;
    }

    public int getCourser_status() {
        return courser_status;
    }

    public void setCourser_status(int courser_status) {
        this.courser_status = courser_status;
    }

    @JsonProperty(value="is_selected")
    public int getStudent_openid() {
        System.out.println(student_openid);
        if(student_openid != null){
            return 1;
        }else{
            return 0;
        }
    }

    public void setStudent_openid(String student_openid) {
        this.student_openid = student_openid;
    }
}
