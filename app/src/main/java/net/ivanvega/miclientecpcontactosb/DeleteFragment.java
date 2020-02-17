package net.ivanvega.miclientecpcontactosb;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteFragment extends Fragment {
    private View vista;
    private ListView lv;
    private Context context;
    ArrayList<String> lista;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista =  inflater.inflate(R.layout.fragment_delete,container,false);
        context = MainActivity.getContext();
        lv = vista.findViewById(R.id.lv);
        llenar();

        //permite dar click a un elemento del list view para obtener el id y eliminarlo
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MainMethods methods=new MainMethods();
            methods.delete(lista.get(position));
            llenar();
            }
        });

        return  vista;
    }

    //Llena el list view del fragment con los contactos
    public void llenar(){
        lista=new ArrayList<>();
        Cursor c = context.getContentResolver().query
                (   Uri.withAppendedPath(ContractCPContactos.CONTENT_URI, "")  ,
                        ContractCPContactos.PROJECTION,
                        null, null,null);

        SimpleCursorAdapter adp =
                new SimpleCursorAdapter(context,
                        android.R.layout.simple_list_item_2,c
                        , new String[]{
                        ContractCPContactos.FIELD_USUARIO,
                        ContractCPContactos.FIELD_EMAIL
                },new int[]{android.R.id.text1,
                        android.R.id.text2}
                        ,SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
                );
        while (c.moveToNext()){
            lista.add(c.getString(c.getColumnIndex(ContractCPContactos.FIELD_USUARIO)));
        }

        lv.setAdapter(adp);
    }

}
