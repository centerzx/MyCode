package com.center.mycode.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.center.mycode.R;

public class TestCollapsingToolbarActivity extends AppCompatActivity
{
    
    private ImageView titleImage;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_collapsing_toolbar);
        titleImage = (ImageView)findViewById(R.id.titleImage);
        initToolbar();
    }
    
    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_toolbar);
//        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setNavigationIcon(R.drawable.btn_navi_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingLayout);
        collapsingToolbarLayout.setTitle("CollapsingToolbar");
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorPrimary));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        titleImage.setImageResource(R.drawable.title_image);
        ViewCompat.setTransitionName(titleImage, "headImage");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    public void goConnects(View v)
    {
//        Intent intent = new Intent();
//        intent.setClassName("com.android.contacts", "com.android.contacts.activities.PeopleActivity");
//        startActivity(intent);
//    
//        Uri uri = Uri.parse("content://contacts/people"); 
//        Intent intent = new Intent(Intent.ACTION_PICK, uri);
//        startActivityForResult(intent, 0);

//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_PICK);
//        intent.setData(ContactsContract.Contacts.CONTENT_URI);
//        startActivityForResult(intent, 0);
        
//        startActivity(new Intent().setClassName(getApplicationContext(),
//            "com.mediatek.contacts.list.ContactListMultiChoiceActivity")
//            .setAction(ContactsIntent.LIST.ACTION_SHARE_MULTICONTACTS));
    }
}
