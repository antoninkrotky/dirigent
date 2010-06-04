package org.dirigent.metafacade.builder.ea;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dirigent.metafacade.builder.vo.DimensionVO;

public class EADimensionDao extends EADao<DimensionVO> {

	private EAObjectPropertyDAO objectPropertyDao = new EAObjectPropertyDAO();
	private EADimensionColumnDao columnsDao = new EADimensionColumnDao();

	@Override
	protected DimensionVO createVO(ResultSet res) throws SQLException {
		// TODO Auto-generated method stub
		DimensionVO v = new DimensionVO();
		v.name = res.getString(1);
		v.codeName = res.getString(2);
		v.description = res.getString(3);
		v.uri = res.getString(4);
		v.id = res.getLong(5);
		v.scdType = Integer.valueOf(objectPropertyDao.getObjectProperty(v.id,
				"slowlyChangingDimensionType")[1]);
		//TODO: set schema uri
		v.schemaUri="schema:default";
		v.columns = columnsDao.getColumns(v.id);
		return v;
	}

	public DimensionVO getDimension(String guid) {
		return findVO(
				"select Name,Alias,Note,ea_guid,Object_ID from t_object where ea_guid=?",
				new Object[] { guid });
	}

	

	@Override
	public void delete(DimensionVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(DimensionVO v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DimensionVO v) {
		Object[] o = new Object[] { v.name, v.codeName, v.description, v.uri };
		String sql = "UPDATE t_object SET \"name\"=?, \"alias\"=?, \"note\"=? WHERE ea_guid=?";
		int r=executeUpdate(sql, o);
		if (r != 1) {
				throw new RuntimeException("Update failed. " + r
						+ " rows updated. 1 row should be updated. SQL: " + sql + " Parameters: " + toList(o));				
			}
		columnsDao.merge(v,v.columns);
		
		
	}

	/*
	 * Repository r=EAHelper.getRepository();
	 * 
	 * 
	 * private DomainDao domainDao=new DomainDao();
	 * 
	 * public IDimension getDimension(String guid) { Element
	 * e=r.GetElementByGuid(guid);
	 * 
	 * DimensionVO v=new DimensionVO(); v.name=e.GetName();
	 * v.codeName=e.GetAlias();
	 * v.scdType=Integer.valueOf(e.GetTaggedValues().GetByName
	 * ("slowlyChangingDimensionType").GetValue()); v.uri=e.GetElementGUID();
	 * v.schemaUri=r.GetPackageByID(e.GetPackageID()).GetPackageGUID();
	 * v.columns=getDimensionColumns(e); v.description=e.GetNotes();
	 * 
	 * IDimension dimension=new DimensionDecorator(v);
	 * 
	 * return dimension; }
	 * 
	 * private Collection<DimensionColumnVO> getDimensionColumns(Element
	 * element) { ArrayList<DimensionColumnVO> l=new
	 * ArrayList<DimensionColumnVO>(); Iterator<Attribute>
	 * i=element.GetAttributes().iterator(); while (i.hasNext()) { Attribute
	 * a=i.next(); if ("BIDimensionColumn".equals(a.GetStereotype())) {
	 * DimensionColumnVO v=new DimensionColumnVO(); v.uri=a.GetAttributeGUID();
	 * v.name=a.GetName(); v.description=a.GetNotes();
	 * v.domain=domainDao.getDomain
	 * (r.GetElementByID(a.GetClassifierID()).GetElementGUID());
	 * v.scdColumnType=
	 * a.GetTaggedValues().GetByName("scdColumnType").GetValue(); l.add(v); } }
	 * return l; }
	 * 
	 * public void save(DimensionVO v) {
	 * 
	 * if (v.uri==null) { throw new
	 * RuntimeException("Creation of new Dimension not yet supported."); }
	 * Element e=r.GetElementByGuid(v.uri); e.SetName(v.name);
	 * e.SetAlias(v.codeName); e.SetNotes(v.description); TaggedValue
	 * tv=e.GetTaggedValues().GetByName("slowlyChangingDimensionType"); if
	 * (tv==null) { tv=e.GetTaggedValues().AddNew("slowlyChangingDimensionType",
	 * "TaggedValue"); } tv.SetValue(Integer.toString(v.scdType)); e.Update();
	 * tv.Update(); mergeDimensionColumns(v.columns, e); }
	 * 
	 * private void mergeDimensionColumns(Collection<DimensionColumnVO>
	 * c,Element e) { Iterator<Attribute> i=e.GetAttributes().iterator();
	 * Map<String,Attribute> m=new HashMap<String,Attribute>(); while
	 * (i.hasNext()) { Attribute a=i.next(); m.put(a.GetAttributeGUID(),a); }
	 * //merge columns Iterator<DimensionColumnVO> i2=c.iterator(); while
	 * (i2.hasNext()) { DimensionColumnVO v=i2.next(); Attribute
	 * a=m.remove(v.uri); if (a==null) { a=e.GetAttributes().AddNew(v.name,
	 * "Attribute"); } a.SetName(v.name); a.SetNotes(v.description);
	 * a.SetStereotype("BIDimensionColumn");
	 * a.SetClassifierID(r.GetElementByGuid(v.domain.uri).GetElementID());
	 * AttributeTag at=a.GetTaggedValues().GetByName("scdColumnType"); if
	 * (at==null) { at=a.GetTaggedValues().AddNew("scdColumnType", "String"); }
	 * at.SetValue(v.scdColumnType); a.Update(); at.Update(); } //delete missing
	 * columns i=m.values().iterator(); while (i.hasNext()) {
	 * i.next().destroy(); } e.Update(); }
	 */
}
