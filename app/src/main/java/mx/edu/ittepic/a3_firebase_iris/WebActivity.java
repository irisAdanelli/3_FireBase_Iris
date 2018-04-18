package mx.edu.ittepic.a3_firebase_iris;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WebActivity extends AppCompatActivity {

    public static final  String user="names";

    TextView txtUser;
    DatabaseReference reference;
    Button add_alumno;
    EditText ctrl_alumno;
    EditText nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello User", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        txtUser = findViewById(R.id.welcome);

        String user = getIntent().getStringExtra("names");

        txtUser.setText("!Bienvenido   "+user+"  !");


        add_alumno=findViewById(R.id.btn_add_alumno);
        ctrl_alumno=findViewById(R.id.et_nocrtl_alumno);
        nom=findViewById(R.id.et_nombre_alumno);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference(FirebaseReferences.TUTORIAL_REFERENCE);


    }

    public void onClickAddAlumno(View view){
        Alumno alumno =new Alumno(Integer.parseInt(ctrl_alumno.getText().toString()),
                nom.getText().toString());
        reference.child(FirebaseReferences.ALUMNO_REFERENCE).push().setValue(alumno);
    }

    public void onClickListarAlumno(View view){
        Intent intent = new Intent(this,ListaAlumnosActivity.class);
        startActivity(intent);
    }

}
