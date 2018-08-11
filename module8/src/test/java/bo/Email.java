package bo;

public class Email {
	
	private String recipient;
	private String subject;
	private String textBody;
	
	public Email(String recipient, String subject, String textBody){
		this.recipient = recipient;
		this.subject = subject;
		this.textBody = textBody;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getSubject() {
		return subject;
	}

	public String getTextBody() {
		return textBody;
	}

}
// Please add "setters"
