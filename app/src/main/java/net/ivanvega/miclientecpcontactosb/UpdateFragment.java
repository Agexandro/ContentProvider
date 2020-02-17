package net.ivanvega.miclientecpcontactosb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;


public class UpdateFragment extends Fragment {
    private View vista;
    private ListView lv;
    private Context context;
    private ArrayList<String> listaNombre;
    private  String datoNuevo="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista =  inflater.inflate(R.layout.fragment_update,container,false);
        context = MainActivity.getContext();
        lv = vista.findViewById(R.id.lv);
        llenar();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog("name",listaNombre.get(position));
            }
        });
        return  vista;
    }

    public void llenar(){
        listaNombre=new ArrayList<>();
        Cursor c = context.getContentResolver().query
                (   Uri.withAppendedPath(ContractCPContactos.CONTENT_URI, "")  ,
                        ContractCPContactos.PROJECTION,
                        null, null,null);

        SimpleCursorAdapter adp =
                new SimpleCursorAdapter(context,
                        android.R.layout.simple_list_item_2,
                        c, new String[]{
                        ContractCPContactos.FIELD_USUARIO,
                        ContractCPContactos.FIELD_EMAIL
                },new int[]{android.R.id.text1,
                        android.R.id.text2}
                        ,SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
                );
        while (c.moveToNext()){
            listaNombre.add(c.getString(c.getColumnIndex(ContractCPContactos.FIELD_USUARIO)));
        }
        lv.setAdapter(adp);
    }

    public void dialog(String titulo, final String id){
        final MainMethods methods = new MainMethods();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(titulo);

// Set up the input
        final EditText input = new EditText(getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                datoNuevo = input.getText().toString();
                methods.update(id,datoNuevo);
                llenar();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


}
