package ball.mac.no.rmuttnews.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import ball.mac.no.rmuttnews.R;
import ball.mac.no.rmuttnews.ServiceActivity;
import ball.mac.no.rmuttnews.utility.GetAllData;
import ball.mac.no.rmuttnews.utility.MyAlert;
import ball.mac.no.rmuttnews.utility.MyConstant;
import ball.mac.no.rmuttnews.utility.PostNewUserToServer;

/**
 * Created by SB Dino on 23-Dec-17.
 */

public class RegisterFragment extends Fragment {

    //    Explicit
    private String emailString, passwordString, firstString, lastString;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Control

        registerControl();

    }//Main Method

    private void registerControl() {
        Button button = getView().findViewById(R.id.btnRegister);
        final EditText email = getView().findViewById(R.id.etEmail);
        final EditText password = getView().findViewById(R.id.etPassword);
        final EditText firstname = getView().findViewById(R.id.etFirstname);
        final EditText lastname = getView().findViewById(R.id.etLastname);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

//                Initial EditText


//                Get Value From EditText
                emailString = email.getText().toString().trim();
                passwordString = password.getText().toString().trim();
                firstString = firstname.getText().toString().trim();
                lastString = lastname.getText().toString().trim();

//                Check Space
                if (emailString.isEmpty()||passwordString.isEmpty()||firstString.isEmpty()||lastString.isEmpty()) {
//                    HAVE SPACE
                    Toast.makeText(getActivity(),"Have Space",Toast.LENGTH_SHORT).show();
                }
                else{
//                    NO Space
                    checkEmail();

                    email.setTextColor(Color.RED);

                }

            }//onClick
        });
    }

    private void checkEmail() {
        //checkEmailStart
        try {

            MyConstant myConstant = new MyConstant();
            MyAlert myAlert = new MyAlert(getActivity());
            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlGetUser());

            String jsonString = getAllData.get();
            Log.d("23DecV1", "JSON ==>: " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);
            String[] columnStrings = myConstant.getColumnUserString();
            Boolean b = true ;//Email True

            for (int i = 0 ; i<jsonArray.length();i++) {
                //                Duplicate email
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (emailString.equals(jsonObject.getString(columnStrings[1]))) {
                    b = false;
                    myAlert.normalDialog("Duplicate Email","Please try again Email");
                }
            }//for

            if (b)
            {
                uploadToServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    checkEmailEnd

    private void uploadToServer() {
        try {

            MyConstant myConstant = new MyConstant();
            PostNewUserToServer postNewUserToServer = new PostNewUserToServer(getActivity());
            postNewUserToServer.execute(emailString,passwordString,firstString,lastString,myConstant.getUrlPostUser());

            String result = postNewUserToServer.get();
            Log.d("23DecV1", "Result: "+ result);
            if (Boolean.parseBoolean(result)) {
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                Toast.makeText(getActivity(),"Can not Upload New User ",Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}//Main Class
