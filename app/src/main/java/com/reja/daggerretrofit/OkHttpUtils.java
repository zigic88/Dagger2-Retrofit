package com.reja.daggerretrofit;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by Reja on 21,January,2019
 * Jakarta, Indonesia.
 */
public class OkHttpUtils {
    private static final String SSL = "SSL";
    private static final String CONSTANT_TOKEN = "token";
    private static final String CONSTANT_AUTH = "Authorization";

    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;

    /**
     * Create Okhttp instance
     *
     * @return
     */
    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }

    /**
     * Get OkHttp Client
     *
     * @return
     */
    public OkHttpClient getClient() {
        return okHttpClient;
    }

    /**
     * Initiate OkHttp Client
     *
     * @param interceptor
     */
    public void initiate(Interceptor interceptor) {
        int connectTimeout = 30;
        int readTimeout = 30;
        if (interceptor == null) {
            okHttpClient = getOkHttpTrustCertificate(connectTimeout, readTimeout).build();
        }
        okHttpClient = getOkHttpTrustCertificate(connectTimeout, readTimeout).addInterceptor(interceptor).build();

    }

    /**
     * Enable Trust SSL Certification
     *
     * @param connectTimeout
     * @param readTimeout
     * @return
     */
    public OkHttpClient.Builder getOkHttpTrustCertificate(int connectTimeout, int readTimeout) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance(SSL);
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS)
                    .readTimeout(readTimeout, TimeUnit.SECONDS).sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Enable Trust SSL Certification
     *
     * @param connectTimeout
     * @param readTimeout
     * @return
     */
    public OkHttpClient.Builder getOkHttpUtilsCert(int connectTimeout, int readTimeout) {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
            SSLContext sslContext = SSLContext.getInstance(SSL);
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout, TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory, trustManager);
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add headers into okhttp client builder
     *
     * @param builder
     * @param headers
     */
    public void addHeaders(Request.Builder builder, Map<String, String> headers) {
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> map : headers.entrySet()) {
                if (map.getKey().equalsIgnoreCase(CONSTANT_TOKEN)) {
                    builder.header(CONSTANT_AUTH, map.getValue());
                } else {
                    builder.header(map.getKey(), map.getValue());
                }
            }
        }
    }

    /**
     * Get the Request Builder
     *
     * @return
     */
    public Request.Builder getBuilder() {
        return new Request.Builder();
    }
}