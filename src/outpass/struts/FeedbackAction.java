package outpass.struts;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import outpass.model.Feedback;
import outpass.model.SendMail;
import outpass.model.User;
import outpass.service.FeedbackService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FeedbackAction extends ActionSupport implements ModelDriven{
	private static String SUCCESS="success";
	private List<Feedback> data;
	public List<Feedback> getData() {
		return data;
	}

	public void setData(List<Feedback> data) {
		this.data = data;
	}

	private Feedback feedback = new Feedback();
	
	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}


	public void validate()
	{
		if(StringUtils.isEmpty(feedback.getEmail()))
		{
			addFieldError("userId","Email Id cant be blank");
		}
		if(StringUtils.isEmpty(feedback.getFullname()))
		{
			addFieldError("userId","Full Name cant be blank");
		}
		if(StringUtils.isEmpty(feedback.getMessage()))
		{
			addFieldError("userId","Message cant be blank");
		}
	}
	
	public String execute()
	{
		if(data.size()>0)
		{
			feedback.setEmail(data.get(0).getEmail());
			feedback.setFullname(data.get(0).getFullname());
			feedback.setMessage(data.get(0).getMessage());
		}
		FeedbackService feedbackService = new FeedbackService();
		if(feedbackService.verifyFeedback(feedback))
		{
			String from=data.get(0).getEmail();
			String to="contact@outpass.co";
			String subject="Talk to us";
			String text=data.get(0).getMessage();
			SendMail sendMail = new SendMail(from,to,subject,text);
			return SUCCESS;
		}
		return LOGIN;
	}

	@Override
	public Feedback getModel() {
		// TODO Auto-generated method stub
		return feedback;
	}

}
