package org.dirigent.metafacade.builder.vo;

public class VO {
	public String uri;
	public long id;
	@Override
	public int hashCode() {
		return uri.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		
		if (obj!=null && obj instanceof VO) {
			return (uri!=null && uri.equals(((VO)obj).uri));
		}
		return false;
	}
}
