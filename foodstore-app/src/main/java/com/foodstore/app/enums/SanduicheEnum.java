package com.foodstore.app.enums;

/**
 * Classe Enum de Ingrediente.
 */
public enum SanduicheEnum {

	X_BACON(1, "X-BACON", "Hambúrguer de carne, queijo e bacon."),
	X_BURGER(2, "X-BURGER", "Hambúrguer de carne e queijo."),
	X_EGG(3, "X-EGG", "Hambúrguer de carne, queijo e ovo."),
	X_EGG_BACON(4, "X-EGG BACON", "Hambúrguer de carne, queijo, bacon e ovo.");

	private Integer codigo;
	private String nomeSanduiche;
	private String descricao;

	/**
	 * Instancia um novo ingrediente.
	 *
	 * @param codigo o codigo
	 */
	private SanduicheEnum(final Integer codigo, final String nomeSanduiche, final String descricao) {
		this.codigo = codigo;
		this.nomeSanduiche = nomeSanduiche;
		this.descricao = descricao;
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
	 * Obtém o nome do sanduiche.
	 *
	 * @return o nome do sanduiche
	 */
	public String getNomeSanduiche() {
		return this.nomeSanduiche;
	}

	/**
	 * Obtém a descricao do sanduiche.
	 *
	 * @return a descricao do sanduiche
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Obter o enum apartir de um int.
	 *
	 * @param codigo o codigo
	 * @return Ingrediente
	 */
	public static SanduicheEnum getValue(final Integer codigo) {
		if (codigo != null) {
			final SanduicheEnum[] values = SanduicheEnum.values();
			for (final SanduicheEnum codigo2 : values) {
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
