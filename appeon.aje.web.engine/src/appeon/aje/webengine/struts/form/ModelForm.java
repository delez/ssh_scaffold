package appeon.aje.webengine.struts.form;

import org.apache.struts.action.ActionForm;

import appeon.aje.webengine.model.DomainExample;

public class ModelForm extends ActionForm{
	private DomainExample model;
	public ModelForm(DomainExample model){
		this.model = model;
	}
	public DomainExample getModel(){
		return model;
	}

}
