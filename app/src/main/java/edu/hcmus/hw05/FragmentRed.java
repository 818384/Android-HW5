package edu.hcmus.hw05;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Date;

public class FragmentRed extends Fragment implements FragmentCallbacks {

    MainActivity main;
    TextView txtInfoUser, txtClass, txtUsername, txtPointAVG;
    Button btnFirst, btnPrevious, btnNext, btnLast;

    public static FragmentRed newInstance(String strArg1) {
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Activities containing this fragment must implement interface: MainCallbacks
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// inflate res/layout_red.xml which includes a textview and a button

        LinearLayout layout_red = (LinearLayout) inflater.inflate(R.layout.layout_red, null);
        txtInfoUser = (TextView) layout_red.findViewById(R.id.txtInfoUser);
        txtClass = (TextView) layout_red.findViewById(R.id.txtClass);
        txtUsername = (TextView) layout_red.findViewById(R.id.txtUsername);
        txtPointAVG = (TextView) layout_red.findViewById(R.id.txtPointAVG);
        btnFirst = (Button) layout_red.findViewById(R.id.btnFirst);
        btnPrevious = (Button) layout_red.findViewById(R.id.btnPrevious);
        btnNext = (Button) layout_red.findViewById(R.id.btnNext);
        btnLast = (Button) layout_red.findViewById(R.id.btnLast);


// show string argument supplied by constructor (if any!)
        try {
            Bundle arguments = getArguments();
            txtInfoUser.setText(arguments.getString("arg1", ""));
        } catch (Exception e) {
            Log.e("RED BUNDLE ERROR – ", "" + e.getMessage());
        }
// clicking the button changes the time displayed and sends a copy to MainActivity
        /*btnRedClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String redMessage = "Red clock:\n" + new Date().toString();
                txtRed.setText(redMessage);
                main.onMsgFromFragToMain("RED-FRAG", redMessage);
            }
        });*/
        btnFirst.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", String.valueOf(DirecButton.First));
            }
        });
        btnLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", String.valueOf(DirecButton.Last));
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", String.valueOf(DirecButton.Next));
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                main.onMsgFromFragToMain("RED-FRAG", String.valueOf(DirecButton.Previous));
            }
        });

        return layout_red;
    }

    @Override
    public void onMsgFromMainToFragment(String user) {
// receiving a message from MainActivity (it may happen at any point in time)
        String[] usr = user.split(",");
        txtInfoUser.setText( usr[0]);
        txtUsername.setText("Họ tên: " + usr[1]);
        txtClass.setText("Lớp: " + usr[2]);
        txtPointAVG.setText("Điểm trung bình: " + usr[3]);
    }
}
