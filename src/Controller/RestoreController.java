package Controller;

public interface RestoreController {
	public void gerarFormulario();
	public void SelecionarDiretorioFullRestore();
	public void SelecionarDiretorioRestoreSimples();
	public void SelecionarDatabase();
	public void EnviarDadosParaRestoreSimples(String database, String diretorio);	
	public void EnviarDadosParaFullrestore(String diretori, String database);
	public void mensagemAutomatica();
}
