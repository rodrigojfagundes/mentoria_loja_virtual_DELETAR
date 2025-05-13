package jdev.mentoria.lojavirtual.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jdev.mentoria.lojavirtual.model.PessoaFisica;
import jdev.mentoria.lojavirtual.model.PessoaJuridica;
import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.model.dto.CepDTO;
import jdev.mentoria.lojavirtual.model.dto.ConsultaCnpjDto;
import jdev.mentoria.lojavirtual.repository.PesssoaFisicaRepository;
import jdev.mentoria.lojavirtual.repository.PesssoaRepository;
import jdev.mentoria.lojavirtual.repository.UsuarioRepository;

@Service
public class PessoaUserService {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PesssoaRepository pesssoaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	@Autowired
	private PesssoaFisicaRepository pesssoaFisicaRepository;
	
	
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica juridica) {
		
		//juridica = pesssoaRepository.save(juridica);
		
		for (int i = 0; i< juridica.getEnderecos().size(); i++) {
			juridica.getEnderecos().get(i).setPessoa(juridica);
			juridica.getEnderecos().get(i).setEmpresa(juridica);
		}
		
		juridica = pesssoaRepository.save(juridica);
		
		//verificando se ja existe um usuario para essa PESSOAJURIDICA,
		//usuario com USERNAME e PASSWORD... Iremos consultar por ID e 
		//e-mail/login
		Usuario usuarioPj = usuarioRepository.findUserByPessoa(juridica.getId(), juridica.getEmail());
		
		//se nao tiver usuario para essa PESSOAJURIDICA, vamos fazer o cadastro
		if (usuarioPj == null) {
			
			String constraint = usuarioRepository.consultaConstraintAcesso();
			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
			}
			
			//criando um novo usuario para cadastrar a pessoa juridica
			//pois qd cad uma pessoa juridica é criado automaticamente um
			//usuario para acessar o sistema
			usuarioPj = new Usuario();
			usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPj.setEmpresa(juridica);
			usuarioPj.setPessoa(juridica);
			usuarioPj.setLogin(juridica.getEmail());
			
			//a senha gerada aleartoriamente é a data e os min atual
			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = new BCryptPasswordEncoder().encode(senha);
			
			usuarioPj.setSenha(senhaCript);
			
			usuarioPj = usuarioRepository.save(usuarioPj);
			
			usuarioRepository.insereAcessoUser(usuarioPj.getId());
			//inserindo PJ/EMPRESA com ROLE/ACESSO DINAMICO no caso "ROLE_ADMIN"
			//alem do ROLE_USER q e o padrao...
			usuarioRepository.insereAcessoUserPj(usuarioPj.getId(), "ROLE_ADMIN");
			
			StringBuilder menssagemHtml = new StringBuilder();
			
			menssagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b><br/>");
			menssagemHtml.append("<b>Login: </b>"+juridica.getEmail()+"<br/>");
			menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			menssagemHtml.append("Obrigado!");
			
			//enviando email com o titulo, o conteudo e o e-mail de destino
			//e-mail de destino e o q foi cad na hora de criar a empresa 
			//(PESSOAJURIDICA)
			try {
			  serviceSendEmail.enviarEmailHtml("Acesso Gerado para Loja Virtual", menssagemHtml.toString() , juridica.getEmail());
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//se tiver usuario para essa PJ dai vamos retornar 
		//a pessoa juridica
		return juridica;
		
	}	
	
	//metodo para salvar uma PESSOAFISICA
	public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {
	//juridica = pesssoaRepository.save(juridica);
		
		for (int i = 0; i< pessoaFisica.getEnderecos().size(); i++) {
			pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
			//pessoaFisica.getEnderecos().get(i).setEmpresa(pessoaFisica);
		}
		
		pessoaFisica = pesssoaFisicaRepository.save(pessoaFisica);
		
		//verificando se ja existe um usuario para essa PESSOAFISICA,
		//usuario com USERNAME e PASSWORD... Iremos consultar por ID e 
		//e-mail/login
		Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaFisica.getId(), pessoaFisica.getEmail());
		
		//se nao tiver usuario para essa PESSOAFISICA vamos fazer o cadastro
		if (usuarioPj == null) {
			
			String constraint = usuarioRepository.consultaConstraintAcesso();
			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint +"; commit;");
			}
			
			//criando um novo usuario para cadastrar a PESSOAFISICA
			usuarioPj = new Usuario();
			usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPj.setEmpresa(pessoaFisica.getEmpresa());
			usuarioPj.setPessoa(pessoaFisica);
			usuarioPj.setLogin(pessoaFisica.getEmail());
			
			//a senha gerada aleartoriamente é a data e os min atual
			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = new BCryptPasswordEncoder().encode(senha);
			
			usuarioPj.setSenha(senhaCript);
			
			usuarioPj = usuarioRepository.save(usuarioPj);
			
			usuarioRepository.insereAcessoUser(usuarioPj.getId());
			
			StringBuilder menssagemHtml = new StringBuilder();
			
			menssagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b><br/>");
			menssagemHtml.append("<b>Login: </b>"+pessoaFisica.getEmail()+"<br/>");
			menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			menssagemHtml.append("Obrigado!");
			
			//enviando email com o titulo, o conteudo e o e-mail de destino
			//e-mail de destino e o q foi cad na hora de criar a empresa 
			//(PESSOAJURIDICA)
			try {
			  serviceSendEmail.enviarEmailHtml("Acesso Gerado para Loja Virtual", menssagemHtml.toString() , pessoaFisica.getEmail());
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//se tiver usuario para essa PJ dai vamos retornar 
		//a pessoa juridica
		return pessoaFisica;
	}
	
	public CepDTO consultaCep(String cep) {
		//passando um numero de CEP q esta no atributo CEP para o VIACEP.COM.BR
		//e esperando receber um obj do tipo CEPDTO (CEPDTO.CLASS)
		return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class).getBody();
	}
	
	public ConsultaCnpjDto consultaCnpjReceitaWS(String cnpj) {
		//passando um numero de CNPJ para a API do RECEITAWS e iremos receber
		//as informacoes referente a esse CNPJ q sera salva em OBJ do tipo
		//CONSULTACNPJDTO
		return new RestTemplate().getForEntity("https://receitaws.com.br/v1/cnpj/" + cnpj, ConsultaCnpjDto.class).getBody();
	}
	

}
