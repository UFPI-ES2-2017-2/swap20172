package ufpi.br.swap.DataSource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ufpi.br.swap.DataModel.DataModel;

/**
 * Created by negadoaiti on 12/11/17.
 */

public class DataSource extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public DataSource(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL(DataModel.criarTabelaAula());
        db.execSQL(DataModel.criarTabelaUsuario());
        db.execSQL(DataModel.criarTabelaConhecimento());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(db);
    }

    public void inserirUsuario(ContentValues values, String tabela){
        if(values.containsKey("email") && values.getAsString("email")!= null){
            String email = values.getAsString("email");
            db.update(tabela,values,"email= " + email,null);
        }
        else{
            db.insert(tabela,null,values);
        }
    }

    public Cursor recuperarUsuario (String tabela){
        Cursor retorno = db.rawQuery("SELECT * FROM " + tabela, null);
    }
}
