package ufsm.br.coletaseletivaufsm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;


public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private Dia dias[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dias = new Dia[3];
        inicializar();
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("0").setIndicator(dias[0].getDias(), null),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("1").setIndicator(dias[1].getDias(), null),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("2").setIndicator(dias[2].getDias(), null),
                FragmentTab.class, null);
    }


    public void inicializar(){
        SharedPreferences sharedPref = getSharedPreferences("Dias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        InputStreamReader is = null;
        try {
            is = new InputStreamReader(getAssets().open("arquivo.csv"));
            BufferedReader reader = new BufferedReader(is);
            int x = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                dias[x] = new Dia();
                dias[x].setDias(line.split(",")[0]);
                dias[x].setLocais(Arrays.asList(line.split(",")));
                editor.putStringSet("Dias"+x, new HashSet<String>(dias[x++].getLocais()));
                editor.apply();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}