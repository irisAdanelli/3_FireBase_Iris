package mx.edu.ittepic.a3_firebase_iris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaAlumnosActivity extends AppCompatActivity {

    RecyclerView rv;
    List<Alumno> alumnos;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumnos);

        setContentView(R.layout.activity_lista_alumnos);
        rv=findViewById(R.id.recy_lista);

        rv.setLayoutManager(new LinearLayoutManager(this));

        alumnos=new ArrayList<>();

        FirebaseDatabase database= FirebaseDatabase.getInstance();

        adapter=new Adapter(alumnos);
        rv.setAdapter(adapter);

        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alumnos.removeAll(alumnos);
                for(DataSnapshot snapshot:
                        dataSnapshot.getChildren()){
                    Alumno alumno=snapshot.getValue(Alumno.class);
                    alumnos.add(alumno);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

