package com.example.sqlite2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<Contact> {
    private Context ctx;
    private ArrayList<Contact> ary;
    private int resourceId;

    public ContactsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Contact> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.resourceId = resource;
        this.ary = objects;
    }

    public int getCount() {
        return this.ary.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(this.resourceId, null);
        Contact c = this.ary.get(position);

        TextView tvName = v.findViewById(R.id.textViewName);
        TextView tvPhone = v.findViewById(R.id.textViewPhone);
        ImageView ivContacts = v.findViewById(R.id.contactImgView);
        tvName.setText(c.getName());
        tvPhone.setText(c.getPhone());

        byte[] ary = c.getContactImage();
        if (ary != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(ary, 0, ary.length);
            ivContacts.setImageBitmap(bitmap);
        }
        
        return v;
    }
}
