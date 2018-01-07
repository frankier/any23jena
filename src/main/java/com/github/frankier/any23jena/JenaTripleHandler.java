package com.github.frankier.any23jena;

import static com.github.frankier.any23jena.Utils.valueToNode;

import org.apache.any23.extractor.ExtractionContext;
import org.apache.any23.writer.TripleHandler;
import org.apache.any23.writer.TripleHandlerException;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Value;

public class JenaTripleHandler implements TripleHandler {
	/**
	 * Acts as an Any23 TripleHandler and writes/converts triples to a Jena Model or Graph.
	 */

	private Graph graph;

	public JenaTripleHandler(Model m) {
		this(m.getGraph());
	}

	public JenaTripleHandler(Graph g) {
		graph = g;
	}
	
	@Override
	public void receiveTriple(Resource subject, IRI predicate, Value object, IRI graph_iri, ExtractionContext context)
			throws TripleHandlerException {
		try {
			graph.add(new Triple(
				valueToNode(subject),
				valueToNode(predicate),
				valueToNode(object)
			));
		} catch(UnknownValueTypeException e) {
			throw new TripleHandlerException("Error while converting triple", e);
		}
	}

	// Stubs

	@Override
	public void startDocument(IRI documentIRI) throws TripleHandlerException {}

	@Override
	public void openContext(ExtractionContext context) throws TripleHandlerException {}

	@Override
	public void receiveNamespace(String prefix, String uri, ExtractionContext context) throws TripleHandlerException {}

	@Override
	public void closeContext(ExtractionContext context) throws TripleHandlerException {}

	@Override
	public void endDocument(IRI documentIRI) throws TripleHandlerException {}

	@Override
	public void setContentLength(long contentLength) {}

	@Override
	public void close() throws TripleHandlerException {}
	
}