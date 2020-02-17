package net.ivanvega.miclientecpcontactosb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InsertFragment extends Fragment {
    private View vista;
    Button boton;

    EditText nombre,correo,telefono;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_insert,container,false);
        nombre = vista.findViewById(R.id.etNombre);
        correo = vista.findViewById(R.id.etCorreo);
        telefono = vista.findViewById(R.id.etTelefono);
        boton = vista.findViewById(R.id.btnAceptar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MainMethods methods = new MainMethods();
                    methods.insertC(nombre.getText().toString(),correo.getText().toString(),telefono.getText().toString());
                    limpiar();
                    Toast.makeText(vista.getContext(),"Contacto añadido",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(vista.getContext(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        return vista;
    }
    private void limpiar(){
        nombre.setText("");
        correo.setText("");
        telefono.setText("");
    }



}
