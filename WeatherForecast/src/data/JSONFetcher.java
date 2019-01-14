package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFetcher {

	private float latitude;
	private float longitude;
	private ArrayList<WeatherByHour> weatherdata= new ArrayList<>();
	private LocalDateTime time;	

	public JSONFetcher(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public JSONObject fetch(String station) {
		String url = generateUrl(station);
		System.out.println(url);
		
		try {
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("HTTP error code: " + 
						connection.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line; 
			StringBuilder result = new StringBuilder();
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			connection.disconnect();

			//Fetching the data and parse to jsonObject
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());

			return jsonObject;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		return null;
	}

	private String generateUrl(String station) {
		String url = "";
		if (station.equals("SMHI")) {
			url = "https://opendata-download-metfcst.smhi.se/api/category/pmp2g/version/2/geotype/point/lon/16.004556/lat/58.655684/data.json";
		} else if (station.equals("YR")) {
			url = "";
		} else if (station.equals("OWM")) {
			// https://openweathermap.org/data/2.5/forecast?lat=58.655684&lon=16.004556&appid=b6907d289e10d714a6e88b30761fae22
			url = "https://openweathermap.org/data/2.5/forecast?lat=" + latitude + "&lon=" + longitude + "&appid=b6907d289e10d714a6e88b30761fae22";
		} else {
			System.out.println("Please provide correct station (SMHI, YR, and OWM)!");
		}
		return url;
	}

	// brist på bra namn
	public void parseSMHI(String filename) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filename));
			JSONArray timeSeries = (JSONArray) jsonObject.get("timeSeries");

			for(Object obj :timeSeries) {
				JSONObject resultobj = (JSONObject)obj;		
				JSONArray resultarr = (JSONArray) resultobj.get("parameters");
				String tid  = (String) resultobj.get("validTime");
				Date hourOfDay = null;
				SimpleDateFormat timeOfDay = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				try {
					hourOfDay = timeOfDay.parse(tid);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				WeatherByHour wby = new WeatherByHour();
				wby.setTime(hourOfDay);

				for(int i=0;i<resultarr.size();i++) {
					JSONObject nestedRes = (JSONObject) resultarr.get(i);	
					String  res =((String)nestedRes.get("name"));
					double value;

					JSONArray temp = (JSONArray) nestedRes.get("values");
					try {
						double tempValue = (Double) temp.get(0);
						value = tempValue;

					} catch (ClassCastException e) {
						Long tempValue = (Long) temp.get(0);
						value = tempValue.doubleValue();
					}					

					if(res.equals("t")) {
						wby.setTemperatur(value);	
					}

					else if(res.equals("msl")) {
						wby.setPressure(value);
					}	

					else if (res.equals("vis")) {
						wby.setVisibility(value);
					}

					else if (res.equals("wd")) {
						wby.setWindDirection(value);
					}

					else if (res.equals("ws")) {
						wby.setWindSpeed(value);
					}

					else if (res.equals("pcat")) {
						wby.setPcat((int) value);
					} else if(res.equals("pmax")) {
						wby.setRainfall(value);
					}				
				}		
				weatherdata.add(wby);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void parseOWM(String filename) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filename));
			JSONArray list = (JSONArray) jsonObject.get("list");
			
			for(Object obj :list) {
				JSONObject resultobj = (JSONObject)obj;		
				JSONObject jObj = (JSONObject) resultobj.get("main");
				
				WeatherByHour wby = new WeatherByHour();
				wby.setTemperatur((Double) jObj.get("temp"));
				
				Double pressure; 
				try {
					pressure = (Double) jObj.get("pressure");
				} catch (ClassCastException e) {
					Long tmp = (Long) jObj.get("pressure");
					pressure = tmp.doubleValue();
				}
				wby.setPressure(pressure);
				
				// Parse windobjekt
				JSONObject windObj = (JSONObject) resultobj.get("wind");
				wby.setWindSpeed((Double) windObj.get("speed"));
				
				Double windDirection; 
				try {
					windDirection = (Double) windObj.get("deg");
				} catch (ClassCastException e) {
					Long tmp = (Long) windObj.get("deg");
					windDirection = tmp.doubleValue();
				}
				wby.setWindDirection(windDirection);
				
				System.out.println(wby);
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<WeatherByHour> getWeatherArray(){
		return weatherdata;
	}

}
