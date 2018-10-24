package com.example.quest.nbd_launcher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView mAppPackage;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppPackage = (TextView) findViewById(R.id.appPackage);
        mImageView = (ImageView) findViewById(R.id.imageView);

        Button launcherButton=(Button)findViewById(R.id.button1);
        //definingbutton click event
        launcherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(), AppList.class);//creating intet 																		//to start applist activity
                startActivityForResult(i, 1); //start applist activity, which will show allapps
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent launchIntent = getPackageManager().getLaunchIntentForPackage((String) mAppPackage.getText());
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String receivedAppPackageName = data.getStringExtra("appPackageName");
                mAppPackage.setText(receivedAppPackageName);

                //convert this packagename to icon bt the below code

                try
                {
                    Drawable icon = getPackageManager().getApplicationIcon(receivedAppPackageName);
                    mImageView.setImageDrawable(icon);
                }
                catch (PackageManager.NameNotFoundException e)
                {
                    e.printStackTrace();
                }

//                *************************** The end ****************
//

            }
        }
    }
}
