package com.student.nicolgom.secretsantahelper;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    static int PERMISSION_CHECK= 0002;
    ListView display;
    ArrayList<Contact> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS}, PERMISSION_CHECK);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            contacts = new contactLoader().execute(MainActivity.this).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(contacts == null){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("demo", contacts.toString());
            display = (ListView) findViewById(R.id.displayList);
            contactAdapter adapter = new contactAdapter(MainActivity.this, R.layout.conlayout, contacts);
            display.setAdapter(adapter);
        }


    }
}
