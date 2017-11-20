package com.student.nicolgom.secretsantahelper;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;


import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Nicolas on 11/19/2017.
 */

public class contactLoader extends AsyncTask<Context, Integer, ArrayList<Contact>>{

    @Override
    protected ArrayList<Contact> doInBackground(Context... contexts) {
        ArrayList<Contact> list = new ArrayList<Contact>();
        Cursor peeps = contexts[0].getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while(peeps.moveToNext()){

            if(peeps.getInt(peeps.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))>0) {
                String name = peeps.getString(peeps.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = peeps.getString(peeps.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if(phone != null) {
                    String id = peeps.getString(peeps.getColumnIndex(ContactsContract.Contacts._ID));
                    InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(contexts[0].getContentResolver(),
                            ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id)));
                    Bitmap img = null;

                    if (inputStream != null) {
                        img = BitmapFactory.decodeStream(inputStream);
                    }

                    Contact temp = new Contact(name, phone, img);
                    list.add(temp);
                }
            }

        }

        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Contact> contacts) {
        super.onPostExecute(contacts);

    }
}
