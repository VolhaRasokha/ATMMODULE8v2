package pages;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftPage extends AccountPage {
	
	public DraftPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[contains(@href,'https://e.mail.ru/compose/')]")
	private List<WebElement> draftMails;

	public String getDraftMailSubject(int index) {
		return draftMails.get(index).getAttribute("data-subject");
	}

	public CreateEmailPage openDraftMail(int index) {
		WebElement draftMail = draftMails.get(index);
		draftMail.click();
		return new CreateEmailPage(driver);
	}
}
