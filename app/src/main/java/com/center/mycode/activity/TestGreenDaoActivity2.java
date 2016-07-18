package com.center.mycode.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.center.mycode.R;
import com.center.mycode.bean.User;
import com.center.mycode.beanhelper.DbUtil;
import com.center.mycode.beanhelper.UserService;
import com.center.mycode.view.SimpleDividerItemDecoration;
import com.center.mycode.view.swipelayout.SwipeRevealLayout;
import com.center.mycode.view.swipelayout.ViewBinderHelper;

import java.util.ArrayList;
import java.util.List;

public class TestGreenDaoActivity2 extends AppCompatActivity
{
    private EditText nameEdit;
    
    private EditText ageEdit;
    
    private EditText sexEdit;
    
    private EditText coreEdit;
    
    private EditText numEdit;
    
    private RecyclerView recyclerView;
    
    private List<User> mList;
    
    private ListAdapter adapter;
    
    private User selectUser;
    
    private UserService userHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_green_dao2);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
//        recyclerView.setHasFixedSize(true);
        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);
        userHelper = DbUtil.getUserService();
        mList = new ArrayList<>();
        getAllData();
        
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        // Only if you need to restore open/close state when
        // the orientation is changed
        if (adapter != null)
        {
            adapter.saveStates(outState);
        }
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        // Only if you need to restore open/close state when
        // the orientation is changed
        if (adapter != null)
        {
            adapter.restoreStates(savedInstanceState);
        }
    }
    
    public void onMyButtonClick(View v)
    {
        switch (v.getId())
        {
            case R.id.buttonAdd:
                getInputData(2);
                break;
            case R.id.buttonQuery:
                queryData();
                break;
            case R.id.buttonUpdate:
                getInputData(1);
                break;
            case R.id.deleteAll:
                deleteAllData();
                break;
            case R.id.queryAll:
                getAllData();
                break;
            case R.id.queryName:
                String userName = nameEdit.getText().toString();
                if (TextUtils.isEmpty(userName))
                {
                    return;
                }
                else
                {
                    mList.clear();
                    mList = userHelper.queryByName(userName);
                    adapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }
    
    private void getAllData()
    {
        List<User> list = userHelper.queryAll();
        if (list != null && list.size() > 0)
        {
            mList = list;
        }
        adapter.notifyDataSetChanged();
    }
    
    private void getInputData(int type)
    {
        if (type == 1 && selectUser == null)
        {
            Toast.makeText(this, "请先选中一条进行修改", Toast.LENGTH_SHORT).show();
            return;
        }
        String userId = numEdit.getText().toString();
        if (TextUtils.isEmpty(userId))
        {
            Toast.makeText(this, "请输入学号", Toast.LENGTH_SHORT).show();
            return;
        }
        String userName = nameEdit.getText().toString();
        if (TextUtils.isEmpty(userName))
        {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        String userAge = ageEdit.getText().toString();
        if (TextUtils.isEmpty(userAge))
        {
            Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
            return;
        }
        String userSex = sexEdit.getText().toString();
        if (TextUtils.isEmpty(userSex))
        {
            Toast.makeText(this, "请输入性别", Toast.LENGTH_SHORT).show();
            return;
        }
        String core = coreEdit.getText().toString();
        if (TextUtils.isEmpty(core))
        {
            Toast.makeText(this, "请输入分数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (type == 1)
        {
            if (selectUser == null)
            {
                Toast.makeText(this, "请先选中一条进行修改", Toast.LENGTH_SHORT).show();
                return;
            }
            userHelper.update(addData(selectUser, userId, userName, userAge, userSex, core));
            selectUser = null;
        }
        else
        {
            User user = new User();
            userHelper.saveOrUpdate(addData(user, userId, userName, userAge, userSex, core));
        }
        getAllData();
    }
    
    private User addData(User user, String userId, String userName, String userAge, String userSex, String core)
    {
        user.setUserId(Long.parseLong(userId));
        user.setName(userName);
        user.setAge(Integer.parseInt(userAge));
        user.setSex(userSex);
        user.setCore(core);
        return user;
    }
    
    private void queryData()
    {
        String sqlStr = "";
        String userCore = coreEdit.getText().toString();
        if (TextUtils.isEmpty(userCore))
        {
            Toast.makeText(this, "请输入查询的分数", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mList.clear();
            sqlStr = "CORE=?";
            mList = userHelper.query("where " + sqlStr, userCore);
            adapter.notifyDataSetChanged();
        }
    }
    
    private String getSqlStr(StringBuffer buffer, String key, String str)
    {
        if (!TextUtils.isEmpty(str))
        {
            buffer.append("and " + key + "=? ");
        }
        else
        {
            buffer.append("");
        }
        return buffer.toString();
    }
    
    private void deleteAllData()
    {
        userHelper.deleteAll();
        mList.clear();
        getAllData();
    }
    
    private class ListAdapter extends RecyclerView.Adapter<MyViewHolder>
    {
        private final ViewBinderHelper binderHelper = new ViewBinderHelper();
        
        /**
         * Only if you need to restore open/close state when the orientation is changed.
         * Call this method in {@link android.app.Activity#onSaveInstanceState(Bundle)}
         */
        public void saveStates(Bundle outState)
        {
            binderHelper.saveStates(outState);
        }
        
        /**
         * Only if you need to restore open/close state when the orientation is changed.
         * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
         */
        public void restoreStates(Bundle inState)
        {
            binderHelper.restoreStates(inState);
        }
        
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dao_list, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view, true);
            return viewHolder;
        }
        
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            MyViewHolder myViewHolder = (MyViewHolder)holder;
            if (mList != null && 0 <= position && position < mList.size())
            {
                User userInfo = mList.get(position);
                if (userInfo != null)
                {
                    binderHelper.bind(myViewHolder.myRevealLayout, userInfo.getId() + "");
                    String str1 =
                        getString(R.string.item_str, userInfo.getUserId(), userInfo.getName(), userInfo.getAge(),
                            userInfo.getSex(), userInfo.getCore());
                    myViewHolder.infoTextView.setText(str1);
                    if (position == 1)
                    {
                        myViewHolder.myRevealLayout.setLockDrag(true);
                    }
                }
            }
        }
        
        @Override
        public int getItemCount()
        {
            return mList == null ? 0 : mList.size();
        }
    }
    
    private class MyViewHolder extends RecyclerView.ViewHolder
    {
        
        private TextView infoTextView;
        
        private TextView btnDelete;
        
        private TextView btnChange;
        
        private FrameLayout item_fl;
        
        private SwipeRevealLayout myRevealLayout;
        
        public MyViewHolder(View itemView, boolean isItem)
        {
            super(itemView);
            
            infoTextView = (TextView)itemView.findViewById(R.id.info_textview);
            btnDelete = (TextView)itemView.findViewById(R.id.btn_delete);
            btnChange = (TextView)itemView.findViewById(R.id.btn_change);
            item_fl = (FrameLayout)itemView.findViewById(R.id.item_fl);
            myRevealLayout = (SwipeRevealLayout)itemView.findViewById(R.id.myRevealLayout);
            item_fl.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (myRevealLayout.getState() == SwipeRevealLayout.STATE_OPEN
                        || myRevealLayout.getState() == SwipeRevealLayout.STATE_OPENING)
                    {
                        myRevealLayout.close(true);
                    }
                    User userInfo = mList.get(getLayoutPosition());
                    numEdit.setText(userInfo.getUserId() + "");
                    nameEdit.setText(userInfo.getName());
                    ageEdit.setText(userInfo.getAge() + "");
                    sexEdit.setText(userInfo.getSex());
                    coreEdit.setText(userInfo.getCore());
                    selectUser = userInfo;
                }
            });
            item_fl.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    if (myRevealLayout.getState() == SwipeRevealLayout.STATE_OPEN
                        || myRevealLayout.getState() == SwipeRevealLayout.STATE_OPENING)
                    {
                        myRevealLayout.close(true);
                    }
                    Snackbar.make(v, "确定删除该信息么？", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            User userInfo = mList.get(getLayoutPosition());
                            userHelper.delete(userInfo);
                            getAllData();
                        }
                    }).show();
                    return false;
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    User userInfo = mList.get(getLayoutPosition());
                    mList.remove(userInfo);
                    adapter.notifyItemRemoved(getLayoutPosition());
                    userHelper.delete(userInfo);
                    adapter.notifyDataSetChanged();
//                    getAllData();
                }
            });
            btnChange.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    User userInfo = mList.get(getLayoutPosition());
                    numEdit.setText(userInfo.getUserId() + "");
                    nameEdit.setText(userInfo.getName());
                    ageEdit.setText(userInfo.getAge() + "");
                    sexEdit.setText(userInfo.getSex());
                    coreEdit.setText(userInfo.getCore());
                    selectUser = userInfo;
                }
            });
        }
    }
}
