package com.edu.ifsp.fabio.semcrise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.ifsp.fabio.semcrise.domain.Posto;
import com.edu.ifsp.fabio.semcrise.R;

import java.util.List;


public class PostoAdapter extends BaseAdapter {
    private final Context context;
    private final List<Posto> listPosto;

    public PostoAdapter(Context context, List listPosto) {
        this.context = context;
        this.listPosto = listPosto;
    }

    @Override
    public int getCount() {
        return listPosto != null ? listPosto.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listPosto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listPosto.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_posto, parent, false);

        TextView tNome = (TextView) view.findViewById(R.id.tNome);
        TextView tLocal = (TextView) view.findViewById(R.id.tLocal);
        TextView tPreco = (TextView) view.findViewById(R.id.tPreco);

        Posto posto = listPosto.get(position);
        tNome.setText(tNome.getText().toString() + posto.getNome());
        tLocal.setText(tLocal.getText().toString() + posto.getLocal());
        tPreco.setText(tPreco.getText().toString() + posto.getPreco().toString());

        return view;
    }
}
