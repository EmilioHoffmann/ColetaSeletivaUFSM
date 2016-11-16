package br.ufsm.coletaseletivaufsm;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

import br.ufsm.coletaseletivaufsm.containers.Containers;

public class ContainerActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private String downloadUrl = "https://www.iconexperience.com/_img/v_collection_png/256x256/shadow/cargo_container.png";
    private Bitmap bitmapDownload;
    private static final String TAG = MainActivity.class.getSimpleName();

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


        //new DownloadImageTask(fotoContainer).execute(containers.getLinkfoto() );
        //fotoContainer.setImageBitmap(bitmapDownload);

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





    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onPreExecute() {
            Log.i("Async-Example", "onPreExecute Called");
            mProgressDialog = ProgressDialog.show(ContainerActivity.this,
                    "Wait", "Downloading Image");
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            mProgressDialog.dismiss();
            bmImage.setImageBitmap(result);
        }
    }


}
