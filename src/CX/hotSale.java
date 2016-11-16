package CX;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class hotSale {	
		
	static WebDriver driver = new FirefoxDriver();	
	static String strDireccion="C:\\CapturasHotSale\\";
	
	public static void main(String[] args) {
		
		abrirPage();		
		ingresarWebSite();	
	}
	
	
	public static void abrirPage() {	
				
		driver.navigate().to("http://www.pagescoring.com/website-speed-test/");
		driver.manage().window().maximize();
	}
	
		
	public static void ingresarWebSite() {	
		
		String[] arrWebSite = {"www.lucaiolionline.com", "www.oscarbarbieri.com", "www.trabucohogar.com.ar", "www.brukman.com", "www.hendel.com", "www.electropuntonet.com/home",
			 "shop.wanama.com", "www.golasouth.com", "www.mihran-alfombras.com", "www.lasmargaritas.com.ar",
			 "www.elbalcon.com", "www.chakanawines.com.ar", "www.shop.jugueteriascarrousel.com", "tiendaowoko.com.ar", "www.juguetesuniversales.com/hot-sale-2016"};
		 
		 for (int i = 0; i < arrWebSite.length; i++) {
			 
			String strWeb=arrWebSite[i];
			
			WebElement eleSitio = driver.findElement(By.name("url"));
			eleSitio.clear();
			eleSitio.sendKeys(arrWebSite[i]);
			
			WebElement botonStarTest = driver.findElement(By.id("starttest"));
			botonStarTest.click();
			
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement eleReporte = wait.until(ExpectedConditions.elementToBeClickable(By.id("starttest")));
				
			if (eleReporte.isDisplayed()) {		
				
				//capturar pantalla
				capturarPantalla(driver, strWeb);
			}			
			driver.navigate().to("http://www.pagescoring.com/website-speed-test/");			
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
		


