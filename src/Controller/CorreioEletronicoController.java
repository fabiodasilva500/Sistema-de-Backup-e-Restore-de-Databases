package Controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface CorreioEletronicoController {
	public void gerarFormulario();
	public void SelecionarArquivo();
	public  void EnviarArquivo(String caminhoArquivo)
			throws AddressException, MessagingException;

}
