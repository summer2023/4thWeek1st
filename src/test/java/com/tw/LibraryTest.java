package com.tw;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {
    @Test
    public void should_student_return_element() throws Exception {
        String[] p = {"张三", "2018", "75", "95", "80", "80"};
        Student student = new Student(p[0], p[1], p[2], p[3], p[4], p[5]);
        assertEquals(student.getName(), "张三");
        assertEquals(student.getId(), "2018");
        assertEquals(student.getMath(), "75");
        assertEquals(student.getChinese(), "95");
        assertEquals(student.getEnglish(), "80");
        assertEquals(student.getProgram(), "80");
        assertEquals(student.getSum(), 330);
    }
//测试添加学生操作，即测试branch1
    @Test
    public void should_studentsql_have_idList_name_and_age() throws Exception {
        StudentSql studentSql = new StudentSql(new ArrayList<String>(), new ArrayList<String>());
        String[] p = {"张三", "2018", "75", "95", "80", "80"};
        Student student1 = new Student(p[0], p[1], p[2], p[3], p[4], p[5]);
        studentSql.addStudent(student1);
        List<String> idlsexp = new ArrayList<String>();
        idlsexp.add("2018");
        List<String> infolsexp = new ArrayList<String>();
        infolsexp.add("2018|张三|75|95|80|80|82.5|330");

        assertEquals(studentSql.getIdList(), idlsexp);
        assertEquals(studentSql.getStudentInfo(), infolsexp);
    }

    @Test
    public void should_transcript_have_title() throws Exception {
        Transcript transcript = new Transcript();
        List<String> tranlsexp = new ArrayList<String>();
        tranlsexp.add("成绩单");
        tranlsexp.add("姓名|数学|语文|英语|编程|平均分|总分");
        tranlsexp.add("========================");

        assertEquals(transcript.getTranscriptInfo(), tranlsexp);

    }

//测试选择的学生的成绩单输出，总分数平均数按选择的学生总分数平均计算。中位数同理。即测试branch2
    @Test
    public void should_transcript_have_added_student_and_show() throws Exception {
        StudentSql studentSql = new StudentSql(new ArrayList<String>(), new ArrayList<String>());
        String[] p = {"张三", "2018", "75", "95", "80", "80"};
        Student student1 = new Student(p[0], p[1], p[2], p[3], p[4], p[5]);
        String[] q = {"李四", "2005", "85", "80", "70", "90"};
        Student student2 = new Student(q[0], q[1], q[2], q[3], q[4], q[5]);
        studentSql.addStudent(student1);
        studentSql.addStudent(student2);
        Transcript transcript = new Transcript();
        String[] idarr = {"2018", "2005"};
        transcript.addSelectStudent(studentSql, idarr);
        transcript.addAverageScore();
        transcript.addMedianScore();

        List<String> tranlsexp = new ArrayList<String>();
        tranlsexp.add("成绩单");
        tranlsexp.add("姓名|数学|语文|英语|编程|平均分|总分");
        tranlsexp.add("========================");
        tranlsexp.add("张三|75|95|80|80|82.5|330");
        tranlsexp.add("李四|85|80|70|90|81.25|325");
        tranlsexp.add("========================");
        tranlsexp.add("全班总分平均数：327.5");
        tranlsexp.add("全班总分中位数：327.5");

        assertEquals(tranlsexp, transcript.getTranscriptInfo());
    }

//分别测试1和2情况下的输入格式
    @Test
    public void should_library_judgement_Student_format() throws Exception {
        Library library = new Library();
        String input1 = "张三,2018,数学:75,语文:90,英语:80,编程:80";
        String input2 = "张3,2018,数学:75,语文:90,英语:80,编程:80";
        String input3 = "张三,20一8,数学:75,语文:90,英语:80,编程:80";
        String input4 = "张三,2018,数学:a,语文:90,英语:80,编程:80";

        assertTrue(library.judgementStudent(input1));
        assertFalse(library.judgementStudent(input2));
        assertFalse(library.judgementStudent(input3));
        assertFalse(library.judgementStudent(input4));

    }

    @Test
    public void should_library_judgement_Number_format() throws Exception {
        Library library = new Library();
        String input1 = "2018,2005";
        String input2 = "张3,2008";

        assertTrue(library.judgementNumber(input1));
        assertFalse(library.judgementNumber(input2));
    }

    //problem:对于game和每个branch都有的同一个Scanner,怎么用mock直接传入输入值

}