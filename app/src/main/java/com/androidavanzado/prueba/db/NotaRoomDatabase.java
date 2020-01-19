package com.androidavanzado.prueba.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androidavanzado.prueba.db.dao.Notadao;
import com.androidavanzado.prueba.db.entity.NotaEntity;

@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {

    public abstract Notadao notaDao();
    private static volatile NotaRoomDatabase INSTANCE;    //Variable para saber si existe una instancia

    //Metodo para obtener la instancia de la Base de Datos
    public static NotaRoomDatabase getDatabase(final Context context){//Como es llamado desde diferentes partes del codigo, se necesita como parametro el contexto
        if (INSTANCE == null){
            synchronized (NotaRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaRoomDatabase.class, "notas_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
