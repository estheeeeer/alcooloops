package fr.estheeer.alcooloops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class AFragment extends Fragment implements View.OnClickListener{
    private double price,alcool;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.formfragment, container, false);
        Button button = view.findViewById(R.id.buttonv);
        button.setOnClickListener(this);
        this.view =  view;
        return view;

    }

    @Override
    public void onClick(View view) {
        EditText edn = this.view.findViewById(R.id.name);
        EditText eda = this.view.findViewById(R.id.alcool);
        EditText edp = this.view.findViewById(R.id.price);
        String name = edn.getText().toString();
        String prices = edp.getText().toString();
        String alcools = eda.getText().toString();
        EditText eds = this.view.findViewById(R.id.shop);
        String shop = eds.getText().toString();
        try {
            price = Double.parseDouble(prices);
            alcool = Double.parseDouble(alcools);
        }catch (Exception e){
            Toast.makeText(this.view.getContext(), "Un nombre est requis", Toast.LENGTH_LONG).show();
        }
        if(shop.isEmpty()||name.isEmpty()||prices.isEmpty()||alcools.isEmpty()){
            Toast.makeText(this.view.getContext(), "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();

        }

    }
}
