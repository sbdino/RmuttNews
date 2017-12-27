package ball.mac.no.rmuttnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import ball.mac.no.rmuttnews.utility.GetAllData;
import ball.mac.no.rmuttnews.utility.MyAlert;
import ball.mac.no.rmuttnews.utility.MyConstant;

public class LoginActivity extends AppCompatActivity {

    private String emailString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        Register Controller
        registerController();

//        Login Controller
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.etEmail);
                EditText password = (EditText) findViewById(R.id.etPassword);

                emailString = email.getText().toString().trim();
                passwordString = password.getText().toString().trim();

                if (emailString.isEmpty()||passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(LoginActivity.this);
                    myAlert.normalDialog("Have Space","Please Fill All Blank");

                }
                else {
//                    NO Space
                    checkEmailAndPassword();
                }
            }//onClick
        });


    }//Main Method


    private void checkEmailAndPassword() {
//checkEmailStart
        try {

            MyConstant myConstant = new MyConstant();
            MyAlert myAlert = new MyAlert(LoginActivity.this);
            GetAllData getAllData = new GetAllData(LoginActivity.this);
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
                    for (int a = 0; a<columnStrings.length; a++) {

                        loginStrings[a] = jsonObject.getString(columnStrings[a]);

                        Log.d("23DecV1", "Login["+a+"] ==> "+ loginStrings[a]);
                    }
                }
            }//for

            if (b) {
//                email fail
                myAlert.normalDialog("email Fail","Please Check Your Email");

            } else if (passwordString.equals(loginStrings[2])) {

                Toast.makeText(LoginActivity.this,"welcome " + loginStrings[3]+" "+loginStrings[4],Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, ServiceActivity.class);
                intent.putExtra("Login", loginStrings);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();

            } else {

                myAlert.normalDialog("Password Fail","Please Try Again Email or Password");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    checkEmailEnd
    private void registerController() {
        TextView register = (TextView) findViewById(R.id.tvRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(regisIntent);

            }//onClick
        });
    }
 }//Main Class

