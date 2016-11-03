package ufsm.br.coletaseletivaufsm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashSet;


public class FragmentTab extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView textView = (TextView) v.findViewById(R.id.text);

        SharedPreferences sharedPref = getContext().getSharedPreferences("Dias", Context.MODE_PRIVATE);
        HashSet hashSet = new HashSet();

        if (this.getTag().equals("0")){
            textView.setText("" + sharedPref.getStringSet("Dias0", hashSet));
        }else if (this.getTag().equals("1")){
            textView.setText("" + sharedPref.getStringSet("Dias1", hashSet));
        }else if (this.getTag().equals("2")){
            textView.setText("" + sharedPref.getStringSet("Dias2", hashSet));
        }

        return v;
    }
}