package outpass.struts;

import org.apache.commons.lang3.StringUtils;

import outpass.model.Partner;
import outpass.service.RegisterPartnerService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class RegisterPartnerAction extends ActionSupport implements ModelDriven{
	private static String SUCCESS="success";
	private Partner partner = new Partner();
	
	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}


	public void validate()
	{
		if(StringUtils.isEmpty(partner.getEmail()))
		{
			addFieldError("userId","Email Id cant be blank");
		}
		if(StringUtils.isEmpty(partner.getFullname()))
		{
			addFieldError("userId","Full Name cant be blank");
		}
		if(StringUtils.isEmpty(partner.getOrganization()))
		{
			addFieldError("userId","Organization cant be blank");
		}
	}
	
	public String execute()
	{
		RegisterPartnerService registerService = new RegisterPartnerService();
		if(registerService.verifyRegister(partner))
		{
			return SUCCESS;
		}
		return LOGIN;
	}

	@Override
	public Partner getModel() {
		// TODO Auto-generated method stub
		return partner;
	}

}
