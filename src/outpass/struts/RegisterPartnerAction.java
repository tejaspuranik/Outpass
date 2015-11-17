package outpass.struts;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import outpass.model.Feedback;
import outpass.model.Partner;
import outpass.service.RegisterPartnerService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class RegisterPartnerAction extends ActionSupport implements ModelDriven{
	private static String SUCCESS="success";
	private List<Partner> data;
	public List<Partner> getData() {
		return data;
	}

	public void setData(List<Partner> data) {
		this.data = data;
	}

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
		if(data.size()>0)
		{
			partner.setEmail(data.get(0).getEmail());
			partner.setFullname((data.get(0).getFullname()));
			partner.setOrganization((data.get(0).getOrganization()));
		}
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
