/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.TextParseUtil;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author rodrigo
 */
public class AuthenticationInterceptor  implements Interceptor{

    public static final String authenticationSessionField = new String("authenticated");
    private static final String authenticationRequiredResult = "authentication_required";
    private Set excludeActions = Collections.EMPTY_SET;
	
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
	Map session = invocation.getInvocationContext().getSession();
	String actionName = invocation.getProxy().getActionName();
        
        /*
        if(invocation.getAction() instanceof AuthenticationAware){
            AuthenticationAware action = ((AuthenticationAware) invocation.getAction());
            action.setActionsWithoutAuthentication(excludeActions);
        }
	*/	
	Object authenticationObject = session.get(authenticationSessionField);
		
	if(excludeActions.contains(actionName) || 
        	(authenticationObject!=null && authenticationObject instanceof Boolean &&
		authenticationObject.equals(Boolean.TRUE))) {
			
                	return invocation.invoke();	
	}
		
	return authenticationRequiredResult;		
    }

	public void setExcludeActions(String values) {
		if (values != null) {
			this.excludeActions = TextParseUtil.commaDelimitedStringToSet(values);
		}
	}

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
