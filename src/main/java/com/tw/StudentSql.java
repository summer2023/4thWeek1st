package com.tw;

import java.util.Collections;
import java.util.List;

public class StudentSql {
    private List<String> studentInfo;
    private List<String> idList;

    public StudentSql(List<String> studentInfo,List<String> idList) {
        this.studentInfo=studentInfo;
        this.idList = idList;
    }


    public void addStudent(Student student) {
        int stusum = student.getSum();
        String studentInfo = student.getId() + "|" + student.getName() + "|" + student.getMath() + "|" +
                student.getChinese() + "|" + student.getEnglish() + "|" + student.getProgram() + "|" + String.valueOf(student.getAverge()) + "|" + String.valueOf(stusum);
        this.studentInfo.add(studentInfo);
        this.idList.add(student.getId());

    }


    public List<String> getIdList() {
        return this.idList;
    }
    public List<String> getStudentInfo() {
        return this.studentInfo;
    }

}
