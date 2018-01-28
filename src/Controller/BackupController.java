package Controller;

public interface BackupController {
	public void gerarFormulario();
	public void SelecionarDiretorio();
	public void SelecionarDatabase();
	public void EnviarDadosParaBackupSimples(String database, String diretorio);
	public void EnviarDadosParaFullBackup(String diretorio);
	public void mensagemAutomatica();
}
