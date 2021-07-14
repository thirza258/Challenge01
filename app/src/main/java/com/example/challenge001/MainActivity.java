package com.example.challenge001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ImageView image;
    private Button pickImage, register;
    private TextView license, txtCountry, txtVName, txtVEmail,txtVPassword, txtVRePassword;
    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtRePassword;
    private TextView txtGender;
    private CheckBox cbAgree;
    private RadioButton rbMale, rbFemale, rbOther;
    private Spinner spCountry;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pick Image Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, spCountry.getItemAtPosition(position).toString() + " Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountry.setAdapter(adapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
                Toast.makeText(MainActivity.this, "Form Submitted", Toast.LENGTH_SHORT).show();
            }
        });

        rbMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "You are Male", Toast.LENGTH_SHORT).show();
            }
        });

        rbFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "You are Female", Toast.LENGTH_SHORT).show();
            }
        });

        rbOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "You are neither Male or Female", Toast.LENGTH_SHORT).show();
            }
        });

        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "You are agree to our license and agreement", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initViews() {

        Log.d(TAG, "initViews: Started");

        image = findViewById(R.id.image);
        pickImage = findViewById(R.id.pickImage);
        register = findViewById(R.id.register);
        license = findViewById(R.id.license);
        txtCountry = findViewById(R.id.txtCountry);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtRePassword = findViewById(R.id.txtRePassword);
        txtGender = findViewById(R.id.txtGender);
        cbAgree = findViewById(R.id.cbAgree);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rbOther = findViewById(R.id.rbOther);
        spCountry = findViewById(R.id.spCountry);
        txtVName = findViewById(R.id.txtVName);
        txtVEmail = findViewById(R.id.txtVEmail);
        txtVPassword = findViewById(R.id.txtVPassword);
        txtVRePassword = findViewById(R.id.txtVRePassword);
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");

        if(validateData()) {
            if(cbAgree.isChecked()) {
                showSnackBar();
            }
            else {
                Toast.makeText(this, "You need to agree to our license and agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");

        txtVName.setVisibility(View.GONE);
        txtVRePassword.setVisibility(View.GONE);
        txtVPassword.setVisibility(View.GONE);
        txtVEmail.setVisibility(View.GONE);

        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String country = spCountry.getSelectedItem().toString();
        String gender = "";

        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Country: " + country + "\n";

        Log.d(TAG, "showSnackBar: Snack Bar Text: " + snackText);

        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dissmiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtName.setText("");
                        txtEmail.setText("");
                        txtPassword.setText("");
                        txtRePassword.setText("");
                    }
                }).show();
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");

        if(txtName.getText().toString().equals("")) {
            txtVName.setVisibility(View.VISIBLE);
            txtVName.setText("Enter your Name");
            return false;
        }

        if(txtEmail.getText().toString().equals("")) {
            txtVEmail.setVisibility(View.VISIBLE);
            txtVEmail.setText("Enter your Email");
            return false;
        }

        if(txtPassword.getText().toString().equals("")) {
            txtVPassword.setVisibility(View.VISIBLE);
            txtVPassword.setText("Enter your Password");
            return false;
        }

        if(txtRePassword.getText().toString().equals("")) {
            txtVRePassword.setVisibility(View.VISIBLE);
            txtVRePassword.setText("Re-Enter your Password");
            return false;
        }

        if(txtPassword.getText().toString().equals(txtVRePassword.getText().toString())) {
            txtVRePassword.setVisibility(View.VISIBLE);
            txtVRePassword.setText("Password doesn't match");
            return false;
        }

        return true;
    }
}