package com.software.program.astrixsa.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Belal on 10/30/2015.
 */

//Class is extending AsyncTask because this class is going to perform a networking operation
public class SendMail extends AsyncTask<Void,Void,Void> {

    //Declaring Variables
    private Context context;
    private Session session;
    //Information to send email
    private String nombreRemitente;
    private String mailRemitente;
    private String telefonoRemitente;
    private String mensajeRemitente;
    private String subject;
    private boolean successSend = false;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public SendMail(AppCompatActivity activity, Context context, String nombreRemitente, String emailRemitente, String telefonoRemitente, String mensajeRemitente){
        //Initializing variables
        this.context = context;
        this.nombreRemitente = nombreRemitente;
        this.mailRemitente = emailRemitente;
        this.telefonoRemitente = telefonoRemitente;
        this.mensajeRemitente = mensajeRemitente;
        this.subject = "Comentario Aplicacion OLA: Investigacion y desarrollo";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context,"Enviando sugerencia","Por favor, espere...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        if(successSend){
            Toast.makeText(context,"Sugerencia enviada",Toast.LENGTH_LONG).show();
            successSend = false;
            return;
        }
        Toast.makeText(context,"Error en el envio",Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creating properties
        Properties props = new Properties();

        //Configuring properties for gmail
        //If you are not using gmail you may need to change the values
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage mm = new MimeMessage(session);

            //Setting sender address
            mm.setFrom(new InternetAddress(Config.EMAIL));//ola.cid.astrix@gmail.com
            //Adding receiver
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress("ola.cid.astrix@gmail.com"));
            //Adding subject
            mm.setSubject("Nuevo comentario: OLA Investigacion y Desarrollo");
            //Adding message
            mm.setText(generarCuerpoEmail());
            //Sending email
            Transport.send(mm);
            successSend = true;

        } catch (MessagingException e) {
            successSend = false;
            //Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return null;
    }
    private String generarCuerpoEmail(){
        String cuerpo = "Nombre: " + nombreRemitente+"\n";
        if(telefonoRemitente != null && !telefonoRemitente.equals("")){
            cuerpo += "Telefono: "+telefonoRemitente+"\n";
        }
        if(mailRemitente != null && !mailRemitente.equals("")){
            cuerpo += "Correo electronico: "+mailRemitente+"\n";
        }
        cuerpo +=  "Comentario: "+mensajeRemitente;

        return cuerpo;
    }
}