package ua.dashan.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class AvocationActivity extends Activity {
    public final static String EXTRA_AVOCATION="avocationNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avocation);
        int avocationNo=(Integer)getIntent().getExtras().get(EXTRA_AVOCATION);
        Avocation avocation=Avocation.avocations[avocationNo];
        ImageView imageView=(ImageView)findViewById(R.id.photo);
        imageView.setImageResource(avocation.getImageResourceId());
        imageView.setContentDescription(avocation.getName());
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(avocation.getName());
        //Заполнение описания напитка
        TextView description = (TextView)findViewById(R.id.description);
        description.setText(avocation.getDescription());
        //цена
        TextView cost=(TextView)findViewById(R.id.cost);
        cost.setText("Цена: "+avocation.getCost()+"грн");
    }
}