package fr.icodem.actionviewapp;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements TextView.OnEditorActionListener {

    private MenuItem searchActionMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);


        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchActionMenuItem = menuItem;

        final EditText searchEditText = (EditText) menuItem.getActionView().findViewById(R.id.search_src_text);
        searchEditText.setOnEditorActionListener(this);

        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                Log.d("MainActivity", "Selected menu item => " + "OnActionExpandListener.onMenuItemActionExpand()");

                searchEditText.requestFocus();
                searchEditText.setText("");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                Log.d("MainActivity", "Selected menu item => " + "OnActionExpandListener.onMenuItemActionCollapse()");

                return true;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MainActivity", "Selected menu item => " + item.getTitle());

        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;

            case R.id.action_search:
                Log.d("MainActivity", "RETURNED FALSE");
                return false;// if true is returned, the action view is not expanded
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (event != null) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                CharSequence textInput = v.getText();

                Toast.makeText(this, textInput, Toast.LENGTH_SHORT).show();
                MenuItemCompat.collapseActionView(searchActionMenuItem);
            }
        }
        return false;
    }
}
