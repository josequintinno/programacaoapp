package br.com.programacaoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String SEPARADOR = "\n";

    public EditText nomeProduto;
    public EditText precoProduto;
    public CheckBox isProdutoImportado;
    public Button salvar;
    public String mensagemErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.nomeProduto = findViewById(R.id.nomeProdutoInput);
        this.precoProduto = findViewById(R.id.precoProdutoInput);
        this.isProdutoImportado = findViewById(R.id.isProdutoImportadoInput);
    }

    public void onSalvarProduto(View view) {

        boolean isValidadoSucesso = true;

        if (this.nomeProduto.getText().toString().trim().isEmpty()) {
            isValidadoSucesso = false;
            this.nomeProduto.setError(getString(R.string.textMensagemCampoObrigatorio));
        }

        if (this.precoProduto.getText().toString().trim().isEmpty()) {
            isValidadoSucesso = false;
            this.precoProduto.setError(getString(R.string.textMensagemCampoObrigatorio));
            Toast.makeText(getApplicationContext(), R.string.textMensagemCampoObrigatorio, Toast.LENGTH_SHORT).show();
        }

        if (this.isProdutoImportado.getText().toString().trim().isEmpty()) {
            isValidadoSucesso = false;
            this.isProdutoImportado.setError(getString(R.string.textMensagemCampoObrigatorio));
            Toast.makeText(getApplicationContext(), R.string.textMensagemCampoObrigatorio, Toast.LENGTH_SHORT).show();
        }

        if (isValidadoSucesso) {

            String nomeProduto = this.nomeProduto.getText().toString();
            Float precoProduto = Float.parseFloat(this.precoProduto.getText().toString());
            Boolean isProdutoImportado = this.isProdutoImportado.isChecked();

            StringBuilder mensagem = new StringBuilder("Dados Informados" + SEPARADOR + SEPARADOR)
                    .append(String.format("Produto: %s" + SEPARADOR, nomeProduto))
                    .append(String.format("Preço: %.2f" + SEPARADOR, precoProduto))
                    .append(String.format("Importado: %s" + SEPARADOR, (isProdutoImportado ? "Sim" : "Não")));

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_produto, findViewById(R.id.toastText));

            TextView toastText = layout.findViewById(R.id.toastText);
            toastText.setText(mensagem.toString());

            Toast customToast = new Toast(getApplicationContext());
            customToast.setDuration(Toast.LENGTH_LONG);
            customToast.setView(layout);

            customToast.show();

        }

    }

}