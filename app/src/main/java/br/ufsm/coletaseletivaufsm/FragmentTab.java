package br.ufsm.coletaseletivaufsm;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.ufsm.coletaseletivaufsm.containers.Containers;
import br.ufsm.coletaseletivaufsm.containers.ContainersDao;
import br.ufsm.coletaseletivaufsm.containers.DaoManager;

/**
 * Criado por Emilio Hoffmann em 11/2016
 *
 */

public class FragmentTab extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);

        final ContainersDao containersDao = DaoManager.getContainersDao();

        switch (this.getTag()){
            case ("0"):
                final ListView lv0 = (ListView) v.findViewById(R.id.listview);
                List<Containers> containersSegunda = containersDao.getContainersSegunda();
                List<String> nomesContainersSegunda = new ArrayList<>();
                for (int x=0; x<containersSegunda.size();x++) {
                    nomesContainersSegunda.add(containersSegunda.get(x).getNome());
                }
                ArrayAdapter<String> adapter0 = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1, nomesContainersSegunda);
                lv0.setAdapter(adapter0);
                lv0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Object listItem = lv1.getItemAtPosition(position);

                        Intent intent = new Intent(getActivity().getApplicationContext(), ContainerActivity.class);
                        intent.putExtra("container", containersDao.getContainerByName(lv0.getItemAtPosition(position).toString()));
                        startActivity(intent);
                    }
                });
                break;
            case ("1"):
                final ListView lv1 = (ListView) v.findViewById(R.id.listview);
                List<Containers> containersQuarta = containersDao.getContainersQuarta();
                List<String> nomesContainersQuarta = new ArrayList<>();

                for (int x=0; x<containersQuarta.size();x++) {
                    nomesContainersQuarta.add(containersQuarta.get(x).getNome());
                }
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1, nomesContainersQuarta);
                lv1.setAdapter(adapter1);
                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Object listItem = lv1.getItemAtPosition(position);

                        Intent intent = new Intent(getActivity().getApplicationContext(), ContainerActivity.class);
                        intent.putExtra("container", containersDao.getContainerByName(lv1.getItemAtPosition(position).toString()));
                        startActivity(intent);
                    }
                });
                break;
        }
        return v;
    }

    public void alerta(String titulo, String mensagem, final String uri) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle(titulo);
        alert.setMessage(mensagem);
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                showMap(Uri.parse(uri));
            }
        });

        alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
    }


    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}