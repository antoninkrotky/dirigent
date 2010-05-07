package org.dirigent.metafacade.builder.ea;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.dirigent.metafacade.builder.vo.DomainVO;


public class EADomainDao extends EADao<DomainVO> {

	private EAObjectPropertyDAO objectPropertyDao=new EAObjectPropertyDAO();
	
	@Override
	protected DomainVO createVO(ResultSet res) throws SQLException {
		DomainVO v=new DomainVO();
		v.name=res.getString(1);
		v.uri=res.getString(2);
		v.id=res.getLong(3);
		v.dataType=objectPropertyDao.getObjectProperty(v.id, "dataType");		
		return v;
	}
	
	public DomainVO getDomain(long id) {
		return findVO("select name,ea_guid,Object_ID from t_object where Object_ID=?",new Object[]{new BigDecimal(id)});
	}

	public Collection<DomainVO> getDomains() {
		return findVOs("select name,ea_guid,Object_ID from t_object where Object_Type='Class' and Stereotype='BIDomain'");
	}

	@Override
	public void delete(DomainVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(DomainVO v) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(DomainVO v) {
		// TODO Auto-generated method stub
		
	}

	/*Repository r=EAHelper.getRepository();
	
	public DomainVO getDomain(String guid) {
		Element e=r.GetElementByGuid(guid);		
		return createDomain(e);
	}
	
	private DomainVO createDomain(Element e) {
		DomainVO v=new DomainVO();
		v.name=e.GetName();
		v.uri=e.GetElementGUID();
		v.dataType=e.GetTaggedValues().GetByName("dataType").GetValue();
		return v;
	}

	public Collection<DomainVO> getDomains() {
		//Iterator<Element> i=r.GetModels().GetByName("Model").GetPackages().GetByName("Domény").GetElements().iterator();
		Iterator<Element> i=r.GetPackageByGuid("{563B0B3B-2D23-43dc-907D-29C37524C2AC}").GetElements().iterator();		
		ArrayList<DomainVO> l=new ArrayList<DomainVO>();
		while (i.hasNext()) {
			l.add(createDomain(i.next()));			
		}
		return l;
	}*/
	
	
}
