package com.example.aplicrestaurante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends MainMenu {
    private FirebaseAuth mAuth;
    FirebaseUser usr;
    AwesomeValidation awesomeValidation;
    Button inicio, registro, recuperar;
    EditText correo,contraseña;
    String direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("RestApp");
        String bb = "#E4F4C536";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bb)));

        cargarDireccion();

        mAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.correofinput, Patterns.EMAIL_ADDRESS,R.string.emailinvalido);
        awesomeValidation.addValidation(this, R.id.contraseñafinput,".{6,}",R.string.pass);

        inicio=findViewById(R.id.iniciarsesion);
        registro = findViewById(R.id.registrarse);
        recuperar = findViewById(R.id.olvide);
        correo = findViewById(R.id.correoelectronicoinput);
        contraseña = findViewById(R.id.contraseñainput);
    }


    public void onClickIniciar(View view) {
        if (correo.getText().length() != 0 && contraseña.getText().length() != 0) {
            if (awesomeValidation.validate()) {
                String mail = correo.getText().toString();
                String pass = contraseña.getText().toString();

                mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login correcto.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, CartaFirebase.class);
                            intent.putExtra("dir", direccion);
                            intent.putExtra("nombre", mAuth.getCurrentUser().getEmail().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            String error = ((FirebaseAuthException) task.getException()).getErrorCode();
                            dameToastdeerror(error);
                        }
                    }
                });

            } else {
                Toast.makeText(this, "Completa todos los datos..", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void onClickOlvide(View view) {
        Intent intent = new Intent(this, RecuperarContrasenya.class);
        startActivity(intent);
    }

    public void onClickRegistrar(View view) {
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
    }

    public void cargarDireccion(){

        Bundle extras = getIntent().getExtras();
        direccion =extras.getString("dir");
    }

    private void dameToastdeerror(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(Login.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(Login.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(Login.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(Login.this, "La dirección de correo electrónico o la contraseña está mal.", Toast.LENGTH_LONG).show();
                correo.setError("La dirección de correo electrónico está mal formateada.");
                correo.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(Login.this, "La dirección de correo electrónico o la contraseña está mal.", Toast.LENGTH_LONG).show();
                contraseña.setError("la contraseña es incorrecta ");
                contraseña.requestFocus();
                contraseña.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(Login.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(Login.this,"Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(Login.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(Login.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                correo.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                correo.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(Login.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(Login.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(Login.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(Login.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(Login.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(Login.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(Login.this, "La contraseña proporcionada no es válida..La dirección de correo electrónico o la contraseña está mal.", Toast.LENGTH_LONG).show();
                contraseña.setError("La dirección de correo electrónico o la contraseña está mal.");
                contraseña.requestFocus();
                break;
        }
    }
}