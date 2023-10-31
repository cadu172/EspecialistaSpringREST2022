package com.algaworks.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;


/*
 * Quando temos dois Beans que implementam a mesma interface e uma classe também Bean faz a injeção de dependencia o Spring acusa um erro 
 * informando que houve uma ambiguidade, ou seja, tem dois Beans iguais carregados para serem injetados.
 * Um forma de resolver isso é alterar o construtor ou atribuito com a marcação @Autowired para um tipo List<> do tipo da Interface, 
 * desta forma o Spring passa todos os Beans para esta lista e você pode usar da forma que foi mais conveniente.
 * Caso você não queira priorizar você precisa informar ao Spring qual o Bean tem prioridade de injeção na classe, então você uma anotação 
 * chamada @Primary
 * */


/*
 * No lugar da anotação @Primary podemos usar a anotação @Qualifier("aliasname"), anotação permite criar um apelido para o Bean, 
 * é mais prático que usar o @Primary porque quando for necessário fazer injeção basta incluir acima do atributo @Autowired a anotação 
 * com o apelido criado.
 * No exemplo abaixo criamos 2 anotações  @Qualifier("notificacao.normal") e @Qualifier("notificacao.urgente"), 
 * na classe AtivacaoClienteService chamamos por exemplo a anotação @Qualifier("notificacao.urgente")
 * com isso o Spring Injeta nesta classe o Bean NotificadorSMS que foi o Bean com aplidado de urgente.
 * */


/*
 * É possível criar um tipo de Qualifier customizado, desta podemos usar as chamadas aos Qualifier de uma forma mas elegante, por exemplo
 * podemos passar como parametro um ENUM com o tipo de Qualifier ao inves de usarmos uma String
 * 
 * */

/*
 * @Profile podemos criar perfis que serão usados para carregar os Beans de acordo com o ambiente que queremos,
 * por exemplo, podemos criar uma classe MOCK que somente simula o envio de um e-mail mas não faz de fato, somente para teste,
 * exemplo:  @Profile("dev")
 * 
 * para ativar o perfil desejado vá no arquivo 
 * application.properties
 * 
 * incluir a seguinte linha: 
 * 
 * spring.profiles.active=dev
 * 
 * você pode definir vários profiles ativos, exemplo:
 * 
 * pring.profiles.active=dev,mysql,redis,oauth2
 * 
 * */

//@Primary
//@Qualifier("notificacao.normal")
@Profile("prod")
@TipoDoNotificador(NivelPrioridade.URGENTE)
@Component
public class NotificadorSMS implements InterfaceNotificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println("Cliente " + cliente.getNome() +
					" notificado por SMS atraves do telefone " + cliente.getTelefone() +
					" - Mensagem : " + mensagem );
	}

}
