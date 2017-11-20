package com.student.nicolgom.secretsantahelper;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nicolas on 11/19/2017.
 */

public class contactAdapter extends ArrayAdapter<Contact>{
    //Context con;


    public contactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact temp = getItem(position);
        ViewHolder viewHolder;
        Log.d("demo", "Works?");
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.conlayout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.nameTxt);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.phoneTxt);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.photo);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (contactAdapter.ViewHolder) convertView.getTag();
        }
        if(temp.name != null) {
            viewHolder.name.setText(temp.getName());
        }
        if(temp.number != null){
            viewHolder.phone.setText(temp.getNumber());
        }
        if(temp.photo != null){
            viewHolder.img.setImageBitmap(temp.getPhoto());
            Log.d("Demo", "img "+ temp.getPhoto().toString());

        }else{
            viewHolder.img.setImageResource(R.drawable.ic_launcher_background);
        }



        return convertView;
    }
    private static class ViewHolder{
        TextView name;
        TextView phone;
        ImageView img;
    }

}
