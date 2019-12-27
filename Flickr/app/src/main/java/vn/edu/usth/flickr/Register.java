package vn.edu.usth.flickr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private EditText username_register,lastname_register,age_register,emailadress_register,password_register;
    private TextView wrap;
    private Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //
        username_register = (EditText) findViewById(R.id.username_register);
        lastname_register = (EditText) findViewById(R.id.lastname_register);
        age_register = (EditText) findViewById(R.id.age_register);
        emailadress_register = (EditText) findViewById(R.id.emailadress_register);
        password_register = (EditText) findViewById(R.id.password_register);
        wrap = (TextView) findViewById(R.id.wrap);
        sign_up = (Button) findViewById(R.id.sign_up);


        username_register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                username_register.setText("");
                return false;
            }
        });
        password_register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                password_register.setText("");
                return false;
            }
        });
        lastname_register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                lastname_register.setText("");
                return false;
            }
        });
        age_register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                age_register.setText("");
                return false;
            }
        });
        emailadress_register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                emailadress_register.setText("");
                return false;
            }
        });

        wrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });



    }
}
