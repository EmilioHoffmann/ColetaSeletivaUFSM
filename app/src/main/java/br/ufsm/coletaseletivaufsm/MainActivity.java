package br.ufsm.coletaseletivaufsm;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

/**
 * Criado por Emilio Hoffmann em 11/2016
 *
 */

public class MainActivity extends FragmentActivity {
    private Dia dias[];
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dias = new Dia[3];

        setContentView(R.layout.activity_main);
        try {
            inicializar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void inicializar() throws InterruptedException {
        Date date = new Date(System.currentTimeMillis()); //or simply new Date();
        String today = (String) android.text.format.DateFormat.format("dd/MM", date);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        String updatedAt = sharedPreferences.getString("ATUALIZADOEM",  "");
        if(isOnline() && !today.equals(updatedAt)) {
            DownloadFile();
        }

        InputStreamReader is;
        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/arquivo.csv";
            is = new InputStreamReader(new FileInputStream(path));
            BufferedReader reader = new BufferedReader(is);
            int x = 0;
            String line;

            FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
            mTabHost.setup(getApplicationContext(), getSupportFragmentManager(), android.R.id.tabcontent);

            while ((line = reader.readLine()) != null) {
                dias[x] = new Dia(line.split(",")[0], Arrays.asList(line.split(",")));
                dias[x].setLocais(dias[x].getLocais().subList(1, dias[x].getLocais().size()));
                mTabHost.addTab(
                        mTabHost.newTabSpec(Integer.toString(x)).setIndicator(dias[x].getDias(), null),
                        FragmentTab.class, null);
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Thread.sleep(500);
            inicializar();
        }
    }

    public Dia[] getDias() {
        return dias;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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