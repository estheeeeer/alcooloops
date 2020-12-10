package fr.estheeer.alcooloops;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class AFragment extends Fragment implements View.OnClickListener {
    private double price, alcool;
    private View view;
    private EditText edname, edalcool, edprice, edvolume,edshop;
    private ImageView photoview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.formfragment, container, false);
        Button button = view.findViewById(R.id.buttonv);
        Button bphotoadd = view.findViewById(R.id.photoadd);
        this.photoview = view.findViewById(R.id.photoview);
        this.view = view;
        this.edname = this.view.findViewById(R.id.name);
        this.edalcool = this.view.findViewById(R.id.alcool);
        this.edprice = this.view.findViewById(R.id.price);
        this.edvolume = this.view.findViewById(R.id.volume);
        this.edshop = this.view.findViewById(R.id.shop);
        if (!super.getArguments().isEmpty()) {
            int idproduct = super.getArguments().getInt("idproduct");
            if (idproduct == 0) {
                edname.setText("biere");
                edalcool.setText("7");
                edvolume.setText("33");
                edshop.setText("shop");
            }
        }
        button.setOnClickListener(this);
        bphotoadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 7);

            }
        });
        return view;

    }

    @Override
    public void onClick(View view) {
        String name = edname.getText().toString();
        String alcools = edalcool.getText().toString();
        String prices = edprice.getText().toString();
        String shop = edshop.getText().toString();

        if (shop.isEmpty() || name.isEmpty() || prices.isEmpty() || alcools.isEmpty()) {
            Toast.makeText(this.view.getContext(), "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
        } else {
            try {
                price = Double.parseDouble(prices);
                alcool = Double.parseDouble(alcools);
            } catch (Exception e) {
                Toast.makeText(this.view.getContext(), "Un nombre est requis", Toast.LENGTH_LONG).show();
            }
        }

    }
}