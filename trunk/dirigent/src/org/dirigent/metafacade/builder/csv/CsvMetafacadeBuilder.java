package org.dirigent.metafacade.builder.csv;

import java.util.HashMap;

import org.dirigent.metafacade.Element;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.decorator.MappingDecorator;
import org.dirigent.metafacade.builder.decorator.SchemaDecorator;
import org.dirigent.metafacade.builder.decorator.TableDecorator;
import org.dirigent.metafacade.builder.vo.MappingVO;
import org.dirigent.metafacade.builder.vo.SchemaVO;
import org.dirigent.metafacade.builder.vo.TableVO;

/**
 * Implementation of MetafacadeBuilder over model stored in CSV files. See
 * dirigent-test/resources/model directory and unit test for further
 * information. This implementation should be used only for dirigent development
 * and evaluation purposes.
 * */
public class CsvMetafacadeBuilder extends MetafacadeBuilder {

	private HashMap<String, Element> elements = new HashMap<String, Element>();
	private TableDao tableDao = new TableDao();
	private MappingDao mappingDao = new MappingDao();
	private SchemaDao schemaDao = new SchemaDao();

	
	@Override
	public Element getMetafacade(String uri) {
		Element element = elements.get(uri);
		if (element == null) {
			element = getSchema(uri);
		}
		if (element == null) {
			element = getMapping(uri);
		}
		if (element == null) {
			element = getTable(uri);
		}

		return element;
	}

	private Element getTable(String uri) {
		TableVO v = tableDao.getTable(uri);
		if (v != null) {
			return new TableDecorator(v);
		}
		return null;
	}

	private Element getMapping(String uri) {
		MappingVO m = mappingDao.getMapping(uri);
		if (m != null) {
			return new MappingDecorator(m);
		}
		return null;
	}

	private Element getSchema(String uri) {
		SchemaVO s = schemaDao.getSchema(uri);
		if (s != null) {
			return new SchemaDecorator(s);
		}
		return null;
	}

}
