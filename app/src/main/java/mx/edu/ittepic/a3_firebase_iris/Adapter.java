package mx.edu.ittepic.a3_firebase_iris;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by OEM on 18/04/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.AlumnosViewHolder>
{
    List<Alumno> alumnos;

    public Adapter(List alumnos){
        this.alumnos=alumnos;
    }

    @Override
    public AlumnosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);
        AlumnosViewHolder holder=new AlumnosViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlumnosViewHolder holder, int position) {
        Alumno alumno= alumnos.get(position);
        holder.tv_noctrl.setText(alumno.getNoctrl());
        holder.tv_nombre.setText(alumno.getNombre());
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public static class AlumnosViewHolder extends RecyclerView.ViewHolder{
        TextView tv_noctrl,tv_nombre;
        public AlumnosViewHolder(View itemView) {
            super(itemView);
            tv_noctrl=itemView.findViewById(R.id.tv_noctrl);
            tv_nombre=itemView.findViewById(R.id.tv_nombre);
        }
    }
}
