package ball.mac.no.rmuttnews.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by SB Dino on 23-Dec-17.
 */

public class PostNewUserToServer extends AsyncTask<String,Void,String>{
    private Context context;

    public PostNewUserToServer(Context context)  {

        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder().add("isAdd", "true")
                    .add("email", strings[0])
                    .add("password", strings[1])
                    .add("firstname", strings[2])
                    .add("lastname", strings[3])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[4]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return  response.body().string();


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}//Main class
