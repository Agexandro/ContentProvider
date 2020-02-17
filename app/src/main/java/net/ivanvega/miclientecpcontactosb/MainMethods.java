package net.ivanvega.miclientecpcontactosb;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

public class MainMethods {
private Context context;

    public void update(String id,String nombre){
        context = MainActivity.getContext();
        //Se define un objeto que contenga los valores de actualizacion
        ContentValues updateValues = new ContentValues();
        //Se definen los criterios de seleccion de las filas a actualizar
        String selectionClause = ContractCPContactos.FIELD_USUARIO+"=?";
        String selectionArgs [] = {id};

        //Se define una variable que contenga el numero de actualizados
        int rowsUpdated = 0;

        //obtiene el valor actualizado  y actualiza la palabra seleccionada
        updateValues.put(ContractCPContactos.FIELD_USUARIO,nombre);;
        rowsUpdated = context.getContentResolver().update(ContractCPContactos.CONTENT_URI,
                updateValues,selectionClause,selectionArgs);
        Toast.makeText(context,rowsUpdated+" Mira",Toast.LENGTH_SHORT).show();

    }

    public void delete(String usuario){
        context = MainActivity.getContext();
        //Se definen los criterios de seleccion de las filas a actualizar
        String selectionClause = ContractCPContactos.FIELD_USUARIO+"=?";
        String selectionArgs [] = {usuario};

        context.getContentResolver().delete(ContractCPContactos.CONTENT_URI,
                selectionClause,selectionArgs);
        Toast.makeText(context,"Eliminado",Toast.LENGTH_SHORT).show();
    }

    public void insertC(String nombre, String correo, String telefono){
        context = MainActivity.getContext();
        //Contenedor para los nuevos valores
        ContentValues contentValues = new ContentValues();

        //Establece los valores que se van a insertar en la bd
        contentValues.put(ContractCPContactos.FIELD_USUARIO, nombre);
        contentValues.put(ContractCPContactos.FIELD_EMAIL, correo);
        contentValues.put(ContractCPContactos.FIELD_TEL, telefono);

        Uri uri = context.getContentResolver().insert(ContractCPContactos.CONTENT_URI,
                contentValues
        );
    }


}
