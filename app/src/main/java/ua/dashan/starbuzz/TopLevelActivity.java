package ua.dashan.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopLevelActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        //создаём onItemClickListener(обработчик нажатия на списке)
        AdapterView.OnItemClickListener itemClickListener =new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?>listView,//списковое представление
                                    View v,int position,long id){//представление на котором сделан щелчок
                //если нажали на объект списка с позицией 2(список начинается с 0)
                if (position==1){
                    Intent intent=new Intent(TopLevelActivity.this,FoodCategoryActivity.class);
                    startActivity(intent);
                }
                else if(position==0){
                    Intent intent=new Intent(TopLevelActivity.this,DrinkCategoryActivity.class);
                    startActivity(intent);
                }
                else if(position==2){
                    Intent intent=new Intent(TopLevelActivity.this,AvocationCategoryActivity.class);
                    startActivity(intent);
                }
            }
        };
        ListView listView=(ListView)findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);//назначили слушателя для спискового представления
    }
}
