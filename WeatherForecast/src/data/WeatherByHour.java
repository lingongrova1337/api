package data;

import java.util.Date;

public class WeatherByHour {
	private Date time;		
	private double temperatur;	//celsius
	private double pressure;	//HPA
	private double windSpeed;	//M/s
	private double windDirection;//degreees	
	//rain kan vara snö regn bla bla bla
	//pcat definerar nederbörden
	private double rainfall; //mm
	private int pcat;
	private double visibility;	//km

	public WeatherByHour(Date time) {
		this.time=time;
	}

	public WeatherByHour(Date time, double temperatur, double pressure, double windSpeed, double windDirection,
			double rainfall, int pcat, double visibility) {
		super();
		this.time = time;
		this.temperatur = temperatur;
		this.pressure = pressure;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.rainfall = rainfall;
		this.pcat = pcat;
		this.visibility = visibility;
	}

	public WeatherByHour() {
		
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(double temperatur) {
		this.temperatur = temperatur;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(double windDirection) {
		this.windDirection = windDirection;
	}

	public double getRainfall() {
		return rainfall;
	}

	public void setRainfall(double rainfall) {
		this.rainfall = rainfall;
	}

	public int getPcat() {
		return pcat;
	}

	public void setPcat(int pcat) {
		this.pcat = pcat;
	}

	public double getVisibility() {
		return visibility;
	}

	public void setVisibility(double visibility) {
		this.visibility = visibility;
	}
	
	public String toString() {
		
		String in = "";
		in += "Date: " + this.time   + ", Temp: " + this.temperatur + ", Pressure: " + this.pressure + ", Pcat: " + this.pcat + ", Rainfall: " + this.rainfall;
		return in;
	}



}
