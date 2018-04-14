package com.tw;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Transcript {
    private List<String> transcriptInfo;
    private int sumScore;
    private int number;
    private double sumaverage;
    private double median;
    private List<Integer> score;
    private final String splitLine="========================";


    public Transcript() {
        this.transcriptInfo = new ArrayList<String>();
        this.score=new ArrayList<Integer>();
        String tableTitle = "成绩单";
        String title = "姓名|数学|语文|英语|编程|平均分|总分";
        this.transcriptInfo.add(tableTitle);
        this.transcriptInfo.add(title);
        this.transcriptInfo.add(splitLine);
    }

    public void addSelectStudent(StudentSql studentsql,String[] ids) {

        for (String s : ids) {
            int flag=-1;
            List<String> tmp=studentsql.getIdList();
            for (int i=0;i<tmp.size();i++) {
                int idInteger = Integer.valueOf(tmp.get(i));
                if (idInteger==Integer.valueOf(s)) {
                    flag=i;
                    break;
                }
            }
            if(flag!=-1){
                String student=studentsql.getStudentInfo().get(flag);
                String[] parts=student.split("\\|");
                StringBuffer sb = new StringBuffer();
                sb.append(parts[1]).append("|").append(parts[2]).append("|").append(parts[3]).append("|").append(parts[4]).append("|").append(parts[5]).append("|").append(parts[6]).append("|").append(parts[7]);
                this.transcriptInfo.add(sb.toString());
                this.sumScore+=Integer.valueOf(parts[7]);
                this.number++;
                this.score.add(Integer.valueOf(parts[7]));
            }
        }
    }


    public List<String> getTranscriptInfo() {
        return  this.transcriptInfo;
    }
    public void addAverageScore() {
        this.transcriptInfo.add(splitLine);
        sumaverage=this.sumScore/(double)this.number;
        String tmp = "全班总分平均数：" + String.valueOf(sumaverage);
        this.transcriptInfo.add(tmp);
    }

    public void addMedianScore() {
        double result=0.0;
        Collections.sort(score);
        int middle=score.size()/2;
        if(score.size()%2==0){
            result = (score.get(middle) + score.get(middle - 1))/(double)2;
        }else{
            result=(double)score.get(middle);
        }
        String tmp = "全班总分中位数：" + String.valueOf(result);
        this.transcriptInfo.add(tmp);
    }


//    成绩单
//    姓名|数学|语文|英语|编程|平均分|总分
//========================
//    张三|75|95|80|80|82.5|330
//    李四|85|80|70|90|81.25|325
//            ========================
//    全班总分平均数：xxx
//    全班总分中位数：xxx
//```
}
