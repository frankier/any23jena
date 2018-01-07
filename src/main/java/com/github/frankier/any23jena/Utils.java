package com.github.frankier.any23jena;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.eclipse.rdf4j.model.Value;
import org.apache.jena.datatypes.TypeMapper;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.IRI;

public class Utils {
	public static Node valueToNode(Value val) throws UnknownValueTypeException {
		/**
		 * Convert a RDF4J Value to a Jena Node.
		 */
		if (val instanceof IRI) {
			return NodeFactory.createURI(val.toString());
		} else if (val instanceof Literal) {
			TypeMapper tm = TypeMapper.getInstance();
			Literal litval = (Literal)val;
			return NodeFactory.createLiteral(
					litval.getLabel(),
					litval.getLanguage().orElse(null),
					tm.getSafeTypeByName(litval.getDatatype().toString()));
		} else {
			throw new UnknownValueTypeException(val.getClass());
		}
	}

}
