package com.example.sourabh.filestorageexamples;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        text = (TextView)findViewById(R.id.text);
    }

    public void LoadFromInternalStorage(View view) {
        File dir = getFilesDir();  //return the directory in which your file will be created
        File file = new File(dir,"MyFile1.txt");  //Creates a new file named MyFile1.txt in a folde "dir"
        readData(file);  //Calling user defined method
    }

    public void LoadFromInternalCacheStorage(View view) {
        File dir = getCacheDir();  //return the directory of internal cache in which your file will be created
        File file = new File(dir,"MyFile2.txt");  //Creates a new file named MyFile2.txt in a folder "dir"
        readData(file);  //Calling user defined method
    }

    public void LoadFromExternalCacheStorage(View view) {
        File dir = getExternalCacheDir();  //return the sd card (external memory card) cache directory in which your file will be created
        File file = new File(dir,"MyFile3.txt");  //Creates a new file named MyFile3.txt in a folder "dir"
        readData(file);  //Calling user defined method
    }

    public void LoadFromExternalPrivateStorage(View view) {
        File dir = getExternalFilesDir("MyDir");  //creates the directory named MyDir in External Files Directory
        File file = new File(dir,"MyFile4.txt");  //Creates a new file named MyFile4.txt in a folder "dir"
        readData(file);  //Calling user defined method
    }

    public void LoadFromExternalPublicStorage(View view) {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);  //return the  Downloads folder directory in which your file will be created
        File file = new File(dir,"MyFile5.txt");  //Creates a new file named MyFile5.txt in a folde "dir"
        readData(file);  //Calling user defined method
    }


    public void Back(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void readData(File file)
    {
        FileInputStream fileInputStream=null;

        try {
            fileInputStream = new FileInputStream(file);
            //fileInputStream = openFileInput("MyFile1.txt");  //open the file for reading
            int read=-1;
            StringBuffer stringBuffer = new StringBuffer();   //to store a string (which is retrieved by Internal Storage)
            while ((read=fileInputStream.read())!=-1)   //read() will return ascii value (char[] bytes) and returns -1 if bytes not found
            {
                stringBuffer.append((char)read);    //Converting ascii into char by typecasting
            }
            text.setText(stringBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null)
            {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
