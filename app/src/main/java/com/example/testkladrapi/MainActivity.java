package com.example.testkladrapi;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testkladrapi.kladrAPI.Response;
import com.example.testkladrapi.kladrAPI.models.CityModel;
import com.example.testkladrapi.kladrAPI.network.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private Response allPersonalData;
    String limit = "4";
    String withParent = "1";
    String contentTypeCity = "city";

    StringBuilder resultText = new StringBuilder();

    List<CityModel> cityArr = new ArrayList<>();    // Модель \лемента

    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText city = (EditText) findViewById(R.id.edit_text_city);
        TextView textCity = (TextView) findViewById(R.id.text_response);
        TextView textModels = (TextView) findViewById(R.id.text_models);

        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                resultText = new StringBuilder();

                if(city.getText().length() == 0){
                    resultText = new StringBuilder();
                    textCity.setText(resultText.toString());
                }

                if(cityArr.size() != 0 || city.getText().length() == 0){
                    cityArr.clear();
                    resultText = new StringBuilder();
                    textModels.setText(resultText.toString());
                }

            NetworkService.getInstance()
                    .getJSONApi()
                    .listCities(String.valueOf(city.getText()), contentTypeCity, withParent, limit)
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
            public void afterTextChanged(Editable editable) {

            }
        });



    }
}