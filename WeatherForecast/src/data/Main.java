package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONObject;

public class Main {

	public static void main(String[] args) {		
		Main main = new Main();

		// Fetch data from smhi
		float latitude = 56.16156f;
		float longitude = 15.58661f;

		JSONFetcher fetcher = new JSONFetcher(latitude, longitude);
		/*JSONObject ett = new JSONObject();
		try {
			ett = fetcher.fetch("SMHI");
		} catch (ParseException e) {
			System.out.println("fel i main parser");
		}
		String fileName = main.generateFileName("SMHI");
		main.createJsonFile(fileName, ett);*/
		
		//hämta OWM data til fil
		//JSONObject tva = fetcher.fetch("OWM");
		
		//String newFile = main.generateFileName("OWM");
		//main.createJsonFile(newFile, tva);
		
		
		JSONFetcher fetcherOWM = new JSONFetcher(latitude, longitude);
		fetcherOWM.parseOWM("src/files/OWM_18_12_21_13_55.json");
		
		
		
		
		//SMHI
		//fetcher.parseSMHI("src/files/SMHI_18_12_20_14_16.json");
		//ArrayList<WeatherByHour> res = fetcher.getWeatherArray();
		//res.forEach(System.out::println);
		//for(int i=0;i<res.size(); i++)
		//	System.out.println(res.get(i).getTemperatur());
		
		
		
	}

	
	public void printJsonFile(JSONObject json) {
		String filepath = "src/files/example.json";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filepath));
			String line;
			while((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createJsonFile(String fileName, JSONObject jsonObject) {
		String filepath = "src/files/" + fileName;
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filepath));
			out.write(jsonObject.toJSONString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Creates an appropriate name for a json file, format ex = ABC_yy_MM_dd_HH_mm.json
	public String generateFileName(String station) {
		String file = "";
		List<String> stations = Arrays.asList("SMHI", "YR", "OWM");
		if (stations.contains(station.toUpperCase())) {
			file += station.toUpperCase() + "_";
			String timestamp = new SimpleDateFormat("yy_MM_dd_HH_mm")
					.format(Calendar.getInstance().getTime());
			file += timestamp + ".json";
			System.out.println(file);
			return file;
		} else {
			System.out.println("Valid options: SMHI, YR, and OWM!");
		}
		return null;
	}


}
