package ua.dashan.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TopLevelActivity extends Activity {
    //  Эти приватные переменные добавлены для того, чтобы объекты были доступны в методе onDestroy().
    private SQLiteDatabase db;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        //создаём onItemClickListener(обработчик нажатия на списке)
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView,//списковое представление
                                    View v, int position, long id) {//представление на котором сделан щелчок
                //если нажали на объект списка с позицией 2(список начинается с 0)
                if (position == 1) {
                    Intent intent = new Intent(TopLevelActivity.this, FoodCategoryActivity.class);
                    startActivity(intent);
                } else if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(TopLevelActivity.this, AvocationCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);//назначили слушателя для спискового представления
        //Заполнение спискового представления list_favorites данными курсора
        ListView listFavorites = (ListView) findViewById(R.id.list_favorites);
        try {
            SQLiteOpenHelper sqLiteOpenHelper = new StarBuzzDataBaseHelper(this);
            db = sqLiteOpenHelper.getReadableDatabase();
            //Cоздать курсор, содержащий значения столбцов _id и NAME для записей,у которых FAVORITE=1.
            cursor = db.query("Drink", new String[]{"_id", "NAME"}, "FAVORITE = 1", null, null, null, null);
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(TopLevelActivity.this, android.R.layout.simple_list_item_1,//макет для списка
                    cursor,//наш курсор
                    new String[]{"NAME"}//Вывести содержимое столбца NAME в текстовом представлении компонента ListView указав text1
                    , new int[]{android.R.id.text1}, 0//В параметре flags обычно передается 0 (значение по умолчанию)
            );
            listFavorites.setAdapter(favoriteAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        //Переход к DrinkActivity при выборе напитка из списка любимых напитков
        // Метод вызывается при выборе варианта спискового представления
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
               /* Если пользователь выбирает один из вариантов спискового представления
                любимых напитков, создать интент для запуска DrinkActivity и передать
                идентификатор напитка.*/
                intent.putExtra(DrinkActivity.EXTRA_DRINK, (int) id);
                startActivity(intent);

            }
        });
    }
//Закрытие курсора и базы данных в методе onDestroy()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
    //Метод вызывается при возвращении пользователя к TopLevelActivity,  данные в списковом представлении будут обновляться
    public void onRestart(){
        super.onRestart();
        try{
            StarBuzzDataBaseHelper starBuzzDataBaseHelper=new StarBuzzDataBaseHelper(this);
            db=starBuzzDataBaseHelper.getReadableDatabase();
            Cursor newCursor=db.query("DRINK",new String[]{"_id", "NAME"}, "FAVORITE = 1", null, null, null, null);
            ListView listFavorites=(ListView)findViewById(R.id.list_favorites);
            CursorAdapter adapter=(CursorAdapter)listFavorites.getAdapter();
           // Заменить курсор, используемый адаптером курсора, новым.
            adapter.changeCursor(newCursor);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }


    }
}
