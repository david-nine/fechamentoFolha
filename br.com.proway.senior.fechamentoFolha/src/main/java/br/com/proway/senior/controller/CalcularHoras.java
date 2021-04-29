package br.com.proway.senior.controller;

import br.com.proway.senior.model.Folha;

public class CalcularHoras implements InterfaceHorasExtras, InterfaceHorasTrabalhadas, InterfaceHorasFaltando {

	
	/**
	 * Calcula o valor a ser descontado de horas faltas
	 * 
	 * Realiza o c�lculo das horas faltas a serem descontadas na folha do
	 * colaborador, recebe o valor de horasFalta e multiplica valorInsalubridade
	 * 
	 * @return valorFaltas = Retorna o valor a ser descontado na folha do
	 *         colaborador referente as horas faltas.
	 */
	public double calcularValorHorasFaltas(Folha folha) {
		folha.setValorHorasFaltas(folha.getHorasFalta() * folha.getValorHoras());
		return folha.getValorHorasFaltas();
	}

	
	/**
	 * Calcula o valor inicial do sal�rio
	 * 
	 * Pega o valor do m�todo calculaValorHora passa para a vari�vel valorHoras,
	 * ap�s isso a vari�vel valor recebe a multipli��o de horasTrabalhas e
	 * valorHoras
	 * 
	 * @return valor = Retorna o valor do sal�rio inicial, considerando apenas a
	 *         quantidade horas trabalhadas e o valor da hora com insalubridade.
	 */
	public double calcularValorDasHorasTrabalhadas(Folha folha) {
		double valorHoras = this.calculaValorHora(folha);
		double valor = folha.getHorasTrabalhadas() * valorHoras;

		return valor;
	}

	
	/**
	 * Calcula hora normal somando insalubridade.
	 * 
	 * Corre��o a m�todos redundantes de calculo de hora insalubre
	 * 
	 * Pega o valor do m�todo calculaInsalubridade e divide pela vari�vel
	 * horasTrabalhadas
	 *
	 * @return valorHoras = vai retornar o valor ganho de insalubridade por hora
	 */
	public double calculaValorHora(Folha folha) {
		CalculosDeExtras calculo = new CalculosDeExtras();
		double valorHoraInsalubridade = (calculo.calculaInsalubridade(folha) / 220);
		if (valorHoraInsalubridade < 0) {
			folha.setValorHoras(folha.getSalarioBase() / 220);
			return folha.getValorHoras();
		} else {
			folha.setValorHoras((folha.getSalarioBase() / 220) + valorHoraInsalubridade);
			return folha.getValorHoras();
		}
	}

	
	/**
	 * Calcula o valor a ser pago em folha referente as horas extras
	 * 
	 * O valor retornado da multiplica��o de valorHoras e fator, vai somar com o
	 * valorHoras e depois multiplica pelas horasExtra
	 * 
	 * @return valor = Retorna o valor a ser pago de horas extras.
	 */
	public double calcularValorHorasExtras(Folha folha) {
		double valorHora50Porcento;
		valorHora50Porcento = folha.getValorHoras() + (folha.getValorHoras() * folha.getFator());
		folha.setValorHoraExtra(folha.getHorasExtra() * valorHora50Porcento);
		return folha.getValorHoraExtra();
	}
}
