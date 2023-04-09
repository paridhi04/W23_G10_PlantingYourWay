package com.example.plantingyourway;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;

public class ContactUsActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMessage = findViewById(R.id.editTxtMessage);

        Button submitBtn = findViewById(R.id.btnSumbit);
        submitBtn.setOnClickListener(v -> new SendEmailAsyncTask().execute());
    }

    private class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private String name, email, message;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            name = editTextName.getText().toString().trim();
            email = editTextEmail.getText().toString().trim();
            message = editTextMessage.getText().toString().trim();

            Log.e("onPreExecute: email id", email);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication("plantingyourwayg10@gmail.com", "mobile@123");
                    }
                });

                MimeMessage messageToSend = new MimeMessage(session);
                messageToSend.setFrom(new InternetAddress("plantingyourwayg10@gmail.com"));
                messageToSend.setRecipient(RecipientType.TO, new InternetAddress(email));
                messageToSend.setSubject("Contact Us Form Submission");
                messageToSend.setText("Name: " + name + "\n\nEmail: " + email + "\n\nMessage: " + message);

                Transport.send(messageToSend);

                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                Toast.makeText(ContactUsActivity.this, "Email sent successfully!", Toast.LENGTH_SHORT).show();
                editTextName.setText("");
                editTextEmail.setText("");
                editTextMessage.setText("");
            } else {
                Toast.makeText(ContactUsActivity.this, "Failed to send email. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
