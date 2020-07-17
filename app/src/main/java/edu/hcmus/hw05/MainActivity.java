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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a new BLUE fragment - show it
        ft = getSupportFragmentManager().beginTransaction(); blueFragment = FragmentBlue.newInstance("first-blue");
        ft.replace(R.id.main_holder_blue, blueFragment); ft.commit();
        // create a new RED fragment - show it
        ft = getSupportFragmentManager().beginTransaction(); redFragment = FragmentRed.newInstance("first-red");
        ft.replace(R.id.main_holder_red, redFragment); ft.commit();


    }
    // MainCallback implementation (receiving messages coming from Fragments)
    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
// show message arriving to MainActivity
        Toast.makeText(getApplication(), " MAIN GOT>> " + sender + "\n" + strValue, Toast.LENGTH_LONG).show();
        if (sender.equals("RED-FRAG")) {
            switch (strValue) {
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
        }
        if (sender.equals("BLUE-FRAG")) {
            try { // forward blue-data to redFragment using its callback method
                redFragment.onMsgFromMainToFragment(strValue);
            }
            catch (Exception e) { Log.e("ERROR", "onStrFromFragToMain " + e.getMessage()); }
        }
    }
}