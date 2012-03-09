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

	/**
	 * Get parent element in model hierarchy. Parent element is typically package or folder.
	 * */
	public IElement getParent();
	
	/**
	 * Get parent element from generalisation relation.
	 * */
	public IElement getGeneralizedParent();
	


	public Collection<IRelation> getStartingRelations();
	
	public Collection<IRelation> getStartingRelations(boolean inherit);
	
	/**
	 * Get starting relations matching specified type and stereotype.
	 * @param type Type of relations to return. If null, type of relation will not
	 *            be matched.
	 * @param stereotype
	 *            Stereotype of relations to return. If null, stereotype of
	 *            relation will not be matched.
	 * @param includeGeneralizedRelations If true return also generalized relations.           
	 *            
	 * */
	public Collection<IRelation> getStartingRelations(String type, String stereotype,boolean includeGeneralizedRelations);

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
	
	public String getStatus();
}
