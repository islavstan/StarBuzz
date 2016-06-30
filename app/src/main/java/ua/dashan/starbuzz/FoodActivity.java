package ua.dashan.starbuzz;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends Activity{
    public final static String EXTRA_FOOD ="drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        //Получить напиток из данных интента
        int drinkNo=(Integer)getIntent().getExtras().get(EXTRA_FOOD);
        //Использовать drinkNo для получения подробной информации о напитке, выбранном пользователем
        Food food = Food.food[drinkNo];
        //Заполнение изображения напитка
        ImageView photo=(ImageView)findViewById(R.id.photo);
        photo.setImageResource(food.getImageResorceId());
        photo.setContentDescription(food.getName());
        //Заполнение названия напитка
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(food.getName());
        //Заполнение описания напитка
        TextView description = (TextView)findViewById(R.id.description);
        description.setText(food.getDescription());
        //цена
        TextView cost=(TextView)findViewById(R.id.cost);
        cost.setText("Цена: "+food.getCost()+"грн");
    }
}
