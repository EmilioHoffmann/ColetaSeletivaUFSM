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


        new DownloadImage().execute("https://upload.wikimedia.org/wikipedia/en/b/be/Google_Chrome_for_Android-_Android_5.0_Logo.png");
        fotoContainer.setImageBitmap(bitmapDownload);

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





    // DownloadImage AsyncTask
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(ContainerActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Download Image Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {
            try {
                DownloadFile();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Bitmap bitmap=null;
        return bitmap;
        }
    }


    public void DownloadFile() throws InterruptedException {

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String fileName = folder.getAbsolutePath()+ "/arquivo.csv";
        File myFile = new File(fileName);
        if(myFile.exists()){
            Boolean deletado = myFile.delete();
            Log.d(TAG, "Arquivo deletado = " + deletado);
        }

        String url = "https://docs.google.com/spreadsheets/d/1g5v7cIC1lWsxtf4r2zQhp21ufed0MtF7bUsVm4S0AgY/pub?output=csv";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "arquivo.csv");
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        /**
         * Setar a data que foi atualizado o arquivo
         *
         */
        Date date = new Date(System.currentTimeMillis()); //or simply new Date();
        String day = (String) android.text.format.DateFormat.format("dd/MM", date);
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ATUALIZADOEM", day);
        editor.apply();
    }

}
