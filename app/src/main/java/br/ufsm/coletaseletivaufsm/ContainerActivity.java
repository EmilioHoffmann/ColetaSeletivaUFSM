package br.ufsm.coletaseletivaufsm;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import br.ufsm.coletaseletivaufsm.containers.Containers;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        TextView nomeContainer = (TextView) findViewById(R.id.nomeContainer);
        TextView descricaoContainer = (TextView) findViewById(R.id.descricaoContainer);
        ImageButton imageButtonAbrirMapa = (ImageButton) findViewById(R.id.imageButtonOpenMap);
        ImageView fotoContainer = (ImageView) findViewById(R.id.imageViewFotoContainer);

        final Containers containers = (Containers) getIntent().getSerializableExtra("container");

        nomeContainer.setText(containers.getNome());
        descricaoContainer.setText(containers.getDescricao());
        //fotoContainer.setImageBitmap(getBitmapFromURL(containers.getLinkfoto()));

        imageButtonAbrirMapa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=19&q=<"+containers.getLatitude()+">,<"+containers.getLongitude()+">("+containers.getNome() +")", containers.getLatitude(),containers.getLongitude());
                alerta("Abrindo mapa", "Deseja abrir o mapa para ver a localização?", uri);
            }
        });
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public void alerta(String titulo, String mensagem, final String uri) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
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
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
