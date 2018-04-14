package com.tw;

public class Student {
    private String name;
    private String id;
    private String math;
    private String Chinese;
    private String English;
    private String program;
    private double averge;
    private int sum;

    public Student(String name, String id, String math, String Chinese, String English, String program) {
        this.name = name;
        this.id=id;
        this.math=math;
        this.Chinese=Chinese;
        this.English=English;
        this.program=program;
        this.sum=Integer.valueOf(this.math)+Integer.valueOf(this.Chinese)+Integer.valueOf(this.English)+Integer.valueOf(this.program);
        this.averge=sum/(double)4;
    }

    public String getName() {
        return this.name;
    }
    public String getId() {
        return this.id;
    }
    public String getMath() {
        return this.math;
    }
    public String getChinese() {
        return this.Chinese;
    }
    public String getEnglish() {
        return this.English;
    }
    public String getProgram() {
        return this.program;
    }

    public double getAverge() {
        return this.averge;
    }

    public int getSum() {
        return  this.sum;
    }
}
