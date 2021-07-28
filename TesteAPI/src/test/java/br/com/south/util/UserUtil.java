package br.com.south.util;

import java.util.Random;

public class UserUtil {	
		
	public Float randomValEmprestimo() {
		Random valEmprestimo = new Random();
		Float valor = valEmprestimo.nextFloat() + 40.000f;
		return valor;
	}
	
	public int randomNumParcelas() {
		Random numParcelas = new Random();
		int parcelas = numParcelas.nextInt(48) + 2;
		return parcelas;
	}	
	
	public boolean randomSeguro() {
		Random seguroB = new Random();
		boolean seguro = seguroB.nextBoolean();
		return seguro;
	}
	
	public int randomCPF() {
		Random numCPF = new Random();
		int cpf = numCPF.nextInt(999999999) + 000000000001;
		return cpf;
	}
}
