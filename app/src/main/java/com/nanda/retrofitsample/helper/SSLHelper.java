package com.nanda.retrofitsample.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by nandagopal on 12/26/16.
 */
public class SSLHelper {

  private Context context;

  public SSLHelper(Context context) {
    this.context = context;
  }

  public SSLSocketFactory getSSlContext() {
    // Install the all-trusting trust manager
    SSLContext sslContext = null;
    try {
      sslContext = SSLContext.getInstance("SSL");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    try {
      sslContext.init(null, getTrustManager(), new java.security.SecureRandom());
    } catch (KeyManagementException e) {
      e.printStackTrace();
    }
    // Create an ssl socket factory with our all-trusting manager
    SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

    return sslSocketFactory;
  }

  public HostnameVerifier getHostNameVerifier() {
    return new HostnameVerifier() {
      @Override public boolean verify(String hostname, SSLSession session) {
        return true;
      }
    };
  }
  
  @NonNull public HttpLoggingInterceptor getHttpLoggingInterceptor() {
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return httpLoggingInterceptor;
  }

  private TrustManager[] getTrustManager() {
    TrustManager[] trustManager = new TrustManager[] {
        new X509TrustManager() {
          @Override public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
              String authType) throws CertificateException {
          }

          @Override public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
              String authType) throws CertificateException {
          }

          @Override public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[] {};
          }
        }
    };

    return trustManager;
  }
}
