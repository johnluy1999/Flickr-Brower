package vn.edu.usth.flickrbrower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {



    private EditText username;
    private EditText password;
    private TextView register;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        register = (TextView) findViewById(R.id.register);
        login    = (Button)   findViewById(R.id.loginbutton);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyen sang register frame
            }
        });
    }

    //----------------------------------------------------------------------------------------------
    private void validate (String username, String password){

        if ( (username.equals("admin")) && (password.equals("admin"))){
            //chuyen sang main acctivity
        }
    }

}
