package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Component
public class AtivacaoClienteService {
	//implements InitializingBean, DisposableBean {
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	/*
	 * Podemos incluir uma parâmetro "required em @Autowired", desta forma dizemos ao Spring se ele é obrigatório ou não, 
	 * o valor padrão é true.
	 * Caso seja definido como true é obrigatório que um Bean deste tipo seja carregado no IoC Container
	 * */
	//@Qualifier("notificacao.urgente")
	/*@TipoDoNotificador(NivelPrioridade.SEM_URGENCIA)
	@Autowired(required = true)
	private InterfaceNotificador notificador;*/
	
	
	/*
	 * É recomendado manter a anotação @Autowired no construtor da classe, desta forma você consegue fazer a injeção manual se for necessário ou quando for realizar algum teste unitário
	 * Caso você mantenha a injeção no atributo private você vai deixar a responsabilidade total da injeção para seu framework.
	 * */
	
	//@Autowired
	/*public AtivacaoClienteService(InterfaceNotificador notificao) {
		this.notificao = notificao;		
	}
	
	public AtivacaoClienteService(String qulquerParametro) {		
	}*/

	public void ativar(Cliente cliente) {
		
		cliente.ativar();
		
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
		
		/*if ( notificador == null ) {
			System.out.println("Cliente ativado, porém não foi notificado da ativação");
		}
		else {
			notificador.notificar(cliente, "Cliente ativado");		
		}*/
		
	}

	/*@Autowired
	public void setNotificao(InterfaceNotificador notificao) {
		this.notificao = notificao;
	}*/
	
	/*@PostConstruct
	public void init() {
		System.out.println("AtivacaoClienteService -> INIT Called");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("AtivacaoClienteService -> DESTROY Called");
	}*/

	/*@Override
	public void afterPropertiesSet() throws Exception {		
		System.out.println("AtivacaoClienteService -> afterPropertiesSet Called");
	}*/
	
	/*
	 * é possível criar métodos de Callback nas Beans, o método de Call-back é uma função chamada logo apos a construção da classe ou logo antes dela ser destruída
	 * existem 3 formas de se fazer isso:
	 * 1 - Implementando as interfaces [InitializingBean, DisposableBean]: estas interfaces obrigam a implementar duas funções afterPropertiesSet (ação executada após a construção da classe) e destroy (ação executada após a destruição da classe) 
	 * 2 - Usando duas anotações @PostConstruct (que informa ao Ioc que a função deve ser chamada apos a construção da classe), @PreDestroy (ação que deve ser executada apos a destruição da classe)
	 * 		para usar estas anotações basta colocar elas acima das funçoes que devem ser executada , por exemplo, @PostConstruct init() e @PreDestroy destroy()
	 * 3 - No ServiceConfig que faz a instancia do Bean você pode usar um parâmetro na anotação @Bean passando dois parametros: initMethod e destroyMethod
	 * 		nestes parametros você deve passar uma String com o nome da função que deve ser chamada, exemplo:
	 * 		@Bean(initMethod = "init", destroyMethod = "destroy"), desta forma quando a classe Configuration criar o Bean ela já informa que estes metodos devem ser chamados no inicio e fim.	 *
	 *  Obs: a forma mais elegante de se usar é a opção "2"
	 * */
	
	
}
