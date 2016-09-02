package com.center.mycode.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.center.mycode.R;
import com.center.mycode.entity.UserInfoResponse;
import com.cmcc.andmu.iothttpsdk.http.BaseResponse;
import com.cmcc.andmu.iothttpsdk.http.HttpResponseHandler;
import com.cmcc.andmu.iothttpsdk.http.IotHttpClient;
import com.cmcc.andmu.iothttpsdk.http.ResultCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestAppCompatActivity extends BaseActivity
{
    private DrawerLayout mDrawerLayout;
    
    private ActionBarDrawerToggle mDrawerToggle;
    
    private static final String EMAIL_PATTERN =
        "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    
    private Matcher matcher;
    
    private TextInputLayout nameLayout;
    
    private TextInputLayout passLayout;
    
    private NavigationView drawer;
    
    private FloatingActionButton floatingBtn;
    
    private Button btnPermission;
    
    private int REQUEST_CODE_PICK_IMAGE = 0x101;
    
    private ImageView mImageView, mImageView2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t);
        mImageView = (ImageView)findViewById(R.id.image);
        mImageView2 = (ImageView)findViewById(R.id.image2);
        initToolbar();
        initInputLayout();
        initFloatBtn();
        initBtn();
    }
    
    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
// Title
        toolbar.setTitle("My Title");
// Sub Title
        toolbar.setSubtitle("Sub title");
        toolbar.setNavigationIcon(android.R.drawable.ic_dialog_alert);
        
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.action_settings:
                        Toast.makeText(TestAppCompatActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_share:
                        Toast.makeText(TestAppCompatActivity.this, "action_share", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mDrawerToggle =
            new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
            {
                @Override
                public void onDrawerOpened(View drawerView)
                {
                    super.onDrawerOpened(drawerView);
                    //打开
                }
                
                @Override
                public void onDrawerClosed(View drawerView)
                {
                    super.onDrawerClosed(drawerView);
                    //关闭
                }
            };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //和上面一样
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_dialog_alert);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setLogo(R.mipmap.ic_launcher);
//        actionBar.setTitle("title");
//        actionBar.setSubtitle("Sub title");
    }
    
    private void initInputLayout()
    {
        
        nameLayout = (TextInputLayout)findViewById(R.id.nameLayout);
        passLayout = (TextInputLayout)findViewById(R.id.passLayout);
        initEditView();
        drawer = (NavigationView)findViewById(R.id.drawer_view2);
        drawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {
                item.setChecked(true);
                switch (item.getItemId())
                {
                    case R.id.item1:
                        
                        break;
                    case R.id.item2:
                        
                        break;
                    case R.id.item3:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.item4:
                        break;
                    case R.id.item5:
                        
                        break;
                    case R.id.item6:
                        
                        break;
                    case R.id.item7:
                        ToastUtils toastUtil = new ToastUtils();
                        toastUtil.Short(TestAppCompatActivity.this, "自定义message字体、背景色")
                            .setToastColor(Color.WHITE, getResources().getColor(R.color.colorAccent))
                            .setGravity(Gravity.CENTER, 0, 0)
                            .show();
                        
                        break;
                }
                return true;
            }
        });
        
    }
    
    private void initFloatBtn()
    {
        floatingBtn = (FloatingActionButton)findViewById(R.id.floatingBtn);
        floatingBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                IotHttpClient.Login(TestAppCompatActivity.this, "13509481519", "123456789",
                    new HttpResponseHandler(UserInfoResponse.class)
                    {
                        @Override
                        public void onStart()
                        {
                            Log.e("onStart", "onStart");
                        }
                        
                        @Override
                        public void onFinish()
                        {
                            Log.e("onFinish", "onFinish");
                        }
                        
                        @Override
                        public void onSuccess(int code, String result, BaseResponse baseResponse)
                        {
                            Toast.makeText(TestAppCompatActivity.this, "" + code + baseResponse.desc,
                                Toast.LENGTH_SHORT).show();
                            if (code == ResultCode.SUCCESS && baseResponse.code == 0)
                            {
                                Snackbar.make(v, getString(R.string.format_string, "Snackbar"), Snackbar.LENGTH_LONG)
                                    .setAction("OK", new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v)
                                        {
                                            
                                        }
                                    })
                                    .show();
                            }
                        }
                        
                        @Override
                        public void onFailure()
                        {
                            
                        }
                    });
            }
        });
    }
    
    private void initBtn()
    {
        btnPermission = (Button)findViewById(R.id.btn_permission);
//        btnPermission.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                doPermission(v);
//            }
//        });
    }
    
    public void doPermission(View v)
    {
        Log.e("doPermission", "doPermission");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED)
            {
                Activity activty = this;
                ActivityCompat.requestPermissions(activty, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0x100);
                return;
            }
        }
        else
        {
            getImageFromAlbum();
        }
    }
    
    public void getImageFromAlbum()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型  
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == 0x100)
        {
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)
                && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //用户同意使用write
                
            }
            else
            {
                //用户不同意，自行处理即可
                finish();
            }
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.item1)
        {
            //打开抽屉侧滑菜单
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    public void initEditView()
    {
        nameLayout.getEditText().addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                
            }
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (!validateEmail(nameLayout.getEditText().getText().toString()))
                {
                    nameLayout.setError("请输入邮箱地址");
                }
                else
                {
                    nameLayout.setErrorEnabled(false);
                    nameLayout.setError("");
                }
            }
            
            @Override
            public void afterTextChanged(Editable s)
            {
                
            }
        });
        
        passLayout.getEditText().addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                
            }
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (!validatePass(passLayout.getEditText().getText().toString()))
                {
                    passLayout.setError("密码至少需6位");
                }
                else
                {
                    passLayout.setErrorEnabled(false);
                    passLayout.setError("");
                }
            }
            
            @Override
            public void afterTextChanged(Editable s)
            {
                
            }
        });
        
    }
    
    public void doOK(View v)
    {
        String name = nameLayout.getEditText().getText().toString();
        String pass = passLayout.getEditText().getText().toString();
        if (!validateEmail(name))
        {
            nameLayout.setError("请输入邮箱地址");
        }
        else
        {
            nameLayout.setErrorEnabled(false);
            nameLayout.setError("");
        }
        if (!validatePass(pass))
        {
            passLayout.setError("密码至少需6位");
        }
        else
        {
            passLayout.setErrorEnabled(false);
            passLayout.setError("");
        }
        
    }
    
    public void doTestCollapsing(View v)
    {
        Intent intent = new Intent(this, TestCollapsingToolbarActivity.class);
        ActivityCompat.startActivity(this, intent,
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, mImageView, "headImage").toBundle());
    }
    
    public void doFlex(View v)
    {
        //让新的Activity从一个小的范围扩大到全屏
        ActivityOptionsCompat options =
            ActivityOptionsCompat.makeScaleUpAnimation(mImageView, //The View that the new activity is animating from
                (int)mImageView.getWidth() / 2, (int)mImageView.getHeight() / 2, //拉伸开始的坐标
                0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
        Intent intent = new Intent(this, TestFlexBoxLayoutActivity.class);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
    
    public void doShowBig(View v)
    {
        //让新的Activity从一个小的范围扩大到全屏
        ActivityOptionsCompat options =
            ActivityOptionsCompat.makeScaleUpAnimation(mImageView2, //The View that the new activity is animating from
                (int)mImageView2.getWidth() / 2, (int)mImageView2.getHeight() / 2, //拉伸开始的坐标
                0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
        Intent intent = new Intent(this, TestPhotoViewActivity.class);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
    
    public void goTestGreenDao(View v)
    {
        Intent intent = new Intent(this, TestGreenDaoActivity.class);
        startActivity(intent);
    }
    
    public void goRefresh(View v)
    {
        Intent intent = new Intent(this, RefreshActivity.class);
        startActivity(intent);
    }
    
    public void doOpenApp(View v)
    {
        Intent intent = new Intent(this, WebviewActivity.class);
        startActivity(intent);
    }
    
    public void doNotify(View v)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        PendingIntent pendingIntent =
            PendingIntent.getActivity(this, 0, new Intent(this, TestGreenDaoActivity.class), 0);
//        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_recycler_list);//自定义布局使用此类
//        remoteViews.setOnClickPendingIntent(R.id.image, pendingIntent);
        Notification notification = builder.setAutoCancel(true)
//                .setContent(remoteViews)
            .setContentIntent(pendingIntent)//2.3及以下系统必须设置
            .setColor(getResources().getColor(R.color.green_600))
            .setSmallIcon(R.drawable.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
            .setContentTitle("这是标题")
            .setContentText("这是内容")
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
//                .addAction(R.drawable.talk_p, "拉下后的标题", pendingIntent)//在这条通知下面添加一个icon图标button，点击触发p事件，例如 phone的挂断  
            .build();
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, notification);
        
    }
    
    public boolean validateEmail(String email)
    {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public boolean validatePass(String pass)
    {
        return pass.length() >= 6;
    }
    
    private boolean hasPermission(String permission)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }
    
    private boolean canMakeSmores()
    {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }
    
}
