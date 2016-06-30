package ua.dashan.starbuzz;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class DrinkCategoryActivity extends ListActivity {
    /*Эти приватные переменные добавляются для того, чтобы базу данных и курсор можно
    было закрыть в методе onDestroy().*/
    private SQLiteDatabase db;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView=getListView();
try {
    //Получить ссылку на базу данных с помощью SQLiteOpenHelper
    SQLiteOpenHelper starBuzzDatabaseHelper = new StarBuzzDataBaseHelper(this);
    //Информация читается из базы данных, поэтому хватит доступа только для чтения.
    db = starBuzzDatabaseHelper.getReadableDatabase();//Получить ссылку на базу данных
    //создаём курсор
    // Курсор должен включать столбец _id —это необходимо для работы адаптера.Он также должен включать столбец NAME
    //для вывода списка названий напитков
    cursor = db.query("DRINK", new String[]{"_id", "NAME"}, null, null, null, null, null);
    CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"}/*Вывести содержимое
            столбца NAME*/, new int[]{android.R.id.text1}, 0);
    listView.setAdapter(listAdapter);

}catch (SQLiteException e){
    //Вывести сообщение для пользователя, если будет выдано исключение SQLiteException.
    Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
    toast.show();
}

        //ArrayAdapter<Drink>listAdapter=new ArrayAdapter<Drink>(this,android.R.layout.simple_list_item_1,Drink.drinks);
        //listView.setAdapter(listAdapter);

    }

    @Override
    /*База данных и курсор закрываются в методе onDestroy() активности.
    Курсор остается открытым до того момента, когда он перестает быть
    нужным адаптеру.*/
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent=new Intent(DrinkCategoryActivity.this,DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINK,(int)id);
        startActivity(intent);
    }
}

