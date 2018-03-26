package com.foodstore.app.enums;

/**
 * Classe Enum de status de Promocao Ativa.
 */
public enum PromocaoAtiva {

	SIM(1),

	NAO(0);

	private Integer codigo;

	/**
	 * Instancia uma promocaoEnum.
	 *
	 * @param codigo o codigo
	 */
	private PromocaoAtiva(final Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtem o codigo do status da promocao.
	 *
	 * @return o codigo da promocao
	 */
	public Integer getCodigo() {
		return this.codigo;
	}

	/**
	 * Obter o enum apartir de um int.
	 *
	 * @param codigo o codigo
	 * @return Status da promocao
	 */
	public static PromocaoAtiva getValue(final Integer codigo) {
		if (codigo != null) {
			final PromocaoAtiva[] values = PromocaoAtiva.values();
			for (final PromocaoAtiva codigo2 : values) {
				if (codigo2.compare(codigo)) {
					return codigo2;
				}
			}
		}
		return null;
	}

	/**
	 * Compare.
	 *
	 * @param i the i
	 * @return value
	 */
	private boolean compare(final Integer i) {
		return this.codigo == i.intValue();
	}

}
