package testeinterface;

import testeinterface.infraestrutura.repository.Pessoa;

public class Main {

	public static void main(String[] args) {
		
		Pessoa pessoa = new Pessoa();
		
		System.out.println(pessoa.obterPrimeiroNome());
		
	}

}
