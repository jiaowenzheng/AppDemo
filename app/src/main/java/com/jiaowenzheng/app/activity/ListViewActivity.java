package com.jiaowenzheng.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jiaowenzheng.app.ListItem;
import com.jiaowenzheng.app.R;
import com.jiaowenzheng.app.adapter.ListItemAdapter;
import com.jiaowenzheng.app.animation.ViewExpandAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  jiaowenzheng
 */
public class ListViewActivity extends AppCompatActivity {

    private ListView mListView;

    private List<ListItem> mList;

    private ListItemAdapter mListAdapter;

    private ListItemAdapter.ViewHolder mLastViewTag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        mListView = (ListView) findViewById(R.id.list_view);

        init();
    }


    public void init(){

        mList = new ArrayList<ListItem>();

        List<String> tempArray1 = new ArrayList<String>();
        tempArray1.add("第一条");
        tempArray1.add("第二条");

        List<String> tempArray2 = new ArrayList<String>();
        tempArray2.add("第一条");
        tempArray2.add("第二条");
        tempArray2.add("第三条");

        List<String> tempArray3 = new ArrayList<String>();
        tempArray3.add("第一条");
        tempArray3.add("第二条");
        tempArray3.add("第三条");
        tempArray3.add("第四条");

        for (int i = 0;i < 10;i++){
            ListItem item = new ListItem();
            item.setDrawableId(R.drawable.banner1);
            if(i == 0){
                item.setChildrenList(tempArray1);
            }else if( i == 1){
                item.setChildrenList(tempArray2);
            }else if(i == 3){
                item.setChildrenList(tempArray3);
            }else{
                item.setChildrenList(tempArray1);
            }

            mList.add(item);
        }

        mListAdapter = new ListItemAdapter(this,mList);
        mListView.setAdapter(mListAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                mListAdapter.setPosition(position);

                //将上一个展开的view 收缩
                if (mLastViewTag != null) {
                    View previousView = parent.findViewWithTag(mLastViewTag);
                    if (previousView != null) {
                        View childrenView = previousView.findViewById(R.id.child_layout);
                        if (childrenView != null
                                && (childrenView.getVisibility() != View.GONE)) {
                            childrenView.startAnimation(new ViewExpandAnimation(
                                    childrenView));
                        }
                    }
                }

                //记录当前item的Tag
                mLastViewTag = (ListItemAdapter.ViewHolder) view.getTag();

                //展开当前点击的item
                View childrenLayout = view.findViewById(R.id.child_layout);
                ViewExpandAnimation expandAnimation = new ViewExpandAnimation(childrenLayout);

                expandAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //item展开结束后，将当前item平滑滚动到顶部
                        mListView.smoothScrollToPositionFromTop(position, 1, 300);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                childrenLayout.startAnimation(expandAnimation);
            }
        });
    }

}
