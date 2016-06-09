package com.xomorod.location;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Ahmad on 6/9/2016.
 */
public class Project {

    public static void exportDatabase(Context context,String databaseName,String fileName) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//"+context.getPackageName()+"//databases//"+databaseName+".db";

                String datetime;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(R.string.dateTimeFormat), Locale.US);
                datetime = dateFormat.format(c.getTime());
                String backupDBPath = fileName+"-"+datetime+".db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                    Toast.makeText(context, String.format(context.getString(R.string.export_prompt),backupDB), Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(context, "Can't not find database", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                Toast.makeText(context, "Can't write sdcard.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
