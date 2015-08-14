package com.jiaowenzheng.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.jiaowenzheng.app.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewActivity extends AppCompatActivity {

    private ExpandableListView mExpandableListView;

    private List<Integer> mGroupsList;
    private List<List<String>> mChildrenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        mExpandableListView = (ExpandableListView) findViewById(R.id.expand_list_view);




    }

    public void init(){

        mGroupsList = new ArrayList<Integer>();
        mChildrenList = new ArrayList<List<String>>();

        for (int i = 0;i < 10; i++){
            mGroupsList.add(R.drawable.banner1);
        }

        List<String> tempArray = new ArrayList<String>();
        tempArray.add("第一条");
        tempArray.add("第二条");
        tempArray.add("第三条");
        tempArray.add("第四条");

        for (int i = 0;i < 10;i++){
            mChildrenList.add(tempArray);
        }



    }

}
