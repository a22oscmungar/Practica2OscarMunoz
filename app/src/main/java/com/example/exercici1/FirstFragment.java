package com.example.exercici1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.exercici1.databinding.FragmentFirstBinding;

import org.w3c.dom.Text;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView tvUsu;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUsu = view.findViewById(R.id.tvBienvenido);

        Bundle args = getArguments();

        if (args != null) {
            // Obtener el nombre de usuario de los argumentos
            String usuario = args.getString("usuario");

            // Configurar el mensaje de bienvenida con el nombre de usuario
            String mensajeBienvenida = "Bienvenido, " + usuario;
            tvUsu.setText(mensajeBienvenida);
        }

    /*
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        }); */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}