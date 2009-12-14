package org.dirigent.metafacade;
/**
 * Enumeration of slowly changing dimension column types.
 * */
public enum SCDType {
	SurrogateKey,
	NaturalKey,
	OverwriteOnChange,
	AddRowOnChange
}
