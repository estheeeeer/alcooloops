package fr.estheeer.alcooloops;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Double price,alcool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.buttonv);
        button.setOnClickListener(this);
        }
    @Override
    public void onClick(View v) {
        EditText edn = findViewById(R.id.name);
        EditText eda = findViewById(R.id.alcool);
        EditText edp = findViewById(R.id.price);
        String name = edn.getText().toString();
        String prices = edp.getText().toString();
        String alcools = eda.getText().toString();
        try {
            price = Double.parseDouble(prices);
            alcool = Double.parseDouble(alcools);
        }catch (Exception e){
            Toast.makeText(this, "Un nombre est requis", Toast.LENGTH_LONG).show();
        }
        EditText eds = findViewById(R.id.shop);
        String shop = eds.getText().toString();
        System.out.println("Name = "+name+" alcool = "+alcools+" Price = "+prices+"Shop = "+shop);
    }
}