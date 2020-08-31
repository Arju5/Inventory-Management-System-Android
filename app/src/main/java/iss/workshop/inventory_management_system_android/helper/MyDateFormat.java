package iss.workshop.inventory_management_system_android.helper;

import java.text.SimpleDateFormat;

public class MyDateFormat {

    public SimpleDateFormat DATE_FORMAT_YMD;
    public SimpleDateFormat DATE_FORMAT_YMD_TEXT;
    public SimpleDateFormat DATE_FORMAT_YMD_HMS;
    public SimpleDateFormat DATE_FORMAT_DMY;
    public SimpleDateFormat DATE_FORMAT_DMY_HMS_Z;
    public SimpleDateFormat DATE_FORMAT_DMY_HM_AAA;
    public SimpleDateFormat DATE_FORMAT_DMY_HMS_AAA;
    public SimpleDateFormat DATE_FORMAT_NOTI;
    public SimpleDateFormat DATE_FORMAT_YEAR;
    public SimpleDateFormat DATE_FORMAT_DMY_2;

    private String result;

    public MyDateFormat()
    {
        DATE_FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
        DATE_FORMAT_YMD_TEXT = new SimpleDateFormat("dd MMMM, yyyy");
        DATE_FORMAT_YMD_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DATE_FORMAT_DMY = new SimpleDateFormat("dd/MM/yyyy");
        DATE_FORMAT_DMY_HMS_Z = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        DATE_FORMAT_DMY_HM_AAA = new SimpleDateFormat("dd MMMM yyyy, h:mm aaa");
        DATE_FORMAT_DMY_HMS_AAA = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DATE_FORMAT_NOTI = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        DATE_FORMAT_YEAR = new SimpleDateFormat("yyyy");
        DATE_FORMAT_DMY_2 = new SimpleDateFormat("dd MMM yyyy");

    }
    public String removeTfromServerDate(String datetime)
    {
        return  datetime.replace("T", " ");
    }

}
