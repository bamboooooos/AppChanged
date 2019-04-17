package com.example.lin9080.fzuscorey;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.litepal.LitePal;

import java.util.ArrayList;

public class analysis_fragment1 extends Fragment {
    private int term=1;
    private int sub=1;
    private ArrayList<Student> students=new ArrayList<>();
    private ArrayList<Student> sortedStudents=new ArrayList<>();
    final AllStuAdapter adapter=new AllStuAdapter(sortedStudents);
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analysis1,container,false);
        MonActivity monActivity=(MonActivity)getActivity();
        students.clear();
        students.addAll((ArrayList<Student>) LitePal.findAll(Student.class));
        term=monActivity.getTerm();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.analysis1RecyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getDadaNeed(term,sub);
        return view;
    }

    private void getDadaNeed(int term,int sub){
        adapter.setSub(sub);
        SortUtil sortUtil=new SortUtil(students,term);
        sortUtil.SortStudent(sub);
        sortedStudents.clear();
        sortedStudents.addAll(sortUtil.getSortedStudents());
        adapter.notifyDataSetChanged();
    }
}
