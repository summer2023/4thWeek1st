package com.tw;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {
    private Transcript transcript;
    private StudentSql studentsql;

    public Library() {

    }

    /*public Library(StudentSql studentsql) {
        this.studentsql = studentsql;
    }*/

    public void game() {
        Transcript transcript=new Transcript();
        System.out.print("```\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n" +
                "```");

        Scanner sc = new Scanner(System.in);
        int flag=sc.nextInt();
        switch (flag) {
            case 1: branch1(sc);break;
            case 2: branch2(sc);break;
            case 3: sc.close();
        }


    }

    public void branch1(Scanner sc) {
        System.out.print("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n" +
                "```");
        String input=sc.nextLine();
        if (!judgementStudent(input)) {
            System.out.print("```\n" +
                    "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n" +
                    "```");
        } else {
            String[] parts=input.split(",");
            String[] gen=new String[4];
            for(int i=2;i<6;i++) {
               gen[i-2]=parts[i].split(":")[1];
            }
            Student student = new Student(parts[0], parts[1], gen[0], gen[1], gen[2], gen[3]);
            this.studentsql.addStudent(student);
            System.out.print("学生"+parts[0]+"的成绩被添加");
            System.out.println("\n" +
                    "```\n" +
                    "1. 添加学生\n" +
                    "2. 生成成绩单\n" +
                    "3. 退出请输入你的选择（1～3）：\n" +
                    "```");
        }
    }

    public void branch2(Scanner sc) {
        System.out.print("```\n" +
                "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n" +
                "```");
        String input=sc.nextLine();
        if (!judgementNumber(input)) {
            System.out.print("```\n" +
                    "请按正确的格式输入（格式： 学号, 学号,...）：\n" +
                    "```");
        }else {
            String[] ids=input.split(",");
            this.transcript.addSelectStudent(studentsql,ids);
            this.transcript.addAverageScore();
            this.transcript.addMedianScore();
            for (String line : this.transcript.getTranscriptInfo()) {
                System.out.println();
            }
        }

    }




    public boolean judgementStudent(String string) {
        boolean flag=false;
        String[] parts = string.split(",");

        if (parts.length == 6 && !hasDigit(parts[0]) && isDigit(parts[1])) {
            flag=true;
            for (int i = 2; i < 6; i++) {
                if (!classAndScore(parts[i])) {
                    flag = false;
                }
            }
        }else{
            flag = false;
        }
       return flag;
    }

    public boolean judgementNumber(String string){
        boolean flag=true;
        String[] parts = string.split(",");
        for (String s : parts) {
            if (!isDigit(s)) {
                flag=false;
            }
        }
        return flag;
    }

    public boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    public boolean isDigit(String content) {
        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) content);
        return matcher.matches();
    }

    public boolean classAndScore(String string) {
        boolean flag=false;
        if (string.contains(":") && string.split(":").length==2 && !hasDigit(string.split(":")[0])&&isDigit(string.split(":")[1])) {
            flag=true;
        }
        return flag;
    }
}
