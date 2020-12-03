package fr.estheeer.alcooloops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SFragment  extends Fragment implements View.OnClickListener{
        private View view;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            String nmagasins = super.getArguments().getString("nmagasins");
            System.out.println("----------------------nmagasins = "+nmagasins+"------------------");
            View view = inflater.inflate(R.layout.shopfragment, container, false);
            TextView lproductsm = view.findViewById(R.id.namem);
            lproductsm.setText(nmagasins);
            //Button button = view.findViewById(R.id.buttonv);
            //button.setOnClickListener(this);
            this.view =  view;
            return view;
        }

    @Override
    public void onClick(View view) {

    }
}
