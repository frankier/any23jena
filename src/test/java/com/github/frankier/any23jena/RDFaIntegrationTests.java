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
		Model test_model = org.apache.jena.rdf.model.ModelFactory.createDefaultModel();
		JenaTripleHandler th = new JenaTripleHandler(test_model);
		
		final Any23 any23 = new Any23("html-rdfa11");
		URL rdfaDocUrl = RDFaIntegrationTests.class
				.getClassLoader().getResource("rdfa.html");
		any23.extract(rdfaDocUrl.toString(), th);

		Model ref_model = ModelFactory.createDefaultModel() ;
		URL convRdfaDocUrl = RDFaIntegrationTests.class
				.getClassLoader().getResource("rdfa.ttl");
		ref_model.read(convRdfaDocUrl.toString());

		List<Statement> test_statements = test_model.listStatements().toList();
		List<Statement> ref_statements = ref_model.listStatements().toList();
		try {
			assertThat(ref_statements, containsInAnyOrder(test_statements.toArray()));
		} catch (AssertionError e) {
			System.err.println("Test statements");
			System.err.println(test_statements);
			System.err.println("Ref statements");
			System.err.println(ref_statements);
			throw e;
		}
	}
}
