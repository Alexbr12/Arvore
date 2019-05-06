package com.example.arvore.ui.conteudo;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arvore.R;

public class ConteudoFragment extends Fragment {



    private ConteudoViewModel mViewModel;
    private TextView nome;
    private TextView conteudo;

    public static ConteudoFragment newInstance() {
        return new ConteudoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.conteudo_fragment, container, false);

        nome = view.findViewById(R.id.nome);
        conteudo = view.findViewById(R.id.message);

        nome.setText(getArguments().getString("nome"));
        conteudo.setText(getArguments().getString("conteudo"));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConteudoViewModel.class);
        // TODO: Use the ViewModel

    }

}
