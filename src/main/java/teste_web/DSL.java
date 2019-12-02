package teste_web;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private WebDriver driver;
	
	public DSL(WebDriver driver){
		this.driver = driver;
	}
	
	public void escrever(By by, String texto){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
		
	}
	
	public void escrever(String id, String texto) {
		escrever(By.id(id), texto);
	}
	
	public String obterValorTexto(String id_campo){
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicar(String id){
		driver.findElement(By.id(id)).click();
	}
	
	public boolean radioMarcado (String id){
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void clicarRadio(By by) {
		driver.findElement(by).click();
	}
	
	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}
	
	public void selecionarCombo(String id, String valor){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public int obterQuantidadeOpcoesCombo(String id){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	
	public boolean verificarOpcaoCombo(String id, String opcao){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	public List<String> obterValoresCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public String msgAlertAcept(){
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		alert.accept();
		return texto;
	}
	
	public String msgAlert(){
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		return texto;
	}
	
	public void okAlert(){
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void confirmAlert(){
		Alert alertCconfirm = driver.switchTo().alert();
		alertCconfirm.dismiss();
	}
	
	public String escreverAlert(String text){
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
		return text;
	}
	
	public void selecionarComboPrime(String radical, String valor){
		clicarRadio(By.xpath(".//*[@id='" + radical + "_focus']/../..//span"));
		clicarRadio(By.xpath(".//ul[@id='"+ radical +"_items']/li[.='"+ valor +"']"));
	}
	
	
	
	public void clicarLink(String linkText){
		driver.findElement(By.linkText(linkText)).click();
	}
	
	public String obterTexto(By by){
		return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id){
		return obterTexto(By.id(id));
	}
	

	/********* Frames e Janelas ************/
	
	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}
	
	public void sairFrame(){
		driver.switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
	
	public Object executarJS(String cmd, Object...param){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
	}
	
	
	/********* Interacao tabelas ************/
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
		//procurar coluna do registro
		WebElement tabela = driver.findElement(By.xpath(".//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);

		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		System.out.println(idLinha);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		System.out.println(idColunaBotao);
		
		//clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++){
			if(linhas.get(i).getText().equals(valor)){
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++){
			if(colunas.get(i).getText().equals(coluna)){
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
}
