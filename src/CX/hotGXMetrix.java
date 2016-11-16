package CX;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class hotGXMetrix {
	static WebDriver driver = new FirefoxDriver();	
	static String strDireccion="C:\\CapturasGXMetrix\\";
	static String baseUrl = "https://gtmetrix.com/";
	
	
	public static void main(String[] args) {
		abrirPageGX();
		ingresarWebSite();
	}
	
	
	public static void abrirPageGX() {
		driver.navigate().to("https://gtmetrix.com/");
		driver.manage().window().maximize();	
	}
	
	public static void ingresarWebSite() {
		String[] arrWebSite = {"www.google.com", "www.garbarino.com", "www.fravega.com", "www.musimundo.com"};
		
		for (int i = 0; i < arrWebSite.length; i++) {
			
			String strWeb=arrWebSite[i];
			
			WebElement buscar = driver.findElement(By.name("url"));
			buscar.clear();
			buscar.sendKeys(arrWebSite[i]);
			
			WebElement botonAnalyse = driver.findElement(By.className("analyze-form-button")); 
			botonAnalyse.click();			
			
			WebDriverWait wait = new WebDriverWait(driver,50);
			WebElement elePDF = wait.until(ExpectedConditions.elementToBeClickable(By.className("actions-other")));
			
			
			if (elePDF.isDisplayed()) {				
				
				capturarPantalla(driver, strWeb);
				elePDF.click();
				
				WebElement radioButtonFull = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='full'])[2]")));
				radioButtonFull.click();
				
			    WebElement descargarPDF = driver.findElement(By.cssSelector("div.form-buttons > button[type=\"submit\"]"));
			    descargarPDF.click();    
			    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			   	  
			
			    
			    }
			
						
			driver.navigate().to("https://gtmetrix.com/");		
			
		}
			
	}	
		

	public static void capturarPantalla(WebDriver driver, String strWeb){	
				
				try {			
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					String strFechaHora=obtenerFecha("Hora");
					FileUtils.copyFile(scrFile, new File(strDireccion+strWeb+"_"+strFechaHora+".png"));
				} catch (IOException e1) {
					// Error en la captura de pantalla
					System.out.println("No se pudo obtener la pantalla.");
					e1.printStackTrace();
				}	
			}
		
		
		public static String obtenerFecha(String strTipo) {
			// Obtiene la fecha del dia
			Calendar fecha = Calendar.getInstance();
	    	int anio = fecha.get(Calendar.YEAR);
	    	int mes = fecha.get(Calendar.MONTH) + 1;
	    	int dia = fecha.get(Calendar.DAY_OF_MONTH);
	    	// Concatena en formato "AAAAMMDD"
	    	String strFecha= Integer.toString(anio)+ Integer.toString(mes)+ Integer.toString(dia);
	    	// Si el parametro es "Hora", agrega la hora del momento en formato "hhmmss".
			if (strTipo=="Hora") {
		       	int hora =fecha.get(Calendar.HOUR_OF_DAY);
		    	int minutos = fecha.get(Calendar.MINUTE);
		    	int segundos = fecha.get(Calendar.SECOND);
		    	strFecha= strFecha + "_"+Integer.toString(hora)+Integer.toString(minutos)+Integer.toString(segundos);
			}		
			return strFecha;
		}
	

}
