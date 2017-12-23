package ball.mac.no.rmuttnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import ball.mac.no.rmuttnews.R;
import ball.mac.no.rmuttnews.utility.GetAllData;
import ball.mac.no.rmuttnews.utility.MyAlert;
import ball.mac.no.rmuttnews.utility.MyConstant;

/**
 * Created by SB Dino on 23-Dec-17.
 */

public class MainFragment extends Fragment {

    private String emailString, passwordString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

//        Login Controller
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = getView().findViewById(R.id.etEmail);
                EditText password = getView().findViewById(R.id.etPassword);

                emailString = email.getText().toString().trim();
                passwordString = password.getText().toString().trim();

                if (emailString.isEmpty()||passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.normalDialog("Have Space","Please Fill All B;ank");

                }
                else {
//                    NO Space
                    checkEmailAndPassword();


                }

            }//onClick
        });
    }//Main Method

    private void checkEmailAndPassword() {

        try {

            MyConstant myConstant = new MyConstant();
            MyAlert myAlert = new MyAlert(getActivity());
            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlGetUser());

            String jsonString = getAllData.get();
            Log.d("23DecV1", "JSON ==>: " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            String[] columnStrings = myConstant.getColumnUserString();
            String[] loginStrings = new String[columnStrings.length];
            Boolean b = true ;//Email Fail

            for (int i = 0 ; i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (emailString.equals(jsonObject.getString(columnStrings[1]))) {
                    b = false;
                    for (int i1=0;i1<columnStrings.length;i1++) {

                        loginStrings[i1] = jsonObject.getString(columnStrings[i1]);

                        Log.d("23DecV1", "Login["+i1+"] ==> "+ loginStrings[i1]);

                    }
                }
            }//for

            if (b) {
//                email fail
                myAlert.normalDialog("email Fail","Please Check Yoru Email");

            } else if (passwordString.equals(loginStrings[2])) {

                Toast.makeText(getActivity(),"welcome " + loginStrings[3]+" "+loginStrings[4],Toast.LENGTH_SHORT).show();


            } else {

                myAlert.normalDialog("Password Fail","Please Try Again Email or Password");

            }




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.tvRegister);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null).commit();
            }//onClick
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}//Main Class
