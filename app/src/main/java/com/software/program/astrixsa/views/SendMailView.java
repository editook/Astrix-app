package com.software.program.astrixsa.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.software.program.astrixsa.R;

public class SendMailView extends AppCompatActivity implements View.OnClickListener {

    private Button botonEnviar;

    private EditText nombre;
    private EditText telefono;
    private EditText email;
    private EditText mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_mail);
        botonEnviar = (Button) findViewById(R.id.botonEnviar);
        nombre = (EditText) findViewById(R.id.nombre);
        telefono = (EditText) findViewById(R.id.telefono);
        email = (EditText) findViewById(R.id.email);
        mensaje = (EditText) findViewById(R.id.mensaje);
        botonEnviar.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void sendEmail() {
        //Getting content for email
        String nombreRemitente = nombre.getText().toString().trim();
        //String email = "davorthebest@gmail.com";
        String emailRemitente = email.getText().toString().trim();
        //String subject = "Hola mundo";
        String telefonoRemitente = telefono.getText().toString().trim();
        //String message = "Comoestas";
        String mensajeRemitente = mensaje.getText().toString().trim();
        nombre.setText("");
        email.setText("");
        telefono.setText("");
        mensaje.setText("");
        //Creating SendMailView object
        //Executing sendmail to send email
        SendMail sm = new SendMail(this,this, nombreRemitente, emailRemitente, telefonoRemitente, mensajeRemitente);
        if(nombreRemitente == null || nombreRemitente.equals("") ){
            Toast.makeText(this,"Por favor, introduzca un nombre",Toast.LENGTH_LONG).show();
            return;
        }
        if(mensajeRemitente== null || mensajeRemitente.trim().equals("") ){
            Toast.makeText(this,"Por favor, introduzca un mensaje",Toast.LENGTH_LONG).show();
            return;
        }
        sm.execute();

    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }
}
