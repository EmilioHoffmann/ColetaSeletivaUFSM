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
import android.widget.TextView;

import java.util.Locale;

import br.ufsm.coletaseletivaufsm.R;

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
        TextView textView = (TextView) v.findViewById(R.id.text);

        Dia dias[];
        dias=((MainActivity) getActivity()).getDias();


        ArrayAdapter<String> locaisAdapter =
                new ArrayAdapter<String>(getContext(),
                        R.layout.local,
                        R.id.cheese_name,
                        dias[0].getLocais()
                );
        ListView locaisList = new ListView(getContext());
        locaisList.setAdapter(locaisAdapter);

        switch (this.getTag()){
            case ("0"):
                final ListView lv0 = (ListView) v.findViewById(R.id.listview);
                ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dias[0].getLocais());
                lv0.setAdapter(adapter0);
                lv0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object listItem = lv0.getItemAtPosition(position);

                        Double latitude = -29.713162;
                        Double longitude = -53.716625;
                        switch (position){
                            case (0):
                                latitude = -29.7208044;
                                longitude = -53.7147546;
                                break;
                            case (1):
                                latitude = -29.7125116;
                                longitude = -53.71602389999998;
                                break;
                            case (2):
                                latitude = -29.7122117;
                                longitude = -53.716029700000036;
                                break;
                            case (3):
                                latitude = -29.7113164;
                                longitude = -53.7163612;
                                break;
                            case (4):
                                latitude = -29.7148128;
                                longitude = -53.717996700000015;
                                break;
                            case (5):
                                latitude = -29.72423330000001;
                                longitude = -53.71174930000001;
                                break;
                            case (6):
                                latitude = -29.711088;
                                longitude = -53.71712020000001;
                                break;
                            case (7):
                                latitude = -29.7231197;
                                longitude = -53.7201083;
                                break;
                            case (8):
                                latitude = -29.7131218;
                                longitude = -53.7187859;
                                break;
                            case (9):
                                latitude = -29.720072;
                                longitude = -53.71722199999999;
                                break;
                            case (10):
                                latitude = -29.711761;
                                longitude = -53.71752459999999;
                                break;
                        }
                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=19&q=<"+latitude+">,<"+longitude+">("+lv0.getItemAtPosition(position) +")", latitude , longitude);
                        alerta("Abrindo mapa", "Deseja abrir o mapa para ver a localização?", uri);
                    }
                });
                break;
            case ("1"):
                final ListView lv1 = (ListView) v.findViewById(R.id.listview);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dias[1].getLocais());
                lv1.setAdapter(adapter1);
                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object listItem = lv1.getItemAtPosition(position);

                        Double latitude = -29.713162;
                        Double longitude = -53.716625;
                        switch (position){
                            case (0):
                                latitude = -29.7155259;
                                longitude = -53.7193036;
                                break;
                            case (1):
                                latitude = -29.7183771;
                                longitude = -53.7157416;
                                break;
                            case (2):
                                latitude = -29.7148279;
                                longitude = -53.71651880000002;
                                break;
                            case (3):
                                latitude = -29.7184191;
                                longitude = -53.7166536;
                                break;
                            case (4):
                                latitude = -29.7185914;
                                longitude = -53.7177426;
                                break;
                            case (5):
                                latitude = -29.7209255;
                                longitude = -53.7179357;
                                break;
                            case (6):
                                latitude = -29.72004689999999;
                                longitude = -53.71022679999999;
                                break;
                            case (7):
                                latitude = -29.71334389999999;
                                longitude = -53.71682129999999;
                                break;
                            case (8):
                                latitude = -29.7221918;
                                longitude = -53.71814189999998;
                                break;
                            case (9):
                                latitude = -29.7110791;
                                longitude = -53.71802059999999;
                                break;
                            case (10):
                                latitude = -29.720222;
                                longitude = -53.7180912;
                                break;
                            case (11):
                                latitude = -29.7163062;
                                longitude = -53.7180564;
                                break;
                            case (12):
                                latitude = -29.7161991;
                                longitude = -53.7175709;
                                break;
                            case (13):
                                latitude = -29.7161036;
                                longitude = -53.7170935;
                                break;
                            case (14):
                                latitude = -29.7159848;
                                longitude = -53.7161896;
                                break;
                            case (15):
                                latitude = -29.720724;
                                longitude = -53.7148015;
                                break;
                            case (16):
                                latitude = -29.7140484;
                                longitude = -53.7192862;
                                break;
                            case (17):
                                latitude = -29.7171804;
                                longitude = -53.714622099999985;
                                break;
                        }
                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=19&q=<"+latitude+">,<"+longitude+">("+lv1.getItemAtPosition(position) +")", latitude , longitude);
                        alerta("Abrindo mapa", "Deseja abrir o mapa para ver a localização?", uri);
                    }
                });
                break;
            case ("2"):
                final ListView lv2 = (ListView) v.findViewById(R.id.listview);
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,dias[2].getLocais());
                lv2.setAdapter(adapter2);
                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object listItem = lv2.getItemAtPosition(position);

                        Double latitude = -29.713162;
                        Double longitude = -53.716625;
                        switch (position){
                            case (0):
                                latitude = -29.6880467;
                                longitude = -53.80879070000003;
                                break;
                            case (1):
                                latitude = -29.715651;
                                longitude = -53.7152074;
                                break;
                            case (2):
                                latitude = -29.7153954;
                                longitude = -53.7196255;
                                break;
                            case (3):
                                latitude = -29.7161118;
                                longitude = -53.71880169999997;
                                break;
                            case (4):
                                latitude = -29.72696349999999;
                                longitude = -53.717091400000015;
                                break;
                            case (5):
                                latitude = -29.712534;
                                longitude = -53.71717339999998;
                                break;
                            case (6):
                                latitude = -29.7194952;
                                longitude = -53.71797860000004;
                                break;
                            case (7):
                                latitude = -29.7211584;
                                longitude = -53.7179786;
                                break;
                            case (8):
                                latitude = -29.7208044;
                                longitude = -53.7147546;
                                break;
                            case (9):
                                latitude = -29.71719989999999;
                                longitude = -53.714690899999994;
                                break;
                        }
                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=19&q=<"+latitude+">,<"+longitude+">("+lv2.getItemAtPosition(position) +")", latitude , longitude);
                        alerta("Abrindo mapa", "Deseja abrir o mapa para ver a localização?", uri);
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