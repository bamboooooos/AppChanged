package com.example.lin9080.fzuscorey;

public class StudentUtil {
    public static int getAllScoreOne(Student student){
        return student.getScore_1()+student.getScore_2()+student.getScore_3()+student.getScore_4()+student.getScore_5()+student.getScore_6();
    }
    public static int getSubjectScore(Student student,int i){
        int result=0;
        switch (i){
            case 1:
                result=student.getScore_1();
                break;
            case 2:
                result=student.getScore_2();
                break;
            case 3:
                result=student.getScore_3();
                break;
            case 4:
                result=student.getScore_4();
                break;
            case 5:
                result=student.getScore_5();
                break;
            case 6:
                result=student.getScore_6();
                break;
            default:
        }
        return result;
    }
}