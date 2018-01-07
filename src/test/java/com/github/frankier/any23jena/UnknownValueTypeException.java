package com.github.frankier.any23jena;

import org.eclipse.rdf4j.model.Value;

public class UnknownValueTypeException extends Exception {
	/**
	 * Thrown when
	 */
	private static final long serialVersionUID = -7657801632079876093L;

	UnknownValueTypeException(Class<? extends Value> unknownCls) {
		super("Unknown RDF4J Value type: " + unknownCls.getName());
	}
}