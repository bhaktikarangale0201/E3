package com.example.exp_3;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

        EditText name = findViewById(R.id.personName);
        EditText pass = findViewById(R.id.password);
        EditText repass = findViewById(R.id.password2);
        Button reg = findViewById(R.id.button);
        DBHelper DB = new DBHelper(this);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String pass1 = pass.getText().toString();
                String repass1 = repass.getText().toString();
                if(pass1.equals(repass1)) {
                    Boolean checkuser = DB.checkusername(name1);

                    if(!checkuser) {
                        Boolean insert = DB.inserdata(name1,pass1);

                        if(insert) {
                            Toast.makeText(MainActivity.this,"Registration successful",Toast.LENGTH_SHORT).show();
                        }

                        else
                            Toast.makeText(MainActivity.this,"Registration unsuccessful",Toast.LENGTH_SHORT).show();
                    }

                    else Toast.makeText(MainActivity.this,"User already registered",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}