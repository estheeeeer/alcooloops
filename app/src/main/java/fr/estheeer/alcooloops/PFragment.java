package fr.estheeer.alcooloops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.productfragment, container, false);
        TextView tvname = view.findViewById(R.id.setnameproduct);
        TextView tvprice = view.findViewById(R.id.setpriceproduct);
        TextView tvalcool = view.findViewById(R.id.setalcoolproduct);
        TextView tvrapport = view.findViewById(R.id.setrapportproduct);
        TextView tvshop = view.findViewById(R.id.setshopproduct);
        TextView tvdate = view.findViewById(R.id.setdateregisterproduct);
        return view;
    }
}
