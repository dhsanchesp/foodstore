package com.foodstore.app.enums;

/**
 * Classe Enum de Ingrediente.
 */
public enum IngredienteEnum {

	HAMBURGUER(1, "HAMBURGUER DE CARNE"),
	ALFACE(2, "ALFACE"),
	BACON(3, "BACON"),
	OVO(4, "OVO"),
	QUEIJO(5, "QUEIJO");

	private Integer codigo;
	private String nomeIngrediente;

	/**
	 * Instancia um novo ingrediente.
	 *
	 * @param codigo o codigo
	 */
	private IngredienteEnum(final Integer codigo, final String nomeIngrediente) {
		this.codigo = codigo;
		this.nomeIngrediente = nomeIngrediente;
	}

	/**
	 * Obtem o codigo do ingrediente.
	 *
	 * @return o codigo do ingrediente
	 */
	public Integer getCodigo() {
		return this.codigo;
	}

	/**
	 * Obtem o nome do ingrediente.
	 *
	 * @return o nome do ingrediente
	 */
	public String getNomeIngrediente() {
		return this.nomeIngrediente;
	}

	/**
	 * Obter o enum apartir de um int.
	 *
	 * @param codigo o codigo
	 * @return Ingrediente
	 */
	public static IngredienteEnum getValue(final Integer codigo) {
		if (codigo != null) {
			final IngredienteEnum[] values = IngredienteEnum.values();
			for (final IngredienteEnum codigo2 : values) {
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
