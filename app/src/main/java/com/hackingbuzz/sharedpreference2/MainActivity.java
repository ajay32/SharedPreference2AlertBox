package com.hackingbuzz.sharedpreference2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TextView yourLanguage;


    public void getAlertBox() {

        new AlertDialog.Builder(this).setIcon(R.drawable.fb).setTitle("Are you sure?")
                .setMessage("which is your language ?")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferences.edit().putString("Language", "ENGLISH").apply();   // dont forget to apply

                        //here you cant set set to text view if you are not getting..
                    }
                }).setNegativeButton("Hindi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                preferences.edit().putString("Language", "HINDI").apply();

// here it will print it to the screen
                String lang = preferences.getString("Language", "");
                yourLanguage.setText("Your Language is :" + lang);
            }
        }).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences("com.hackingbuzz.sharedpreference2", Context.MODE_PRIVATE);

        yourLanguage = (TextView) findViewById(R.id.tv_language);

// we haven't put anything in our helper but we trying to get language..so,,we want this for new users to our app
// we know they havent set annything so we want to show then alert dialog box as they get into our app..so that they could choose a language ..n then its gonna store peremently..so
        // second time they arrive this condition will be false..n we will show users what language they chose last time.

        final String chooseLanguage = preferences.getString("Language", "");

        if (chooseLanguage == "") {

          getAlertBox();

        } else {

            // if you come to app second time it will print it to screen coz if condition will not execute n we getting from sharedPrefernce before than in chooseLanguage
            yourLanguage.setText("Your Language is :" + chooseLanguage);

        }


    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.change_language) {
            getAlertBox();
        }


        return super.onOptionsItemSelected(item);
    }



}
