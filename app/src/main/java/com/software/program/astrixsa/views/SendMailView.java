package com.software.program.astrixsa.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        //Creating SendMailView object
        SendMail sm = new SendMail(this, nombreRemitente, emailRemitente, telefonoRemitente, mensajeRemitente);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }
}
