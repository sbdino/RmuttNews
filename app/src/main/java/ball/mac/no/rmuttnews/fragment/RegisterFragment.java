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
import android.widget.Toast;

import ball.mac.no.rmuttnews.R;
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

//        Register Controll

        registerControll();

    }//Main Method

    private void registerControll() {
        Button button = getView().findViewById(R.id.btnRegister);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Initial EditText
                EditText email = getView().findViewById(R.id.etEmail);
                EditText password = getView().findViewById(R.id.etPassword);
                EditText firstname = getView().findViewById(R.id.etFirstname);
                EditText lastname = getView().findViewById(R.id.etLastname);

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

                    uploadToServer();
                }

            }//onClick
        });
    }

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
                Toast.makeText(getActivity(),"Cann not Upload New User ",Toast.LENGTH_SHORT).show();
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
