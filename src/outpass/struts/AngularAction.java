package outpass.struts;

import com.opensymphony.xwork2.Action;

import outpass.model.PersonData;

public class AngularAction implements Action {

    private PersonData personData = new PersonData();

    public String execute() {

            personData.setFirstName("Mohaideen");
            personData.setLastName("Jamil");
            return SUCCESS;
    }

    public PersonData getPersonData() {
            return personData;
    }

    public void setPersonData(PersonData personData) {
            this.personData = personData;
    }
}