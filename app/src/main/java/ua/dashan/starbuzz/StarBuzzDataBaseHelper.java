package ua.dashan.starbuzz;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Помощники SQLite должны расширять класс SQLiteOpenHelper
public class StarBuzzDataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "starbuzz"; // Имя базы данных
    private static final int DB_VERSION = 2; // Версия базы данных

    StarBuzzDataBaseHelper(Context context) {
        //Вызываем конструктор супер-класса SQLiteOpenHelper и передаем ему имя и версию базы данных
        //null- параметр используется для работы с курсорами
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
   /* Метод onCreate() вызывается при создании базы данных;
    мы используем его для создания таблицы и вставки данных.*/
    public void onCreate(SQLiteDatabase db) {
updateMyDatabase(db,0,DB_VERSION);

    }

    @Override
   /* Метод onUpgrade() вызывается тогда, когда воз-
    никает необходимость в обновлении базы данных.*/
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
updateMyDatabase(db, oldVersion, newVersion);
    }
    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<1){
            //Создать таблицу DRINK
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +"NAME TEXT, "+"DESCRIPTION TEXT, "+"IMAGE_RESOURCE_ID INTEGER);");
            //вставляем данные каждого напитка в отдельную строку
            insertDrink(db,"Coca-cola","Это вкусное, сытное, а также полезное блюдо, сделанное из свежих ингредиентов",R.drawable.kokakola);
            insertDrink(db, "Вода «Моршинская» без газа","Это вода с Днепра в фирменной упаковке",R.drawable.morshin_bez_gaza);
            insertDrink(db,"Сок «Rich» вишневый","Отличный сок",R.drawable.sok);


        }
        if(oldVersion<2){
            //Добавить числовой столбец FAVORITE в таблицу DRINK.
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }
   // Так как нужно добавить несколько напитков, мы создаем отдельный метод.
    private static void insertDrink(SQLiteDatabase db,String name,String description, int resourceId){
        /*для определения набора значений создается объект
        ContentValues для хранения пар «имя/значение» данных*/
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME",name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK", null, drinkValues);

    }
}
