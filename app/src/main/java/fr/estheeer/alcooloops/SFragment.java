package fr.estheeer.alcooloops;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SFragment  extends Fragment {
        private View view;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            String nmagasins = super.getArguments().getString("nmagasins");
            String[] products = new String[]{
                    "Goudale",
                    "Rince Cochon",
                    "Corona",
                    "Leffe"
            };
            ArrayList alistproducts = new ArrayList<String>(Arrays.asList(products));
            View view = inflater.inflate(R.layout.shopfragment, container, false);
            ListView listvproducts = view.findViewById(R.id.listproducts);
            //List<String> productslist = new ArrayList<String>(Arrays.asList(products));
            //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
            //        (view.getContext(), android.R.layout.simple_expandable_list_item_1, productslist);
            listvproducts.setAdapter(new CustomAdapter(alistproducts, view.getContext(), nmagasins, super.getParentFragmentManager(), listvproducts));
            //Button button = view.findViewById(R.id.buttonv);
            //button.setOnClickListener(this);
            this.view = view;
            TextView namegasin = view.findViewById(R.id.namemagasin);
            namegasin.setText(nmagasins);
            return view;

            }


}
