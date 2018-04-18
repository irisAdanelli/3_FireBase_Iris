package mx.edu.ittepic.a3_firebase_iris;

/**
 * Created by OEM on 18/04/2018.
 */

public class Alumno {
    int noctrl;
    String nombre;

    public Alumno(){

    }

    public Alumno(int noctrl,String nombre){
        this.noctrl=noctrl;
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public int getNoctrl(){
        return noctrl;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setNoctrl(int noctrl){
        this.noctrl=noctrl;
    }
}
