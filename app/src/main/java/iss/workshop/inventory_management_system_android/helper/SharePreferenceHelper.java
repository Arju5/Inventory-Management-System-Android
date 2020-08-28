package iss.workshop.inventory_management_system_android.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceHelper {
    private SharedPreferences sharedPreference;

    private static String SHARE_PREFRENCE = "inventorypref";

    private static String USER_NAME = "user_name";
    private static String USER_ID = "user_id";
    private static String USER_ROLE = "user_role";

    public SharePreferenceHelper(Context context)
    {
        sharedPreference = context.getSharedPreferences(SHARE_PREFRENCE, Context.MODE_PRIVATE);
    }

    //***** Member Login *****//
    public void setLogin(String user_name,int user_id,String user_role)
    {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(USER_NAME, user_name);
        editor.putInt(USER_ID,user_id);
        editor.putString(USER_ROLE,user_role);
        editor.commit();//put in shared preference
    }

    public void logoutSharePreference()
    {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.clear();
        editor.commit();
    }

    public String getUserName() {
        return sharedPreference.getString(USER_NAME,"");
    }

    public int getuserId(){
        return sharedPreference.getInt(USER_ID,0);
    }

    public String getUserRole() { return sharedPreference.getString(USER_ROLE,""); }

    public boolean isLogin()
    {
        return sharedPreference.contains(USER_ID);
    }
    //***** Member Login *****//
}
