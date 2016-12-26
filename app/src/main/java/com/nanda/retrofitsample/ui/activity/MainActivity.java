package com.nanda.retrofitsample.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.nanda.retrofitsample.R;
import com.nanda.retrofitsample.app.AppController;
import com.nanda.retrofitsample.app.AppRepo;
import com.nanda.retrofitsample.model.Country;
import com.nanda.retrofitsample.utils.UiUtils;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private AppRepo appRepo;
  private TextView tvCountry;
  private List<Country> countryList;
  private ProgressDialog progressDialog;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Loading Country List...");
    progressDialog.setCancelable(false);

    tvCountry = (TextView) findViewById(R.id.tv_country);
    appRepo = AppController.getInstance().getAppRepo();

    progressDialog.show();
    Call<List<Country>> call = appRepo.getApi().getCountries();
    call.enqueue(new Callback<List<Country>>() {
      @Override public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
        countryList = response.body();
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
        UiUtils.showToast(getApplicationContext(),
            countryList != null ? "" + countryList.size() : "0");
        StringBuilder sb = new StringBuilder();
        for (int i = 0, countryListSize = countryList.size(); i < countryListSize; i++) {
          Country country = countryList.get(i);
          sb.append(country.getName() + "\n");
        }

        tvCountry.setText(sb.toString());
      }

      @Override public void onFailure(Call<List<Country>> call, Throwable throwable) {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
        UiUtils.showToast(getApplicationContext(), throwable.getMessage());
      }
    });
  }
}
