package com.xriva.weatherline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.bind.DatatypeConverter;

public class WeatherLine
{

	public WeatherLine()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		WeatherLine wl = new WeatherLine();
		wl.findWeather("75214:4:US");
		wl.findWeather("19.3034", "-81.3863"); // George Town, Grand Cayman

	}

	private void findWeather(String loc)
	{
		String baseURL = "https://twcservice.mybluemix.net";
		String auth = "user" + ":"
				+ "pass";
		String authB64 = DatatypeConverter.printBase64Binary(auth.getBytes());

		try
		{
			URL weatherURL = new URL(baseURL + String.format(
					"/api/weather/v1/location/%s/observations.json?units=e&language=en-US",
					loc));
			URLConnection conds = weatherURL.openConnection();
			conds.setRequestProperty("Authorization", "Basic " + authB64);
			conds.connect();

			Reader r = new BufferedReader(
					new InputStreamReader(conds.getInputStream()));
			StreamTokenizer st = new StreamTokenizer(r);
			st.quoteChar('"');
			st.parseNumbers();
			st.eolIsSignificant(false);
			while ((st.nextToken() != StreamTokenizer.TT_EOF))
			{
				if (st.ttype == StreamTokenizer.TT_WORD || st.ttype == '"')
					System.out.println("<" + st.sval + ">");
				if (st.ttype == StreamTokenizer.TT_NUMBER)
					System.out.println("[" + st.nval + "]");
			}
		}
		catch (MalformedURLException e)
		{
			System.err.printf("Malformed URL Exception" + e.getMessage());
		}
		catch (IOException e)
		{
			System.err.printf("IOException" + e.getMessage());
		}
		catch (NullPointerException e)
		{
			System.err.printf("Null Pointer Exception" + e.getMessage());
		}

	}

	private void findWeather(String lat, String lng)
	{
		String baseURL = "https://twcservice.mybluemix.net";
		String auth = "f131a669-5b09-425b-8b37-886bd8b7553d" + ":"
				+ "5fmwUvqFUZ";
		String authB64 = DatatypeConverter.printBase64Binary(auth.getBytes());

		try
		{
			URL weatherURL = new URL(baseURL + String.format(
					"/api/weather/v1/geocode/%s/%s/observations.json?units=e&language=en-US",
					lat, lng));
			URLConnection conds = weatherURL.openConnection();
			conds.setRequestProperty("Authorization", "Basic " + authB64);
			conds.connect();

			Reader r = new BufferedReader(
					new InputStreamReader(conds.getInputStream()));
			StreamTokenizer st = new StreamTokenizer(r);
			st.quoteChar('"');
			st.parseNumbers();
			st.eolIsSignificant(false);
			while ((st.nextToken() != StreamTokenizer.TT_EOF))
			{
				if (st.ttype == StreamTokenizer.TT_WORD || st.ttype == '"')
					System.out.println("<" + st.sval + ">");
				if (st.ttype == StreamTokenizer.TT_NUMBER)
					System.out.println("[" + st.nval + "]");
			}
		}
		catch (MalformedURLException e)
		{
			System.err.printf("Malformed URL Exception" + e.getMessage());
		}
		catch (IOException e)
		{
			System.err.printf("IOException" + e.getMessage());
		}
		catch (NullPointerException e)
		{
			System.err.printf("Null Pointer Exception" + e.getMessage());
		}

	}

}
