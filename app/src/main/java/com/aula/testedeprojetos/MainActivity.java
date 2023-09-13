package com.aula.testedeprojetos;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editPeso,editAltura;
    TextView txtPes,txtAci,txtAba,txtIde;
    Button btnCalcular;
    RadioButton radioM,radioF;
    int acimapt=0,baixopt=0,idealpt=0,pesquisapt=0;
    String message = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregar();

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){
                    calcular();
                }
            }
        });
    }

    private void calcular() {
        double peso = Double.parseDouble(editPeso.getText().toString());
        double altura = Double.parseDouble(editAltura.getText().toString());
        double resultado = peso / (altura*altura);
        String rs = String.format("%.2f",resultado );//Formatação do resultado
        if(radioM.isChecked()){
            if(resultado < 18){
                baixopt++;
                message = "Abaixo do peso ideal. Resultado: "+rs+"Kg/m2";
            } else if (resultado <= 25) {
                acimapt++;
                message = "Acima do peso ideal. Resultado: "+rs+"Kg/m2";
            }else{
                idealpt++;
                message = "Peso ideal. Resultado: "+rs+"Kg/m2";
            }
        }else{
            if(resultado < 17){
                baixopt++;
                message = "Abaixo do peso ideal. Resultado: "+rs+"Kg/m2";
            } else if (resultado <= 26) {
                acimapt++;
                message = "Acima do peso ideal. Resultado: "+rs+"Kg/m2";
            }else{
                idealpt++;
                message = "Peso ideal. Resultado: "+rs+"Kg/m2";
            }
        }
        pesquisapt++;
        txtPes.setText("Pesquisa: "+pesquisapt);
        txtAci.setText("%Acima: "+acimapt);
        txtAba.setText("%Abaixo: "+baixopt);
        txtIde.setText("%Ideal: "+idealpt);
        Toast toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
        toast.show();
    }

    private void carregar(){
        editPeso = findViewById(R.id.editPeso);
        editAltura = findViewById(R.id.editAltura);
        txtPes = findViewById(R.id.txtPes);
        txtAci = findViewById(R.id.txtAci);
        txtAba = findViewById(R.id.txtAba);
        txtIde = findViewById(R.id.txtIde);
        btnCalcular = findViewById(R.id.btnCalcular);
        radioM = findViewById(R.id.radioM);
        radioF = findViewById(R.id.radioF);
    }

    private boolean validar(){
        String peso = editPeso.getText().toString();
        String altura = editAltura.getText().toString();
        if(peso.isEmpty()){
            editPeso.setError("Obrigatorio!");
            return false;
        }
        if(altura.isEmpty()){
            editAltura.setError("Obrigatorio!");
            return false;
        }
        if(!radioM.isChecked() && !radioF.isChecked()){
            Toast toast = Toast.makeText(this,"Selecione pelo menos um sexo!",Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return true;
    }
}