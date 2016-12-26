package com.nanda.retrofitsample.app;

import com.nanda.retrofitsample.model.Country;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nandagopal on 12/26/16.
 */

public interface AppApi {

  /*To Get list of country
  *
  * @Param List<Country>> returns the country*/
  @GET(AppConstants.GET_COUNTRY) Call<List<Country>> getCountries();
}
