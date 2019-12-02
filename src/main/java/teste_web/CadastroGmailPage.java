package teste_web;

import org.openqa.selenium.By;

public class CadastroGmailPage {
	private DSL dsl;
	
	public void botaoCriarConta() throws InterruptedException{
		dsl.clicar("gmail-create-account");
	}
	
	public void preencherPrimeiroNome(String primeiroNome){
		dsl.escrever("firstName", primeiroNome);
	}
	
	public void preencherApelido(String apelido){
		dsl.escrever("lastName", apelido);
	}
	
	public void preencherNomeUtilizador(String nomeUtilizador){
		dsl.escrever("username", nomeUtilizador);
	}
	
	public void preencherSenha(String senha){
		dsl.escrever(By.name("Passwd"), senha);
	}
	
	public void preencherConfirmacaoSenha(String confirmacaoSenha){
		dsl.escrever(By.name("ConfirmPasswd"), confirmacaoSenha);
	}
	
	public void botaoSeguinte(){
		dsl.clicarRadio(By.linkText("Seguinte"));
	}
	
	public void preencherTelefone(String numero){
		dsl.escrever("phoneNumberId", numero);
	}
	
}
