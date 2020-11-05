package com.example.testkladrapi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testkladrapi.kladrAPI.cityJsonModel.Response;
import com.example.testkladrapi.kladrAPI.models.BuildModel;
import com.example.testkladrapi.kladrAPI.models.CityModel;
import com.example.testkladrapi.kladrAPI.models.StreetModel;
import com.example.testkladrapi.kladrAPI.network.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private Response allCityData;
    private Response allStreetData;
    private Response allBuildData;

    String limitCity = "4";
    String limitStreet = "7";
    String limitBuild = "7";
    String withParent = "1";
    String contentTypeCity = "city";
    String contentTypeStreet = "street";
    String contentTypeBuild = "building";

    public String cityId = "";
    public String streetId = "";

    StringBuilder resultTextForCity = new StringBuilder();
    StringBuilder resultTextForStreet = new StringBuilder();
    StringBuilder resultTextForBuild = new StringBuilder();

    List<CityModel> cityArr = new ArrayList<>();    // Модель для города, который идет в listView listCities
    List<StreetModel> streetArr = new ArrayList<>();    // Модель для улицы, которая идет в listView listStreets
    List<BuildModel> buildArr = new ArrayList<>();    // Модель для строения, который идет в listView listStreets

    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Набор для города
        EditText cityEdit = (EditText) findViewById(R.id.edit_text_city);
        ListView listCities = (ListView) findViewById(R.id.list_cities);

        // Набор для улицы
        EditText streetEdit = (EditText) findViewById(R.id.edit_text_street);
        ListView listStreets = (ListView) findViewById(R.id.list_streets);

        // Набор для строения
        EditText buildEdit = (EditText) findViewById(R.id.edit_text_build);
        ListView listBuilds = (ListView) findViewById(R.id.list_builds);

        // Набор для квартиры
        EditText flatEdit = (EditText) findViewById(R.id.edit_text_flat);

        // Набор для индекса
        EditText indexEdit = (EditText) findViewById(R.id.edit_text_index);
        indexEdit.setEnabled(false);
        indexEdit.setClickable(false);
        indexEdit.setFocusable(false);

        cityEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                resultTextForCity = new StringBuilder();
                listCities.setVisibility(View.VISIBLE);

                cityArr.clear();
                listCities.setAdapter(null);

                indexEdit.setText("");

                if (cityArr.size() != 0 || cityEdit.getText().length() == 0) {
                    cityArr.clear();
                    resultTextForCity = new StringBuilder();
                    listCities.setVisibility(View.GONE);
                }

                NetworkService.getInstance()
                        .getJSONApi()
                        .listCities(String.valueOf(cityEdit.getText()), contentTypeCity, withParent, limitCity)
                        .enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                allCityData = response.body();

                                try {
                                    StringBuilder sb1_1;

                                    for (int i = 1; i < allCityData.getResult().size(); i++) {
                                        sb1_1 = new StringBuilder();
                                        sb1_1.append(allCityData.getResult().get(i).getTypeShort())
                                                .append(". ")
                                                .append(allCityData.getResult().get(i).getName());


                                        if (allCityData.getResult().get(i).getParents() != null
                                                && allCityData.getResult().get(i).getParents().size() != 0) {

                                            StringBuilder sb1_2 = new StringBuilder();

                                            for (int j = 0; j < allCityData.getResult().get(i).getParents().size(); j++) {

                                                sb1_2.append(", ")
                                                        .append(allCityData.getResult().get(i).getParents().get(j).getName())
                                                        .append(" ")
                                                        .append(allCityData.getResult().get(i).getParents().get(j).getTypeShort());
                                            }

                                            s = sb1_1.toString() + sb1_2.toString() + "\n";

                                            cityArr.add(new CityModel(sb1_1.toString() + sb1_2.toString(),
                                                    allCityData.getResult().get(i).getId()));

                                            resultTextForCity.append(s);

                                        } else if (allCityData.getResult().get(i).getParents() != null &&
                                                allCityData.getResult().get(i).getParents().size() == 0) {
                                            sb1_1.append("");

                                            cityArr.add(new CityModel(sb1_1.toString(),
                                                    allCityData.getResult().get(i).getId()));

                                            resultTextForCity.append(sb1_1);
                                        }

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
                                            Log.d("CITY_ID", String.valueOf(allCityData.getResult().get(i3+1).getZip()));

                                            cityEdit.setText(cityArr.get(i3).getCityName());
                                            indexEdit.setText(String.valueOf(allCityData.getResult().get(i3+1).getZip()));

                                            cityArr.clear();
                                            listCities.setAdapter(null);
                                        });
                                        // <----------------------------------------------------->
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable sAfter) {
                if (sAfter.toString().equals("г ") || sAfter.toString().equals("г.")
                        || sAfter.toString().equals("д.") || sAfter.toString().equals("д ")
                        || sAfter.toString().equals("пос ") || sAfter.toString().equals("пос.")
                        || sAfter.toString().equals("с ") || sAfter.toString().equals("с.")
                ) {
                    cityEdit.setText("");
                }
            }
        });

        streetEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                resultTextForStreet = new StringBuilder();
                listStreets.setVisibility(View.VISIBLE);

//                indexEdit.setText("");

                streetArr.clear();
                listStreets.setAdapter(null);

                if (streetArr.size() != 0 || streetEdit.getText().length() == 0) {
                    streetArr.clear();
                    resultTextForStreet = new StringBuilder();
                     listStreets.setVisibility(View.GONE);
                }

                NetworkService.getInstance()
                        .getJSONApi()
                        .listStreets(cityId, String.valueOf(streetEdit.getText()), contentTypeStreet, withParent, limitStreet)
                        .enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                allStreetData = response.body();

                                try {
                                    StringBuilder sb2_1;

                                    for (int i = 1; i < allStreetData.getResult().size(); i++) {

                                        sb2_1 = new StringBuilder();
                                        sb2_1.append(allStreetData.getResult().get(i).getTypeShort())
                                                .append(". ")
                                                .append(allStreetData.getResult().get(i).getName());

                                        streetArr.add(new StreetModel(sb2_1.toString(), allStreetData.getResult().get(i).getId()));
                                        resultTextForStreet.append(sb2_1);

                                        // Для наполнения listViewStreet
                                        // <----------------------------------------------------->
                                        ListAdapter adapterStreets = new ArrayAdapter<>(MainActivity.this,
                                                android.R.layout.simple_list_item_1, streetArr);

                                        listStreets.setAdapter(adapterStreets);
                                        // <----------------------------------------------------->

                                        // Прослушиваение кликов в listView listStreets
                                        // <----------------------------------------------------->
                                        listStreets.setOnItemClickListener((adapterView, view, i3, l) -> {
                                            streetId = streetArr.get(i3).getStreetId();
                                            Log.d("CITY_ID", streetId);

                                            indexEdit.setText(String.valueOf(allStreetData.getResult().get(i3 + 1).getZip()));

                                            streetEdit.setText(streetArr.get(i3).getStreetName());
                                            streetArr.clear();
                                            listStreets.setAdapter(null);
                                        });
                                        // <----------------------------------------------------->
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
            public void afterTextChanged(Editable editable) { }
        });

        buildEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                resultTextForBuild = new StringBuilder();
                listBuilds.setVisibility(View.VISIBLE);

                indexEdit.setText("");

                buildArr.clear();
                listBuilds.setAdapter(null);

                if (buildArr.size() != 0 || buildEdit.getText().length() == 0) {
                    buildArr.clear();
                    resultTextForBuild = new StringBuilder();
                    listBuilds.setVisibility(View.GONE);
                }

                NetworkService.getInstance()
                        .getJSONApi()
                        .listBuildings(streetId, String.valueOf(buildEdit.getText()), contentTypeBuild, limitBuild)
                        .enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                                allBuildData = response.body();

                                try {
                                    StringBuilder sb3_1;

                                    for (int i = 1; i < allBuildData.getResult().size(); i++) {

                                        sb3_1 = new StringBuilder();
                                        sb3_1.append(allBuildData.getResult().get(i).getTypeShort())
                                                .append(". ")
                                                .append(allBuildData.getResult().get(i).getName());


                                        buildArr.add(new BuildModel(sb3_1.toString()));
                                        resultTextForBuild.append(sb3_1);

                                        // Для наполнения listViewBuilds
                                        // <----------------------------------------------------->
                                        ListAdapter adapterBuilds = new ArrayAdapter<>(MainActivity.this,
                                                android.R.layout.simple_list_item_1, buildArr);

                                        listBuilds.setAdapter(adapterBuilds);
                                        // <----------------------------------------------------->


                                        // Прослушиваение кликов в listView listBuilds
                                        // <----------------------------------------------------->
                                        listBuilds.setOnItemClickListener((adapterView, view, i3, l) -> {

                                            buildEdit.setText(buildArr.get(i3).getBuildName());
                                            indexEdit.setText(String.valueOf(allBuildData.getResult().get(i3 + 1).getZip()));

                                            buildArr.clear();
                                            listBuilds.setAdapter(null);
                                        });
                                        // <----------------------------------------------------->
                                    }

                                }catch(Exception e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) { }
                        });
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

    }
}