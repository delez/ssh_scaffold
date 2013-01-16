package appeon.aje.webengine.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.eclipse.jface.action.Action;

import appeon.aje.webengine.model.DomainExample;
import appeon.aje.webengine.struts.form.ModelForm;

public class ActionExample  extends Action {

    public static String SUCCESS = "success";
    public static String FAILURE = "failure";

    /**
     * Dispatch control to the "success" forward.
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        String action = request.getParameter("action");
        DomainExample model = ((ModelForm)form).getModel();
        if("new".equals(action)){
        	return doNew(model);
        }else if("list".equals(action)){
        	return doList(model);
        }else if("delete".equals(action)){
        	return doDelete(model);
        }else if("view".equals(action)){
        	return doView(model); 
        }else
        	return mapping.findForward(FAILURE);
    }
    protected ActionForward doNew(DomainExample model){
    	
    	return null;
    }
    protected ActionForward doList(DomainExample model){
    	return null;
    }
    protected ActionForward doDelete(DomainExample model){
    	return null;
    }
    protected ActionForward doView(DomainExample model){
    	return null;
    }
}