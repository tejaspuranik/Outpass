package outpass.struts;

import org.apache.commons.lang3.StringUtils;

import outpass.model.User;
import outpass.service.RegisterService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class RegisterAction extends ActionSupport implements ModelDriven{
	private static String SUCCESS="success";
	private User user = new User();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void validate()
	{
		if(StringUtils.isEmpty(user.getEmail()))
		{
			addFieldError("userId","User Id cant be blank");
		}
	}
	
	public String execute()
	{
		System.out.println("email in reg action");
		System.out.println(user.getEmail());
		RegisterService loginService = new RegisterService();
		System.out.println("email in reg action");
		System.out.println(user.getEmail());
		if(loginService.verifyLogin(user))
		{
			return SUCCESS;
		}
		return LOGIN;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
