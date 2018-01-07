package com.github.frankier.any23jena;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.List;

import org.apache.any23.Any23;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.junit.Test;

public class RDFaIntegrationTests {
	@Test
	public void testLoadRdfa() throws Exception {
		Model testModel = org.apache.jena.rdf.model.ModelFactory.createDefaultModel();
		JenaTripleHandler th = new JenaTripleHandler(testModel);
		
		final Any23 any23 = new Any23("html-rdfa11");
		URL rdfaDocUrl = RDFaIntegrationTests.class
				.getClassLoader().getResource("rdfa.html");
		any23.extract(rdfaDocUrl.toString(), th);

		Model refModel = ModelFactory.createDefaultModel() ;
		URL convRdfaDocUrl = RDFaIntegrationTests.class
				.getClassLoader().getResource("rdfa.ttl");
		refModel.read(convRdfaDocUrl.toString());

		List<Statement> testStatements = testModel.listStatements().toList();
		List<Statement> refStatements = refModel.listStatements().toList();
		try {
			assertThat(refStatements, containsInAnyOrder(testStatements.toArray()));
		} catch (AssertionError e) {
			System.err.println("Test statements");
			System.err.println(testStatements);
			System.err.println("Ref statements");
			System.err.println(refStatements);
			throw e;
		}
	}
}
