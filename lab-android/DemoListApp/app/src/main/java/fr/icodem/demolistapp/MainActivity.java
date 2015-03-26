package fr.icodem.demolistapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private TextView selectionText;
    private ListView productList;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = (ListView) findViewById(R.id.productList);
        selectionText = (TextView) findViewById(R.id.selectionText);
        adapter = new ProductAdapter(this);
        productList.setAdapter(adapter);

        new LoadProductList().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("MainActivity", "Item clicked: " + id);
        Product p = (Product) adapter.getItem(position);

        //Toast.makeText(this, "Selected product : " + p.getName(), Toast.LENGTH_LONG);
        selectionText.setText(p.getName());
    }


    private class LoadProductList extends AsyncTask<Void, Void, List<Product>> {

        @Override
        protected List<Product> doInBackground(Void... voids) {
            URL url = null;
            HttpURLConnection connection = null;
            Log.i("MainActivity", "load product list");

            List<Product> productList = null;
            try {
                url = new URL(Constants.BASE_URL + "/myapp/products?name=tintin");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept","application/json");

                InputStream is = connection.getInputStream();
                String data = IOUtils.readStream(is);
                productList =  parseProduct(data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(connection!=null)
                    connection.disconnect();
            }

            return productList;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            adapter.clear();
            adapter.addAll(products);
            adapter.notifyDataSetChanged();
        }

        private List<Product> parseProduct(String str){
            List<Product> products = new ArrayList<Product>();
            try {
                JSONArray jsonArray = new JSONArray(str);
                for(int i =0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int id = jsonObject.getInt("id");
                    String nom = jsonObject.getString("name");
                    String description= jsonObject.getString("description");
                    double price=jsonObject.getLong("price");

                    Product product = new Product();
                    product.setId(id);
                    product.setName(nom);
                    product.setDescription(description);
                    product.setPrice(price);
                    products.add(product);
                }

            }catch (JSONException e) {
                Log.e("MainActivity", "Failed loading products", e);
            }
            return products;
        }
    }

}
