package org.dirigent.metafacade.builder.csv;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IElement;
import org.dirigent.metafacade.IRelation;
import org.dirigent.metafacade.builder.MetafacadeBuilder;
import org.dirigent.metafacade.builder.decorator.MappingDecorator;
import org.dirigent.metafacade.builder.decorator.SchemaDecorator;
import org.dirigent.metafacade.builder.decorator.TableDecorator;
import org.dirigent.metafacade.builder.vo.MappingVO;
import org.dirigent.metafacade.builder.vo.ObjectVO;
import org.dirigent.metafacade.builder.vo.SchemaVO;
import org.dirigent.metafacade.builder.vo.TableVO;

/**
 * Implementation of MetafacadeBuilder over model stored in CSV files. See
 * dirigent-test/resources/model directory and unit test for further
 * information. This implementation should be used only for dirigent development
 * and evaluation purposes.
 * */
public class CsvMetafacadeBuilder extends MetafacadeBuilder {

	private HashMap<String, IElement> elements = new HashMap<String, IElement>();

	private TableDao tableDao;
	private MappingDao mappingDao;
	private SchemaDao schemaDao;

	public CsvMetafacadeBuilder() {
		tableDao = new TableDao();
		mappingDao = new MappingDao();
		schemaDao = new SchemaDao();
	}

	@Override
	public IElement getMetafacade(String uri) {
		IElement element = elements.get(uri);
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

	private IElement getTable(String uri) {
		TableVO v = tableDao.getTable(uri);
		if (v != null) {
			return new TableDecorator(v);
		}
		return null;
	}

	private IElement getMapping(String uri) {
		MappingVO m = mappingDao.getMapping(uri);
		if (m != null) {
			return new MappingDecorator(m);
		}
		return null;
	}

	private IElement getSchema(String uri) {
		SchemaVO s = schemaDao.getSchema(uri);
		if (s != null) {
			return new SchemaDecorator(s);
		}
		return null;
	}

	@Override
	public void save(IElement element) {
		throw new RuntimeException("Operation save is not supported on "
				+ this.getClass().getName());
	}


	@Override
	public Collection<ObjectVO> getChildObjects(String uri) {
		throw new RuntimeException(
				"Operation getChildObjects is not supported on "
						+ this.getClass().getName());
	}

	@Override
	public Vector<IElement> getChildElements(String uri) {
		throw new RuntimeException(
				"Operation getChildElements is not supported on "
						+ this.getClass().getName());
	}

	@Override
	public Collection<IAttribute> getAttributes(String elementURI) {
		throw new RuntimeException(
				"Operation getAttributes is not supported on "
						+ this.getClass().getName());
	}

	@Override
	public Collection<IRelation> getEndingRelations(String elementUri) {
		throw new RuntimeException(
				"Operation getEndingRelations is not supported on "
						+ this.getClass().getName());
	}

	@Override
	public Collection<IRelation> getStartingRelations(String elementUri) {
		throw new RuntimeException(
				"Operation getStartingRelations is not supported on "
						+ this.getClass().getName());
	}

	/* (non-Javadoc)
	 * @see org.dirigent.metafacade.builder.MetafacadeBuilder#clearCache()
	 */
	@Override
	public void clearCache() {
		// TODO Auto-generated method stub
		
	}



}
