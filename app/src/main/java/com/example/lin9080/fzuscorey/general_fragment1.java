package com.example.lin9080.fzuscorey;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;

import org.litepal.LitePal;

import java.util.ArrayList;

public class general_fragment1 extends Fragment {
    private ArrayList<Student> students=new ArrayList<>();
    private ArrayList<ChartBean> chartBeans=new ArrayList<>();
    private ArrayList<ArrayList<ChartBean>> chartBeansList=new ArrayList<>();
    private ArrayList<Student> mstudents=new ArrayList<>();
    private View view;
    private LineChart chart;
    private int term=1;
    private int sub=1;
    private int[] colors=new int[2];
    private int[] circleColors=new int[2];
    private ArrayList<String> labels=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_general1, container, false);
        students.addAll((ArrayList<Student>) LitePal.findAll(Student.class));
        MonActivity monActivity = (MonActivity) getActivity();
        BarChart barChart=(BarChart)view.findViewById(R.id.genera1BarChart);
        term=monActivity.getTerm();
        initTerm(term,sub);
        BarChartUtil.initBarChart(barChart,chartBeans);
        BarChartUtil.showBarChart(barChart,chartBeans,"成绩/占比",Color.BLUE);
        return view;
    }
    private void initTerm(int term,int sub){
        for(int i=0;i<students.size();i++){
            if(students.get(i).getTerm()==term){
                mstudents.add(students.get(i));
            }
        }
        getDataNeed(sub);
    }
    private void getDataNeed(int sub){
        chartBeans.clear();
        int result=0;
        int high=0;
        int low=100;
        int excellent=0;
        int fail=0;
        for(int m=0;m<mstudents.size();m++){
            result+=StudentUtil.getSubjectScore(mstudents.get(m),sub);
            if(StudentUtil.getSubjectScore(mstudents.get(m),sub)>high) {
                high =StudentUtil.getSubjectScore(mstudents.get(m),sub);
            }
            if(StudentUtil.getSubjectScore(mstudents.get(m),sub)<low){
                low=StudentUtil.getSubjectScore(mstudents.get(m),sub);
            }
            if(StudentUtil.getSubjectScore(mstudents.get(m),sub)>85){
                excellent++;
            }
            if(StudentUtil.getSubjectScore(mstudents.get(m),sub)<60){
                fail++;
            }
        }
        chartBeans.add(new ChartBean((int)((double)excellent/(double)(mstudents.size())*100),"优秀率"));
        chartBeans.add(new ChartBean((int)((double)fail/(double)(mstudents.size())*100),"不及格率"));
        chartBeans.add(new ChartBean(result/(mstudents.size()),"平均分"));
        chartBeans.add(new ChartBean(high,"最高分"));
        chartBeans.add(new ChartBean(low,"最低分"));
    }
}
