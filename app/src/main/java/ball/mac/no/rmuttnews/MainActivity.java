package ball.mac.no.rmuttnews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    SharedPreferences shared = getSharedPreferences("Rmuttnews", Context.MODE_PRIVATE);
                    String val = shared.getString("email","");
                    if (val.length() == 0) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Intent intent = new Intent(MainActivity.this,ServiceActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        };
        thread.start();

    }//Main Method
}// Main Class
