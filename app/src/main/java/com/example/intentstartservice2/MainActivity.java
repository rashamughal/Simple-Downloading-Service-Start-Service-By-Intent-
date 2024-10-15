package com.example.intentstartservice2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText editText=findViewById(R.id.editTextText);


        Button startService=findViewById(R.id.startServiceButton);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ServiceIntent =new Intent(MainActivity.this, MyService1.class);
                String Url=editText.getText().toString();
                // Optionally pass some data to the service
                ServiceIntent.putExtra("url", Url);
                // Start the service with the URL
                startService(ServiceIntent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}