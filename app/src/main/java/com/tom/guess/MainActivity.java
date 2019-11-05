package com.tom.guess;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();
    private EditText number;
    private int num;
    int counter;
    private TextView edCounter;
    int secret = new Random().nextInt(10)+1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        number = findViewById(R.id.number);
        edCounter = findViewById(R.id.counter);
        edCounter.setText(counter+ "");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                    reset();
                }
            });
        reset();
        num  = number.getText().length();
        Log.d(TAG, "seceret " + secret);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                Log.d(TAG, "secret"+secret);
            }
        });
    }
    public void guess(View view){
        int num =Integer.parseInt(number.getText().toString());
        counter++;
        edCounter.setText(counter+"");
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset();
                Log.d(TAG, "secret"+secret);
            }
        };
        number.setText(Integer.toString(num));
        String message = "So good!";
        if (num < secret){
            message = "Bigger";
            listener = null;
            //Toast.makeText(MainActivity.this, "Higher", Toast.LENGTH_SHORT).show();
           /* new AlertDialog.Builder(MainActivity.this)
                    .setTitle("hahaha")
                    .setMessage("bigger")
                    .setPositiveButton("OK",null)
                    .show();*/

        } else if (num > secret){
            message = "Smaller";
            listener = null;
            //Toast.makeText(MainActivity.this, "Lower", Toast.LENGTH_SHORT).show();
            /*new AlertDialog.Builder(MainActivity.this)
                    .setTitle("hahaha")
                    .setMessage("lower")
                    .setPositiveButton("OK",null)
                    .show();*/

        } else {
            //Toast.makeText(MainActivity.this, "you are right!", Toast.LENGTH_SHORT).show();

        }
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("ok", listener)
                .show();

    }

    private void reset() {
        secret = new Random().nextInt(10)+1;
        counter=0;
        edCounter.setText(counter+"");
        number.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }
}
