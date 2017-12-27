package ball.mac.no.rmuttnews;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SB Dino on 27-Dec-17.
 */


public class SharedPrefs {
    final static String FileName = "NLBS";

    public static String readSharedSetting(Context context,String  settingName,String defaultValue){
        SharedPreferences sharedPrefs = context.getSharedPreferences(FileName,Context.MODE_PRIVATE);
        return sharedPrefs.getString(settingName,defaultValue);
    }

    public static void saveSharedSetting(Context context,String settingName,String settingValue){
        SharedPreferences sharedPrefs = context.getSharedPreferences(FileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(settingName,settingValue);
        editor.apply();
    }
}
