package edu.hcmus.hw05;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements MainCallbacks {

    private FragmentTransaction ft;
    private FragmentRed redFragment;
    private FragmentBlue blueFragment;
    private int currentPos;
    private String[] user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a new BLUE fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        blueFragment = FragmentBlue.newInstance("first-blue");
        ft.replace(R.id.main_holder_blue, blueFragment);
        ft.commit();
        // create a new RED fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance("first-red");
        ft.replace(R.id.main_holder_red, redFragment);
        ft.commit();


    }
    // MainCallback implementation (receiving messages coming from Fragments)
    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {

        if (sender.equals("RED-FRAG"))
        {
            try{
                switch (strValue)
                {
                    case "0":
                        currentPos = 0;
                        blueFragment.onMsgFromMainToFragment(strValue);
                        break;
                    case "1":
                        if (currentPos > 0)
                            currentPos -= 1;
                        blueFragment.onMsgFromMainToFragment(String.valueOf(currentPos));
                        break;
                    case "2":
                        if (currentPos < blueFragment.getLengthUserArray()-1)
                            currentPos += 1;
                        blueFragment.onMsgFromMainToFragment(String.valueOf(currentPos));
                        break;
                    case "3":
                        currentPos = blueFragment.getLengthUserArray()-1;
                        blueFragment.onMsgFromMainToFragment(String.valueOf(currentPos));
                        break;
                }
            } catch (Exception e){
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }

        }
        if (sender.equals("BLUE-FRAG")) {
            try {
                user = strValue.split(";");
                currentPos = Integer.parseInt(user[0]);
                redFragment.onMsgFromMainToFragment(user[1]);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}