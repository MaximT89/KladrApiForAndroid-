package com.example.testkladrapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testkladrapi.kladrAPI.Response;
import com.example.testkladrapi.kladrAPI.network.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private Response allPersonalData;
    String limit = "3";
    String withParent = "1";
    String contentTypeCity = "city";

    StringBuilder resultText = new StringBuilder();

    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText city = (EditText) findViewById(R.id.edit_text_city);
        TextView textCity = (TextView) findViewById(R.id.text_response);
        Button btn = (Button) findViewById(R.id.btn_search_city);

        btn.setOnClickListener(view -> {

            Log.d("SIZE", "в EditText ввели: " + city.getText());

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

                                        StringBuilder sb2;

                                        for (int j = 0; j < allPersonalData.getResult().get(i).getParents().size(); j++) {
                                            sb2 = new StringBuilder();

                                            sb2.append(", ")
                                                    .append(allPersonalData.getResult().get(i).getParents().get(j).getTypeShort())
                                                    .append(" ")
                                                    .append(allPersonalData.getResult().get(i).getParents().get(j).getName());

                                            s = sb1.toString() + sb2.toString() + "\n";

                                            resultText.append(s);
                                        }

                                    } else if (allPersonalData.getResult().get(i).getParents() != null && allPersonalData.getResult().get(i).getParents().size() == 0) {

                                        sb1.append("\n");
                                        resultText.append(sb1);
                                    }

                                    textCity.setText(resultText.toString());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<Response> call, Throwable t) { }
                    });
        });
    }
}