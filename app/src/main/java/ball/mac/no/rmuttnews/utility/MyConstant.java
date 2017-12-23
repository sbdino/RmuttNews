package ball.mac.no.rmuttnews.utility;

/**
 * Created by SB Dino on 23-Dec-17.
 */

public class MyConstant {

    private String urlPostUser = "http://androidthai.in.th/rmutt/addDataMaster.php";
    private String urlGetUser = "http://androidthai.in.th/rmutt/getAllDatamac.php";
    private String[] columnUserString = new String[]{
            "user_ID",
            "email",
            "password",
            "firstname",
            "lastname",
            "status"
    };

    public String[] getColumnUserString() {
        return columnUserString;
    }

    public String getUrlGetUser() {
        return urlGetUser;
    }

    public String getUrlPostUser() {
        return urlPostUser;
    }
}//Main Class
