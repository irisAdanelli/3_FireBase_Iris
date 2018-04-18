package mx.edu.ittepic.a3_firebase_iris;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mail,password;
    private Button reg,log;
    private ProgressDialog progres;
    private FirebaseAuth fireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciar el objeto
        fireBase = FirebaseAuth.getInstance();

        mail = findViewById(R.id.editMail);
        password = findViewById(R.id.editPassword);

        reg = findViewById(R.id.btnReg);
        log = findViewById(R.id.btnLogin);

        progres = new ProgressDialog(this);

        reg.setOnClickListener(this);
        log.setOnClickListener(this);
    }


    private void registrarUsuario(){
        String themail = mail.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(themail)){
            Toast.makeText(this, "Ingresa un MAIL", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Ingresa una Contrasenia", Toast.LENGTH_SHORT).show();
            return;
        }

        progres.setMessage("Realizando Registro en Linea");
        progres.show();

        //crear un usuario
        fireBase.createUserWithEmailAndPassword(themail,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Se guardo mail y usuario", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(task.getException() instanceof FirebaseAuthUserCollisionException) {

                        Toast.makeText(MainActivity.this, "Ya existe el usuario", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(MainActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();

                    }
                }
                progres.dismiss();
            }
        });

    }

    private void loginUsuario(){
        final String themail = mail.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(themail)){
            Toast.makeText(this, "Ingresa un MAIL", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Ingresa una Contrasenia", Toast.LENGTH_SHORT).show();
            return;
        }

        progres.setMessage("Realizando Registro en Linea");
        progres.show();

        //loging un usuario
        fireBase.signInWithEmailAndPassword(themail,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent irWelcom = new Intent(getApplication(),WebActivity.class);
                            irWelcom.putExtra(themail, pass);
                            startActivity(irWelcom);
                        }
                        else{

                            if(task.getException() instanceof FirebaseAuthUserCollisionException) {

                                Toast.makeText(MainActivity.this, "Ya existe el usuario", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(MainActivity.this, "No se pudo login el usuario", Toast.LENGTH_SHORT).show();

                            }
                        }
                        progres.dismiss();
                    }
                });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnReg:
                registrarUsuario();
                break;

            case R.id.btnLogin:
                loginUsuario();
                break;
        }
    }
}
