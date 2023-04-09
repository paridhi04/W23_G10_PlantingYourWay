package com.example.plantingyourway;

import android.os.Bundle;
//import android.se.omapi.Session;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Properties;
//import javax.mail.Message;
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
        editTextEmail = findViewById(R.id.editTxtEmail);
        editTextMessage = findViewById(R.id.editTxtMessage);

        Button submitBtn = findViewById(R.id.btnSumbit);
        submitBtn.setOnClickListener(v -> sendEmail());
    }

    private void sendEmail() {
        String recipientEmail = editTextEmail.getText().toString();
        String subject = "Contact Us Form Submission";
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

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

        try {
            MimeMessage messageToSend = new MimeMessage(session);
            messageToSend.setFrom(new InternetAddress("sender@gmail.com"));
//            messageToSend.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            messageToSend.setRecipient(RecipientType.TO, new InternetAddress(recipientEmail));
            messageToSend.setSubject(subject);
            messageToSend.setText("Name: " + name + "\n\nEmail: " + email + "\n\nMessage: " + message);

            Transport.send(messageToSend);

            Toast.makeText(this, "Email sent successfully!", Toast.LENGTH_SHORT).show();
            editTextName.setText("");
            editTextEmail.setText("");
            editTextMessage.setText("");
        } catch (MessagingException e) {
            Toast.makeText(this, "Failed to send email. Please try again later.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}