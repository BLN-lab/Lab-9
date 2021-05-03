package com.example.lab_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b_read,b_write;
    private EditText edit;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_read=findViewById(R.id.read);
        b_write=findViewById(R.id.write);

        edit=findViewById(R.id.input);

        txt=findViewById(R.id.end);

        b_write.setOnClickListener(this);
        b_read.setOnClickListener(this);
    }

    private void WriteExternal(){
        String state= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            File root = Environment.getExternalStorageDirectory();
            File dir=new File(root.getAbsolutePath()+"/HureeCsClass");
            if(!dir.exists()){
                dir.mkdir();
            }
            File file=new File(dir,"External.txt");
            String message=edit.getText().toString();
            try{
                FileOutputStream fos=new FileOutputStream(file);
                fos.write(message.getBytes());
                edit.setText("");
                Toast.makeText(this,"Message saved...",Toast.LENGTH_SHORT).show();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void ReadExternal(){
        File root=Environment.getExternalStorageDirectory();
        File dir=new File(root.getAbsolutePath()+"/HureeCsClass");
        File file=new File(dir,"External.txt");
        String message;
        try{
            FileInputStream fis=new FileInputStream(file);
            InputStreamReader isr=new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuffer sb=new StringBuffer();
            while((message=br.readLine())!=null){
                sb.append(message+"\n");
            }
            txt.setText(sb.toString());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View id) {
        if(id.getId()==R.id.write){
            WriteExternal();
        }else if(id.getId()==R.id.read){
            ReadExternal();
        }
    }
}