package outpass.struts;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import outpass.model.SendMail;
import outpass.model.User;
import outpass.service.RegisterService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class RegisterAction extends ActionSupport implements ModelDriven{
	private static String SUCCESS="success";
	private User user = new User();
	private List<User> data;
	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}

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
		if(data.size()>0)
			user.setEmail(data.get(0).getEmail());
		RegisterService loginService = new RegisterService();
		if(loginService.verifyLogin(user))
		{
			String from="tejaspuranik11@gmail.com";
			String to=data.get(0).getEmail();
			String subject="For being on the list";
			String text="Hello ,"+"\n"+"Thanks for your interest in OutPass.  We are excited to welcome you on board as an"
					+ " early member of the growing OutPass community! We’ll keep you updated on the latest news and progress "
					+ "through this email so you can be one of the first people to enjoy OutPass once we launch! Get ready to party like never before. "
					+ "OutPass is about to transform your nights!Cheers,Team OutPass Salut,Merci de votre intérêt pour OutPass."
					+ " Nous sommes fiers de vous accueillir dans notre communauté et de faire grandir OutPass avec vous."
					+ " Vous serez avertis des nouveautés et changements via cette adresse email afin de faire partie des premiers utilisateurs "
					+ "profitant de l’expérience OutPass.Préparez vous à faire la fête comme jamais auparavant."
					+ " OutPass arrive pour boulverser vos nuits! L’équipe OutPass";
			SendMail sendMail = new SendMail(from,to,subject,text);
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
