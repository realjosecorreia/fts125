package extra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BuscaExtra {
	String url;
	WebDriver driver;
	String nomePasta = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());
	
	// Funções e Metodos de Apoio
	
	// Tirar Print da Tela
	public void Print(String nomePrint) throws IOException {
		File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(foto, new File("C:\\Users\\corre\\fts125-workspace-b\\Extra\\target\\evidencias\\" + nomePasta + "\\" + nomePrint + ".png"));
	}
	
	@Before
	public void Iniciar() {
		url = "https://www.submarino.com.br";
		System.setProperty("webdriver.chrome.driver",
		 "C:\\Users\\corre\\fts125-workspace-b\\Extra\\drivers\\chrome\\79\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
	}
	
	@After
	public void Finalizar() {
		driver.quit();
	}
	
	@Given("^que acesso o site do Submarino$")
	public void que_acesso_o_site_do_submarino() throws Throwable {
		driver.get(url);
		Print("Passo 1 - Acessou o site do Submarino");
		System.out.println("Passo 1 - Acessou o site do Submarino");
	}

	@When("^preencho o termo \"([^\"]*)\" e clico na Lupa$")
	public void preencho_o_termo_e_clico_na_Lupa(String termo) throws Throwable {
		driver.findElement(By.id("h_search-input")).clear();
		driver.findElement(By.id("h_search-input")).sendKeys(termo);
		Print("Passo 2 - Preencheu o termo de busca");
		driver.findElement(By.id("h_search-input")).sendKeys(Keys.ENTER);
		//driver.findElement(By.id("btnOK")).click();
	}

	@Then("^exibe a lista de produtos$")
	public void exibe_a_lista_de_produtos() throws Throwable {
		Thread.sleep(3000);
		assertEquals("Smartphone com Ofertas Incríveis no Submarino.com", driver.getTitle());
		//assertEquals("smartphone", driver.findElement(By.cssSelector("ul.neemu-breadcrumb-container")).getText());
		Print("Passo 3.p - Exibiu a lista de produtos");
	}
	
	@Then("^exibe a mensagem de produto nao encontrado$")
	public void exibe_a_mensagem_de_produto_nao_encontrado() throws Throwable {
		Thread.sleep(3000);
		assertTrue(driver.findElement(By.cssSelector("span.TextUI-sc-12tokcy-0.CIZtP")).getText().contains("Não encontramos nenhum resultado para"));
		//assertEquals("smartphone", driver.findElement(By.cssSelector("ul.neemu-breadcrumb-container")).getText());
		Print("Passo 3.n - Exibiu a mensagem de erro");
	}
}
