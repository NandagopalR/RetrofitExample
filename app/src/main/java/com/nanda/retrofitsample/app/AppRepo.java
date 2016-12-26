package com.nanda.retrofitsample.app;

import com.nanda.retrofitsample.model.Country;
import java.util.List;

/**
 * Created by nandagopal on 12/26/16.
 */
public class AppRepo {

  private static final String TAG = AppRepo.class.getSimpleName();
  private AppApi appApi;
  private List<Country> countryList;

  public AppRepo(AppApi appApi) {
    this.appApi = appApi;
  }

  public AppApi getApi() {
    return appApi;
  }
}
