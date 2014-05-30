package org.dirigent.metafacade.builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import org.dirigent.metafacade.builder.vo.VO;

/**
 * Abstract parent data access object (DAO) class. Implementations of metafacade
 * builders using database reporsitory, can use this class as parent for their
 * DAO classes.
 * */
public abstract class AbstractDao<V> {

	protected AbstractDao() {

	}

	/**
	 * 
	 * @return connection identifier
	 * @throws RuntimeException
	 *             if unable to create connection
	 */
	protected Connection getConnection() {
		return DAOHelper.instance().getConnection();
	}

	protected abstract V createVO(ResultSet res) throws SQLException;

	protected V findVO(String query) {
		return findVO(query, null);
	}

	private PreparedStatement createPreparedStatement(String query,
			Object[] parameters) throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement(query);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null) {
					stmt.setObject(i + 1, parameters[i]);
				} else {
					stmt.setNull(i + 1, Types.INTEGER);
				}
			}
		}
		return stmt;
	}

	protected int executeUpdate(String sql, Object[] params) {
		try {
			PreparedStatement stmt = createPreparedStatement(sql, params);
			int r = stmt.executeUpdate();
			stmt.close();
			return r;
		} catch (SQLException e) {
			throw new RuntimeException("Unable to execute statement " + sql
					+ " Parameters: " + toList(params), e);
		}
	}

	protected String toList(Object[] params) {
		if (params.length == 0) {
			return "[]";
		}
		StringBuffer sb = new StringBuffer('[');
		for (int i = 0; i < params.length; i++) {
			sb.append(params[i] == null ? null : params[i].toString());
			if (i != params.length - 1) {
				sb.append(',');
			}
		}
		sb.append(']');
		return sb.toString();
	}

	protected V findVO(String query, Object[] parameters) {
		try {
			PreparedStatement stmt = createPreparedStatement(query, parameters);
			ResultSet res = stmt.executeQuery();
			V v = null;
			if (res.next()) {
				v = createVO(res);
			}
			res.close();
			stmt.close();
			return v;
		} catch (Exception e) {
			throw new RuntimeException("Exception executin query: " + query
					+ ". Parameters: " + Arrays.toString(parameters), e);
		}
	}

	protected Collection<V> findVOs(String query) {
		return findVOs(query, null);
	}

	protected Collection<V> findVOs(String query, Object[] parameters) {

		try {
			PreparedStatement stmt = createPreparedStatement(query, parameters);
			ResultSet res = stmt.executeQuery();
			ArrayList<V> r = new ArrayList<V>();
			while (res.next()) {
				r.add(createVO(res));
			}
			res.close();
			stmt.close();
			return r;
		} catch (Exception e) {
			throw new RuntimeException("Exception executin query: " + query, e);
		}
	}

	protected boolean isNew(V v) {
		return ((VO) v).uri == null;
	}

	public void merge(V v) {
		if (isNew(v)) {
			insert(v);
		} else {
			update(v);
		}
	}

	public abstract void insert(V v);

	public abstract void update(V v);

	public abstract void delete(V v);

	public void delete(Collection<V> v) {
		Iterator<V> i = v.iterator();
		while (i.hasNext()) {
			delete(i.next());
		}
	}

	public void merge(Collection<V> v) {
		Iterator<V> i = v.iterator();
		while (i.hasNext()) {
			merge(i.next());
		}
	}

	protected String generateGUID() {
		return '{' + UUID.randomUUID().toString() + '}';
	}

}
