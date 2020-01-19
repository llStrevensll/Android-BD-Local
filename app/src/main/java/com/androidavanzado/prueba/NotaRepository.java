package com.androidavanzado.prueba;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.androidavanzado.prueba.db.NotaRoomDatabase;
import com.androidavanzado.prueba.db.dao.Notadao;
import com.androidavanzado.prueba.db.entity.NotaEntity;

import java.util.List;

public class NotaRepository {
    private Notadao notaDao;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> AllNotasFavoritas;

    //Constructor
    public NotaRepository(Application application){

        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);

        notaDao = db.notaDao();
        allNotas = notaDao.getAll();
        AllNotasFavoritas = notaDao.getAllFavoritas();
    }

    //Declarar operaciones que se pueden hacer al repositorio
    public LiveData<List<NotaEntity>> getAll() { return allNotas;}

    public LiveData<List<NotaEntity>> getAllFavs() { return AllNotasFavoritas;}

    public void insert (NotaEntity nota){//Insert se debe realizar de manera asincrona
        new insertAsyncTask(notaDao).execute(nota);//execute para segundo plano
    }

    //AsyncTask- Tarea Asincrona
    private static class insertAsyncTask extends AsyncTask<NotaEntity, Void, Void>{

        private Notadao notaDatoAsyncTask;

        //constructor de insertAsyncTask
        insertAsyncTask(Notadao dao){
            notaDatoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {//Ejecución en segundo plano, recibe un array de parametros

            notaDatoAsyncTask.insert(notaEntities[0]);//insercion del primer parametro
            return null;
        }
    }

    //Actualizar nota
    public void update (NotaEntity nota) { new updateAsyncTask(notaDao).execute(nota);}

    private static class updateAsyncTask extends AsyncTask<NotaEntity, Void, Void>{

        private Notadao notaDatoAsyncTask;

        //constructor de insertAsyncTask
        updateAsyncTask(Notadao dao){
            notaDatoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {//Ejecución en segundo plano, recibe un array de parametros

            notaDatoAsyncTask.update(notaEntities[0]);//insercion del primer parametro
            return null;
        }
    }
}
