package teste_web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestesCadastroGmail {
	
	private WebDriver driver;
	private DSL dsl;
	private CadastroGmailPage page;
	
	@Before
	public void init(){
		System.setProperty("webdriver.chrome.driver", "\\Users\\priscila.franca\\Downloads\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://gmail.com/mail/help/intl/pt_pt/about.html");
		System.getProperty("user.dir");
		dsl = new DSL(driver);	
		page = new CadastroGmailPage();
	}

	
	@After
	public void finaliza(){
		driver.quit();
	}
	
	@Test
	public void teste1() throws InterruptedException{
		page.botaoCriarConta();
		page.preencherPrimeiroNome("pit");
		page.preencherApelido("pitoca");
		page.preencherNomeUtilizador("pitpitocapete123");
		page.preencherSenha("36055201@Vo");
		page.preencherConfirmacaoSenha("36055201@Vo");
		page.botaoSeguinte();
		
	}
}
