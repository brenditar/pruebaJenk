package CX;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class pruebaJenkins {
	
	static WebDriver driver = new FirefoxDriver();

	@Test
	public void test() {
		abrir();
		cerrar();
	}

	
	public static void abrir(){
		// Abrimos el sitio
		driver.navigate().to("http://www.google.com.ar");
		driver.manage().window().maximize();
	}
	
	public static void cerrar(){
		driver.quit();
	}
}
