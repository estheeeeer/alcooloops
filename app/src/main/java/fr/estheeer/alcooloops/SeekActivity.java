package fr.estheeer.alcooloops;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SeekActivity extends AppCompatActivity implements View.OnClickListener {
        private SeekBar seekbar;
        private double rcoeff, toceff;
        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.seek_bar);
            seekbar = findViewById(R.id.seekbar);
            TextView percent = findViewById(R.id.tvpercent);
            ImageButton bget = findViewById(R.id.buttonsb);
            bget.setOnClickListener(this);
            seekbar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    percent.setText(String.valueOf(seekbar.getProgress())+"% rogne\n"+String.valueOf(100-seekbar.getProgress())+"% goût");
                    return false;
                }
            });
        }

        @Override
        public void onClick(View view) {
                rcoeff = seekbar.getProgress();
                toceff = 100-seekbar.getProgress();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);

    }

}
