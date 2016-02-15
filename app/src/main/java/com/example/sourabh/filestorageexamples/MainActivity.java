package com.example.sourabh.filestorageexamples;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.FileHandler;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
    }


    public void Next(View view) {
        startActivity(new Intent(this,MainActivity2.class));
    }

    public void SaveInInternalStorage(View view) {
        String text=editText.getText().toString();
        File dir = getFilesDir();  //return the directory in which your file will be created
        File file = new File(dir,"MyFile1.txt");  //Creates a new file named MyFile1.txt in a folde "dir"
        writeData(file, text);  //Calling user defined method

    }

    public void SaveInInternalCacheStorage(View view) {
        String text=editText.getText().toString();
        File dir = getCacheDir();  //return the directory of internal cache in which your file will be created
        File file = new File(dir,"MyFile2.txt");  //Creates a new file named MyFile1.txt in a folde "dir"
        writeData(file, text);  //Calling user defined method

    }

    public void SaveInExternalCacheStorage(View view) {
        String text=editText.getText().toString();
        File dir = getExternalCacheDir();  //return the sd card (external memory card) cache directory in which your file will be created
        File file = new File(dir,"MyFile3.txt");  //Creates a new file named MyFile1.txt in a folder "dir"
        writeData(file, text);  //Calling user defined method

    }

    public void SaveInExternalPrivateStorage(View view) {
        String text=editText.getText().toString();
        File dir = getExternalFilesDir("MyDir");  //creates the directory named MyDir in External Files Directory
        File file = new File(dir,"MyFile4.txt");  //Creates a new file named MyFile1.txt in a folder "dir"
        writeData(file, text);  //Calling user defined method

    }

    public void SaveInExternalPublicStorage(View view) {
        String text=editText.getText().toString();
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);  //return the  Downloads folder directory in which your file will be created
        File file = new File(dir,"MyFile5.txt");  //Creates a new file named MyFile1.txt in a folde "dir"
        writeData(file, text);  //Calling user defined method

    }

    public void writeData(File file,String text)
    {
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(file);
            //fileOutputStream=openFileOutput("MyFile1.txt", Context.MODE_PRIVATE);  //to open the file for writing text in private mode
            fileOutputStream.write(text.getBytes());    //writing the text into the file in the form of char[] bytes ; getBytes will convert string to char[] bytes
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream!=null)
            {
                try {
                    fileOutputStream.close();    //closing the file
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Toast.makeText(this,"Data saved to "+file.getAbsolutePath(),Toast.LENGTH_LONG).show();  //getAbsolutePath returns the directory of the file

    }
}
