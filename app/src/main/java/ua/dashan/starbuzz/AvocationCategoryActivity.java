package ua.dashan.starbuzz;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AvocationCategoryActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView=getListView();
        ArrayAdapter<Avocation>arrayAdapter=new ArrayAdapter<Avocation>(this,android.R.layout.simple_list_item_1,Avocation.avocations);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent=new Intent(AvocationCategoryActivity.this,AvocationActivity.class);
        intent.putExtra(AvocationActivity.EXTRA_AVOCATION,(int)id);
        startActivity(intent);
    }
}
