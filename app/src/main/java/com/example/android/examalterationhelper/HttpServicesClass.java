package com.example.android.examalterationhelper;


import android.util.Log;

import com.loopj.android.http.HttpGet;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.conn.ClientConnectionManager;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.protocol.HTTP;

public class HttpServicesClass {

    public int responseCode;

    public String message;

    public String response;

    public ArrayList<NameValuePair> ArrayListParams;

    public ArrayList <NameValuePair> headers;

    public String UrlHolder;

    public String getResponse()
    {
        return response;
    }

    public String getErrorMessage()
    {
        return message;
    }

    public int getResponseCode()
    {
        return responseCode;
    }

    public HttpServicesClass(String url)
    {
        HttpServicesClass.this.UrlHolder = url;

        ArrayListParams = new ArrayList<NameValuePair>();

        headers = new ArrayList<NameValuePair>();
    }

    public void AddParam(String name, String value)
    {
        ArrayListParams.add(new BasicNameValuePair(name, value));
    }

    public void AddHeader(String name, String value)
    {
        headers.add(new BasicNameValuePair(name, value));
    }

    public void ExecuteGetRequest() throws Exception
    {
        String MixParams = "";

        if(!ArrayListParams.isEmpty())
        {
            MixParams += "?";

            for(NameValuePair p : ArrayListParams)
            {
                String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(),"UTF-8");

                if(MixParams.length() > 1)
                {
                    MixParams  +=  "&" + paramString;
                }
                else
                {
                    MixParams += paramString;
                }
            }
        }

        HttpGet httpGet = new HttpGet(UrlHolder + MixParams);

        for(NameValuePair h : headers)
        {
            httpGet.addHeader(h.getName(), h.getValue());
        }

        executeRequest(httpGet, UrlHolder);
    }

    public void ExecutePostRequest() throws Exception
    {
        Log.v("error9","hi9");
        HttpPost httpPost = new HttpPost(UrlHolder);
        Log.v("error10","hi10");
        for(NameValuePair h : headers)
        {
            Log.v("error11","hi11");
            httpPost.addHeader(h.getName(), h.getValue());
        }

        if(!ArrayListParams.isEmpty())
        {
            Log.v("error12","hi12");
            httpPost.setEntity(new UrlEncodedFormEntity(ArrayListParams, HTTP.UTF_8));
        }

        executeRequest(httpPost, UrlHolder);
    }

    private void executeRequest(HttpUriRequest request, String url)
    {
        Log.v("error13","hi13");
        HttpParams httpParameters = (HttpParams) new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);

        HttpConnectionParams.setSoTimeout(httpParameters, 10000);

        HttpClient httpClient = new DefaultHttpClient((ClientConnectionManager) httpParameters);

        HttpResponse httpResponse;
        try
        {
            Log.v("error14","hi14");
            httpResponse = httpClient.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            HttpEntity entity = httpResponse.getEntity();
            if (entity != null)
            {
                InputStream inputStream = entity.getContent();

                response = convertStreamToString(inputStream);

                inputStream.close();
            }
        }
        catch (ClientProtocolException e)
        {
            httpClient.getConnectionManager().shutdown();
            e.printStackTrace();
        }
        catch (IOException e)
        {
            httpClient.getConnectionManager().shutdown();
            e.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream is)
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        StringBuilder stringBuilder = new StringBuilder();

        String line = null;
        try
        {
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

}