package ua.dashan.starbuzz;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FoodCategoryActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView=getListView();
        //this – текущая активность
        //android.R.layout.simple_list_item_1 – Встроенный ресурс макета. Он приказывает адаптеру массива отображать каждый элемент массива в виде надписи.
        //drinks – массив данных, которые мы хотим вывести в список
        ArrayAdapter<Food>lisAdapter=new ArrayAdapter<Food>(this,android.R.layout.simple_list_item_1, Food.food);

        //Затем адаптер массива связывается со списковым представлением при помощи метода setAdapter()
        listView.setAdapter(lisAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent=new Intent(FoodCategoryActivity.this,FoodActivity.class);
        intent.putExtra(FoodActivity.EXTRA_FOOD,(int)id);
        startActivity(intent);
    }
}
