package com.example.arvore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "Conteudo.db";
    public final static String TABLE_NAME = "conteudo_table";
    public final String COL_1 = "ID";
    public final String COL_2 = "NOME";
    public final String COL_3 = "CONTEUDO";
    public final String COL_4 = "IDPAI";

    private List<No> raiz;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, CONTEUDO TEXT, IDPAI INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData (String nome, String conteudo, int idpai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, nome);
        cv.put(COL_3, conteudo);
        cv.put(COL_4, idpai);

        long id =  db.insert(TABLE_NAME, null, cv);

        Log.i("InsertDB", idpai+"");
        return id;

    }

    public void insertNos (List<No> nos) {
        for(int i = 0; i < nos.size(); i++) {
            insertData(nos.get(i).getNome(), nos.get(i).getConteudo(), nos.get(i).getIdPai());
            insertNos(nos.get(i).getNo());
        }
    }

    public Cursor getData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID=  " +id;

        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Boolean updateData (int id, String nome, String conteudo, int idPai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nome);
        contentValues.put(COL_3, conteudo);
        contentValues.put(COL_4, idPai);
        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id+""});
        return true;
    }

    public Integer deleteData (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {""+id});
    }

    public Cursor getAllData () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public List<No> convertCursorToNos (Cursor cursor) {
        if(cursor.getCount() <= 0) {
            return null;
        }

        raiz = new ArrayList<>();

        while(cursor.moveToNext()) {
            No no = new No (cursor.getInt(cursor.getColumnIndex(COL_1)),
                    cursor.getString(cursor.getColumnIndex(COL_2)),
                    cursor.getString(cursor.getColumnIndex(COL_3)),
                    cursor.getInt(cursor.getColumnIndex(COL_4)));
            Log.i("Cursor", no.getNome());
            inserir(raiz, no, no.getIdPai());
        }
        return this.raiz;
    }

    public void inserir (List<No> raizLocal, No noAdd, int id) {
        if(this.raiz.size() == 0){
            Log.i("Convert1", noAdd.getNome());
            this.raiz.add(noAdd);
            return;
        }

        if(id == -1 ){
            Log.i("Convert2", noAdd.getNome());
            this.raiz.add(noAdd);
            return;
        }

        for(int i = 0; i < raizLocal.size(); i++) {
            No no = raizLocal.get(i);
            if(no.getId() == id) {
                //no.get(id).getNo().add(noInsere)
                Log.i("Convert3", noAdd.getNome());
                raizLocal.get(i).getNo().add(noAdd);
                return;
            }
            inserir(no.getNo(), noAdd, id);
        }
    }
}
