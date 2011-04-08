package org.dirigent.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Set dirigent config name if config parameter exists in request.
 * */
public class ConfigFilter implements javax.servlet.Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		String configName = arg0.getParameter("config");
		if (configName != null) {
			((HttpServletRequest) arg0).getSession().setAttribute("configName",
					configName);
		} else {
			configName = (String)((HttpServletRequest) arg0).getSession().getAttribute(
					"configName");
			if (configName==null) {
				configName="default";
			}
		}
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
