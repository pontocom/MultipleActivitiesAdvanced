package pt.iscte.pamdaam.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // definir uma variável para ter o nome da app (apenas para não ter que repetir o texto várias vezes)
    protected static String APP_NAME = "MULTIPLE_ACTIVITIES_APP";

    // definir as variáveis para ler os campos nome e email do formulário
    protected EditText nome, email;

    static int INPUT_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // obter a ligação aos campos do formulário no layout da Activity
        nome = (EditText) findViewById(R.id.etNome);
        email = (EditText) findViewById(R.id.etEmail);
    }

    /**
     * Função usada para processar o click num determinado botão
     */
    public void clickSecondActivityButton(View v) {
        // mostrar uma mensagem no Log a dizer que carregaram no botão
        Log.i(APP_NAME, "----> CARREGARAM NO BOTÃO!");

        // obter o texto que foi introduzido no campo "nome" do formulário e guardá-lo numa variável
        String s_nome = nome.getText().toString();

        // obetr o texto que foi introduzido no campo "email" do formulário e guardá-lo numa variável
        String s_email = email.getText().toString();

        // escrever o nome introduzido para o Log
        Log.i(APP_NAME, "----> NOME = " + s_nome);


        // vamos passar os dados introduzidos por este utilizador nesta Activity para a SecondActivity
        Intent i = new Intent(this, SecondActivity.class);

        // adicionar os dados que queremos passar para a SecondActivity
        i.putExtra("NOME", s_nome); // nome
        i.putExtra("EMAIL", s_email); // email

        // iniciar uma segunda actividade, passando o Intent que já contém valores
        //startActivity(i);

        // iniciar uma segunda actividade, passando o Intent que já contém valores, e esperar pelos resultados da SecondActivity
        startActivityForResult(i, INPUT_REQUEST);
    }

    /**
     * Função usada para limpar os dados do formulário
     */
    public void clearDataClick(View v) {
        //basicamente o que precisamos de fazer é colocar um texto vazio nos campos do formulário
        nome.setText("");
        email.setText("");
    }

    /**
     * Função usada para tratar do retorno de outras Actividades - neste caso da SecondActivity
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // validar o resultado devolvido
        if(resultCode == Activity.RESULT_OK) {
            // apresenta o resultado retornado e mostra-o no ecran
            Toast.makeText(this, data.getStringExtra("FRASE"), Toast.LENGTH_LONG).show();
        }
    }
}
