package br.com.eng2d.superficie.builder;

import br.com.eng2d.superficie.Manipulador;
import br.com.eng2d.superficie.Superficie;

//Padrão de projeto builder
public abstract class Builder extends Manipulador {

	public Builder(Superficie superficie) {
		super(superficie);
	}
}