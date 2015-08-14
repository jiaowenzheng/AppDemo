package com.jiaowenzheng.app.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiaowenzheng.app.ListItem;
import com.jiaowenzheng.app.R;

import java.util.List;

/**
 * Created by wenzheng on 2015/8/13.
 */
public class ListItemAdapter extends BaseAdapter {

    private List<ListItem> mList;
    private Context mContext;

    private int mPosition = -1;
    private DisplayMetrics mDisplayMetrics;
    private LayoutInflater mInflater;

    public ListItemAdapter(Context context, List<ListItem> mList) {
        this.mList = mList;
        this.mContext = context;
        this.mDisplayMetrics = mContext.getResources().getDisplayMetrics();
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.group_item,null);
            holder.childrenLayout = (LinearLayout) convertView.findViewById(R.id.child_layout);
            holder.groupImg = (ImageView) convertView.findViewById(R.id.children_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListItem item = mList.get(position);
        holder.groupImg.setImageResource(item.getDrawableId());

        if (holder.childrenLayout.getChildCount() > 0) {
            holder.childrenLayout.removeAllViews();
        }

        //动态加载子view布局
        int size = item.getChildrenList().size();
        for (int i = 0; i < size; i++) {
            View childrenView = mInflater.inflate(R.layout.child_item, null);
            TextView name = (TextView) childrenView.findViewById(R.id.child_text);
            name.setText(item.getChildrenList().get(i));
            holder.childrenLayout.addView(childrenView);
        }

        //这里很重要，计算当前要展开view的高度
        LinearLayout childrenLayout = (LinearLayout) convertView.findViewById(R.id.child_layout);
        int widthSpec = View.MeasureSpec.makeMeasureSpec((int) (mDisplayMetrics.widthPixels -
                10 * mDisplayMetrics.density), View.MeasureSpec.EXACTLY);
        childrenLayout.measure(widthSpec, 0);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) childrenLayout.getLayoutParams();

        if(mPosition == position) {
            params.bottomMargin = 0;
            childrenLayout.setVisibility(View.VISIBLE);
        } else {
            params.bottomMargin = -childrenLayout.getMeasuredHeight();
            childrenLayout.setVisibility(View.GONE);
        }

        return convertView;
    }


    public void setPosition(int position) {
        mPosition = position;
    }

    public  static class ViewHolder {
        ImageView groupImg;
        LinearLayout childrenLayout;
    }
}
