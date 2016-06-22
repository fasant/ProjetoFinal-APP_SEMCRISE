package com.edu.ifsp.fabio.semcrise.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.edu.ifsp.fabio.semcrise.R;
import com.edu.ifsp.fabio.semcrise.presenter.ListarPostosPresenter;


public class ListarPostosFragment extends Fragment {

    public ListarPostosFragment() {
    }

    private ListView mList;
    private int ordem;

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listar_postos, container, false);

        mList = (ListView) view.findViewById(R.id.listView);
        ListarPostosPresenter listarPostosPresenter = new ListarPostosPresenter();

        listarPostosPresenter.listarPostos(getContext(), mList, getOrdem());

        return view;
    }
}
