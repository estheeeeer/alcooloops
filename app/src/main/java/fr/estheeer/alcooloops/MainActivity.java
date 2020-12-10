package fr.estheeer.alcooloops;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] magasins = new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment, AFragment.class,new Bundle() )
                    .commit();
        }

        ViewGroup viewscroll = findViewById(R.id.scroll);
        magasins[0]="Casino";
        magasins[1]="Lidl";
        magasins[2]="LeaderPrice";
        magasins[3]="G20";
        magasins[4]="Franprix";


        for(int i=0; i<5; i++) {
            Button bm = new Button(this);
            bm.setLayoutParams(new LinearLayout.LayoutParams(195, 195));
            viewscroll.addView(bm);
            bm.setId(i);
            Button button = super.findViewById(i);
            button.setOnClickListener(this);
            button.setTextSize(40);
            button.setText(""+magasins[i].charAt(0));
        }
        ImageButton buttonhome = new ImageButton(this);
        buttonhome = super.findViewById(R.id.home);
        buttonhome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //AFragment fragment = new AFragment();
                AFragment fragment = new AFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                transaction.commit();
            }
        });
    }

    @Override
    public void onClick(View view) {
        SFragment fragment = new SFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nmagasins", magasins[view.getId()]);
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();

    }
}