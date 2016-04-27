package pt.iscte.pamdaam.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    // definir uma variável para ter o nome da app (apenas para não ter que repetir o texto várias vezes)
    protected static String APP_NAME = "MULTIPLE_ACTIVITIES_APP";

    // declaração de variáveis para tratar de lidar com os campos do layout
    protected TextView tvNome, tvEmail;

    // variavel que permite construir uma frase de retorno
    protected String returnString;

    // definir uma variável para manipular o número de telefone
    protected EditText etPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // obter os campos do layout
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);

        // obter o Intent que está a ser enviado pela MainActivity
        Intent i = getIntent();

        // obter os dados que foram enviados - nome e email
        String nome = i.getStringExtra("NOME"); //nome
        String email = i.getStringExtra("EMAIL"); //email

        // construir uma frase de retorno
        returnString = "Nome -> " + nome + "; Email -> " + email;

        // escrever o nome introduzido para o Log
        Log.i(APP_NAME, "----> NOME = " + nome);

        // depois de obter os valores, colocar os mesmos no layout
        tvNome.setText(nome);
        tvEmail.setText(email);
    }

    /**
     * Função para definir o que faz o botão de Close
     */
    public void closeButtonClick(View v) {
        // construir o Intent para devolver
        Intent r = new Intent();

        // colocar os dados para retornar
        r.putExtra("FRASE", returnString);

        // estabelecer o resultado a devolver
        setResult(Activity.RESULT_OK, r);

        // termina a actividade actual
        finish();
    }

    /**
     * Função que serve para demonstrar a passagem de Intents para outras Activities
     */
    public void buttonCallClick(View v) {

        String phone = "tel:" + etPhoneNumber.getText().toString();

        Intent tel = new Intent(Intent.ACTION_DIAL);
        tel.setData(Uri.parse(phone));
        startActivity(tel);
    }
}
