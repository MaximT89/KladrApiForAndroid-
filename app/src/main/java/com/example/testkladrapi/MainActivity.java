package com.example.testkladrapi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testkladrapi.kladrAPI.Response;
import com.example.testkladrapi.kladrAPI.models.CityModel;
import com.example.testkladrapi.kladrAPI.network.NetworkService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.R.layout.simple_list_item_1;

public class MainActivity extends AppCompatActivity {

    private Response allPersonalData;
    String limit = "4";
    String withParent = "1";
    String contentTypeCity = "city";

    public String cityId = "";

    StringBuilder resultText = new StringBuilder();

    List<CityModel> cityArr = new ArrayList<>();    // Модель \лемента

    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText cityEdit = (EditText) findViewById(R.id.edit_text_city);
        TextView textCity = (TextView) findViewById(R.id.text_response);
        TextView textModels = (TextView) findViewById(R.id.text_models);
        ListView listCities = (ListView) findViewById(R.id.list_cities);

        cityEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                resultText = new StringBuilder();
                listCities.setVisibility(View.VISIBLE);

                cityArr.clear();
                listCities.setAdapter(null);

                if(cityArr.size() != 0 || cityEdit.getText().length() == 0){
                    cityArr.clear();
                    resultText = new StringBuilder();
                    textModels.setText(resultText.toString());
                    listCities.setVisibility(View.GONE);
                }

            NetworkService.getInstance()
                    .getJSONApi()
                    .listCities(String.valueOf(cityEdit.getText()), contentTypeCity, withParent, limit)
                    .enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            allPersonalData = response.body();

                            try {
                                StringBuilder sb1;

                                for (int i = 1; i < allPersonalData.getResult().size(); i++) {
                                    sb1 = new StringBuilder();
                                    sb1.append(allPersonalData.getResult().get(i).getTypeShort())
                                            .append(". ")
                                            .append(allPersonalData.getResult().get(i).getName());

                                    if (allPersonalData.getResult().get(i).getParents() != null
                                            && allPersonalData.getResult().get(i).getParents().size() != 0) {

                                        StringBuilder sb2 = new StringBuilder();

                                        for (int j = 0; j < allPersonalData.getResult().get(i).getParents().size(); j++) {

                                            sb2.append(", ")
                                                    .append(allPersonalData.getResult().get(i).getParents().get(j).getName())
                                                    .append(" ")
                                                    .append(allPersonalData.getResult().get(i).getParents().get(j).getTypeShort());
                                        }

                                        s = sb1.toString() + sb2.toString() + "\n";

                                        cityArr.add(new CityModel(sb1.toString() + sb2.toString(), allPersonalData.getResult().get(i).getId()));
                                        resultText.append(s);

                                    } else if (allPersonalData.getResult().get(i).getParents() != null && allPersonalData.getResult().get(i).getParents().size() == 0) {

                                        sb1.append("\n");

                                        cityArr.add(new CityModel(sb1.toString(), allPersonalData.getResult().get(i).getId()));
                                        resultText.append(sb1);
                                    }

                                    // Для теста вывод всех элементов cityArr
                                    // <----------------------------------------------------->
                                    StringBuilder cityModelsSB = new StringBuilder();

                                    for (int j = 0; j < cityArr.size(); j++) {
                                        cityModelsSB.append(cityArr.get(j).getCityName())
                                                .append(" ")
                                                .append(cityArr.get(j).getCityId())
                                                .append("\n");
                                    }

                                    textModels.setText(cityModelsSB);
                                    // <----------------------------------------------------->

                                    // Для наполнения listView
                                    // <----------------------------------------------------->
                                    ListAdapter adapterCities = new ArrayAdapter<>(MainActivity.this,
                                            android.R.layout.simple_list_item_1, cityArr);

                                    listCities.setAdapter(adapterCities);
                                    // <----------------------------------------------------->


                                    // Прослушиваение кликов в listView listCities
                                    // <----------------------------------------------------->
                                    listCities.setOnItemClickListener((adapterView, view, i3, l) -> {
                                        cityId = cityArr.get(i3).getCityId();
                                        Log.d("CITY_ID", cityId);

                                        cityEdit.setText(cityArr.get(i3).getCityName());
                                        cityArr.clear();
                                        listCities.setAdapter(null);
                                    });


                                    // <----------------------------------------------------->


                                    textCity.setText(resultText.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) { }
                    });
            }

            @Override
            public void afterTextChanged(Editable sAfter) {
                if(sAfter.toString().equals("г ") || sAfter.toString().equals("г.")
                        || sAfter.toString().equals("д.") || sAfter.toString().equals("д ")
                        || sAfter.toString().equals("пос ") || sAfter.toString().equals("пос.")
                        || sAfter.toString().equals("с ") || sAfter.toString().equals("с.")
                ){
                    cityEdit.setText("");
                }
            }
        });
    }
}