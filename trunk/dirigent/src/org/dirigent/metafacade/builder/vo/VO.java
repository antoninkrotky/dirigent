package org.dirigent.metafacade.builder.vo;

public class VO {
	public String uri;
	@Override
	public int hashCode() {
		return uri.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof VO) {
			return uri.equals(((VO)obj).uri);
		}
		return false;
	}
}
