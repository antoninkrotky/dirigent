package org.dirigent.metafacade;

import java.util.Collection;
import java.util.Map;

import org.dirigent.metafacade.builder.vo.MappingSourceVO;

public interface IMapping extends IElement,IGeneratable,ISchemaProvider, IQueriable {
	public ISchema getSchema();
	public ITable getTargetTable();
	public Map<MappingSourceVO, IElement>getSources();
	public Collection<IColumnMapping> getColumnMappings();
	public String getTargetColumnList();
	public String injectSubqueries(String template);
}
