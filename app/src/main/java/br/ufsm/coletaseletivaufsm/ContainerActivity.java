package br.ufsm.coletaseletivaufsm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.Locale;
import br.ufsm.coletaseletivaufsm.containers.Containers;

public class ContainerActivity extends AppCompatActivity {


    /**
     * Metodo de inicialização
     * Seta todas as Views
     *
     * Busca o container que é enviado na abertura do Intent
     *
     * Seta a ação de click para gerar o alerta e abrir mapa
     *
     * @param savedInstanceState Salvar a instância
     */
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

        imageButtonAbrirMapa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f?z=19&q=<"+containers.getLatitude()+">,<"+containers.getLongitude()+">("+containers.getNome() +")", containers.getLatitude(),containers.getLongitude());
                showAlert("Abrindo mapa", "Deseja abrir o mapa para ver a localização?", uri);
            }
        });

        /**
         * Download da foto e atribuição ao ImageView
         *
         */
        if(isOnline()) {
            Picasso.with(getApplicationContext()).load(containers.getLinkfoto()).into(fotoContainer);
        }

    }

    /**
     * Metodo para verificação de conexão com a internet
     * @return Esta conectado?
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Metodo para gerar um alerta na tela e abrir o mapa
     *
     * @param titulo Titulo do alerta
     * @param mensagem Texto no alerta
     * @param uri URI contendo as coordenadas para abrir o mapa
     */
    public void showAlert(String titulo, String mensagem, final String uri) {
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

    /**
     * Metodo para abrir o mapa em outra tela
     *
     * @param geoLocation URI enviada com as coordenadas pelo alerta
     */
    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
