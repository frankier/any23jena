# any23jena

This tiny library provides a bridge to allow Any23 to load triples into a Jena
Model/Graph. This can be used to support formats Jena doesn't support natively
(e.g. RDFa).

## Installation

This library is available on jCenter. See: https://bintray.com/frankier/maven/any23jena

## Example

You can use it like so::

    Model myModel = org.apache.jena.rdf.model.ModelFactory.createDefaultModel();
    JenaTripleHandler tripleHandler = new JenaTripleHandler(myModel);

    final Any23 any23 = new Any23("html-rdfa11");
    any23.extract("file:///path/to/rdfa.html", tripleHandler);
    
    // myModel now contains your triples
