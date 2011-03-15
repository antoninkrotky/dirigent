package org.dirigent.metafacade;

import java.util.Collection;
import java.util.Map;

import org.dirigent.metafacade.builder.vo.VO;

public interface IElement {
	public String getUri();

	public String getName();

	public String getType();

	public String getStereotype();

	public Map<String, String> getProperties();



	public VO getValueObject();

	public IElement getParent();

	public Collection<IRelation> getStartingRelations();

	/**
	 * Search starting relations and return first relation of matching type and
	 * stereotype. Thhe order of searching realtions is not quaranted. So use
	 * this method for searches when only one relation of specified type and
	 * stereotype is expected.
	 * 
	 * @param type
	 *            Type of relation to search. If null, type of relation will not
	 *            be matched.
	 * @param stereotype
	 *            Stereotype of relation to search. If null, stereotype of
	 *            relation will not be matched.
	 * @return First relation with matching tzype and stereotype.
	 * */
	public IRelation getFirstStartingRelation(String type, String stereotype);

	public Collection<IRelation> getEndingRelations();

	/**
	 * Description of element.
	 * */
	public String getDescription();

	public String getAlias();

	public String getKeywords();
}
