package com.biggdiscountsmedia.biggdiscounts.httputils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

public class HttpUtil {

	private static final String DEFAULT_ERROR_MSG = "Server not responding";

	public HttpUtil() {
		init();
	}

	private void init() {
	}

	/**
	 * Method will send httpGet request to server
	 * 
	 * @param <T>
	 * 
	 * @param url
	 * @param post
	 * @return
	 */
	public String doGet(String url) {

		// Log.d("URL", url);
		BasicHttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, 60000);
		HttpConnectionParams.setSoTimeout(httpParameters, 60000);

		// create HttpClient
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		InputStream inputStream = null;
		// make GET request to the given URL
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("Accept", "application/json"); // or
															// application/jsonrequest
		httpget.setHeader("Content-Type", "application/json");
		try {
			HttpResponse httpResponse = httpClient.execute(httpget);
			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// convert inputstream to string
		if (inputStream != null) {
			try {
				String result = getStringFromInputStream(inputStream);
				return result;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Method will doPost to server
	 * 
	 * @param <T>
	 * 
	 * @param url
	 * @param post
	 * @return
	 */
	public String doPost(String url, List<NameValuePair> post) {

		InputStream inputStream = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(post));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			inputStream = httpEntity.getContent();

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// convert inputstream to string
		if (inputStream != null) {
			try {
				String result = getStringFromInputStream(inputStream);
				return result;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * This Method will get the String data from Input Stream
	 * 
	 * @param is
	 * @return String
	 * @throws IOException
	 */
	private String getStringFromInputStream(InputStream is) throws IOException {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
}
