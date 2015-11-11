package outpass.struts;

import org.apache.commons.lang3.StringUtils;

import outpass.model.Feedback;
import outpass.service.FeedbackService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FeedbackAction extends ActionSupport implements ModelDriven{
	private static String SUCCESS="success";
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
		FeedbackService feedbackService = new FeedbackService();
		if(feedbackService.verifyFeedback(feedback))
		{
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
