package ball.mac.no.rmuttnews;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import ball.mac.no.rmuttnews.utility.GetAllData;
import ball.mac.no.rmuttnews.utility.MyAlert;
import ball.mac.no.rmuttnews.utility.MyConstant;
import ball.mac.no.rmuttnews.utility.PostNewUserToServer;

public class RegisterActivity extends AppCompatActivity {

    private String emailString, passwordString, firstString, lastString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


//        Register Control

        registerControl();

    }//Main Method

    private void registerControl() {
        Button button = (Button) findViewById(R.id.btnRegister);
        final EditText email = (EditText) findViewById(R.id.etEmail);
        final EditText password = (EditText) findViewById(R.id.etPassword);
        final EditText firstname = (EditText) findViewById(R.id.etFirstname);
        final EditText lastname = (EditText) findViewById(R.id.etLastname);

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
                    Toast.makeText(RegisterActivity.this,"Have Space",Toast.LENGTH_SHORT).show();
                }
                else{
//                    NO Space
                    checkEmail();

                    email.setTextColor(Color.RED);
                    email.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            email.setText("");
                            email.setTextColor(Color.BLACK);
                        }
                    });

                }

            }//onClick
        });
    }

    private void checkEmail() {
        //checkEmailStart
        try {

            MyConstant myConstant = new MyConstant();
            MyAlert myAlert = new MyAlert(RegisterActivity.this);
            GetAllData getAllData = new GetAllData(RegisterActivity.this);
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
            PostNewUserToServer postNewUserToServer = new PostNewUserToServer(RegisterActivity.this);
            postNewUserToServer.execute(emailString,passwordString,firstString,lastString,myConstant.getUrlPostUser());

            String result = postNewUserToServer.get();
            Log.d("23DecV1", "Result: "+ result);
            if (Boolean.parseBoolean(result)) {
                Toast.makeText(RegisterActivity.this,"Register Complete",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(RegisterActivity.this,"Can not Upload New User ",Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}//Main Class
