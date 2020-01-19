package com.androidavanzado.prueba;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.androidavanzado.prueba.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {

    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;

    public NuevaNotaDialogViewModel(Application application){//Application->contexto
        super(application);

        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getAll();

    }

    //Consulta
    //El fragment que necesita recibir la nueva lista de datos
    public LiveData<List<NotaEntity>> getAllNotas() { return allNotas; }

    //Inserta
    //El fragment que inserte una nueva nota, deberá comunicarlo a este ViewModel
    public void  insertarNota(NotaEntity nuevaNotaEntity) { notaRepository.insert(nuevaNotaEntity);}

    //Actualiza
    public void updateNota(NotaEntity notaActualizarEntity){
        notaRepository.update(notaActualizarEntity);
    }

}
