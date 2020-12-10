package fr.estheeer.alcooloops;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private String namemagasin;
    private  FragmentManager fragmentManager;
    private  ListView listView;

    public CustomAdapter(ArrayList<String> list, Context context, String namemagasin, FragmentManager fragmentm, ListView listView) {
        this.list = list;
        this.context = context;
        this.namemagasin = namemagasin;
        this.fragmentManager = fragmentm;
        this.listView = listView;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view ;
        if (convertView == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.customlayout, parent, false);
            convertView = view;
        }else{
            view=convertView;
        }
        int idproduct=0;
        String currentItem = getItem(position);
        TextView textViewItemName = convertView.findViewById(R.id.product);
        textViewItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PFragment fragment = new PFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idproduct",idproduct);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                transaction.commit();

            }
        });

        Button bmodifier = convertView.findViewById(R.id.bnmodfier);
        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem+namemagasin);
        bmodifier.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AFragment fragment = new AFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("idproduct", idproduct);
                fragment.setArguments(bundle);
                FragmentManager fm = fragmentManager;
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                transaction.commit();
            }
        });


        return convertView;

    }

}