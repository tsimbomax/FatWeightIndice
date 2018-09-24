package com.example.root.fatweightindice.dao;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class HttpAccess extends AsyncTask<String, Integer, Long> {

    private ArrayList<NameValuePair> parameters;
    private String ret = null;
    private AsyncResponse delegate = null;

    /**
     * Public constructor. It initializes the parameters of the client request.
     */
    public HttpAccess(){
        this.parameters = new ArrayList<NameValuePair>();
    }

    /**
     * Delagation link
     * @param delegate the implementation of the contract/specification
     */
    public void setDelegate(AsyncResponse delegate) {
        Log.d("INFO", "**************** setDelegate *****************");
        this.delegate = delegate;
    }

    /**
     *Add a parameter into the list of parameters
     * @param name of the parameter to add
     * @param value of the parameter to add
     */
    public void addParam(String name, String value){
        Log.d("INFO", "**************** addParam *****************");
        parameters.add(new BasicNameValuePair(name, value));
    }

    /**
     * Connection en tache de fond dans un thread separé
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {

        Log.d("INFO", "**************** doInBackground *****************");
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(strings[0]);

        try {
            // parameters encoding
            httpPost.setEntity(new UrlEncodedFormEntity(parameters));
            Log.d("INFO", "**************** 01 *****************");
            //connection, sending of parameters and waiting of the result.
            HttpResponse response = httpClient.execute(httpPost);
            Log.d("INFO", "**************** 02 *****************");

            Header headers[] = response.getAllHeaders();
            for (Header header : headers) {
                Log.d("HEAD", "**************" + header.getName());
                if(header.getName()=="result"){
                    Log.d("HEAD", "**************" + header.getValue());
                }

            }
            //response transformation
            ret = EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException e) {
            Log.d("ERROR ENCODING", "**************** " + e.toString());
        } catch (IOException e) {
            Log.d("ERROR CONNECTION", "**************** " + e.toString());
        }
        Log.d("INFO-BACK", "**************** doInBackground *****************");
        return null;
    }

    /**
     *
     * @param aLong
     */
    @Override
    protected void onPostExecute(Long aLong) {
        Log.d("INFO", "**************** onPostExecute *****************");
        delegate.processFinish(ret);
        Log.d("INFO-BACK", "**************** onPostExecute *****************");
    }
}
