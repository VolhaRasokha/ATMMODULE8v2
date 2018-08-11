package services;

import org.testng.Reporter;

import pages.AccountPage;
import pages.BasePage;
import pages.BasketPage;
import pages.CreateEmailPage;
import pages.DraftPage;
import pages.HomePage;
import pages.IncomingPage;
import utils.WebDriverSingleton;
import bo.Email;
import bo.User;

public class EmailService {

	public boolean login(User user, String text) {
		Reporter.log("Login to mail.ru by: " + user, 2, true);
		HomePage homePage = new HomePage(
				WebDriverSingleton.getWebDriverInstance());
		homePage.setUserName(user);
		homePage.setUserPassword(user);
		AccountPage accountPage = homePage.clickSubmitBtn();
		return accountPage.isTextPresentOnPage(text);
	}
// 	according to code naming convention if a method returns a boolean value, its name should start from is, has etc. E.g. isLogInSuccessful

	public void createEmail(Email email) {
		Reporter.log("Email creation is started...");
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		CreateEmailPage createEmailPage = accountPage.clickMailCreationBtn();
		createEmailPage.fillMailAddress(email.getRecipient());
		createEmailPage.fillMailSubject(email.getSubject());
		createEmailPage.fillMailBody(email.getTextBody());
		createEmailPage.clickSaveDraftBtn();
	}

	public boolean checkEmailInDraftFolder(Email email) {
// 		isEmailPresentInDraftsFolder
		Reporter.log("Check if email exists in Draft folder...");
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		DraftPage draftPage = accountPage.clickMailDraftMenuLink();
		return draftPage.isTextPresentOnPage(email.getSubject());
	}

	public String getIncomingEmailSubject(int index) {
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		IncomingPage incomingPage = accountPage.clickMailIncomingMenuLink();
		String actualSubject = incomingPage.getIncomingMailSubject(index);
		return actualSubject;
	}

	public void openDraftEmail(int index) {
		Reporter.log("Open draft email...");
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		DraftPage draftPage = accountPage.clickMailDraftMenuLink();
		draftPage.openDraftMail(index);
	}

	public boolean sendEmail(Email email) {
		Reporter.log("Email is sent..", 2, true);
		CreateEmailPage createEmailPage = new CreateEmailPage(
				WebDriverSingleton.getWebDriverInstance());
		AccountPage accountPage = createEmailPage.clickMailSendBtn();
		return accountPage.isElementPresent(accountPage.mailSentTitle);
// 		this method can be void and accountPage.isElementPresent(accountPage.mailSentTitle); can be removed, since it's never checked in tests
	}

	public void refreshPage() {
		BasePage basePage = new CreateEmailPage(
				WebDriverSingleton.getWebDriverInstance());
		basePage.refresh();
	}

	public boolean checkEmailInSentFolder(Email email) {
// 		isEmailPresentInSentFolder
		Reporter.log("Check if email is in sent folder...");
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		accountPage.clickMailSentMenuLink();
		return accountPage.isTextPresentOnPage(email.getSubject());
	}

	public boolean checkEmailInIncomingFolder(Email email) {
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		accountPage.clickMailIncomingMenuLink();
		return accountPage.isTextPresentOnPage(email.getSubject());
// 		this method is not used in tests, can be removed
	}

	public void deleteIncomingMail(int indexOfemail) {
		IncomingPage incomingPage = new IncomingPage(
				WebDriverSingleton.getWebDriverInstance());
		incomingPage.deleteIncomingMail(indexOfemail);
	}

	public boolean checkEmailInIncomingFolderBySubject(
// 		isEmailPresent...
			String subjectDeleteIncomingMail) {
		IncomingPage incomingPage = new IncomingPage(
				WebDriverSingleton.getWebDriverInstance());
		return incomingPage.isTextPresentOnPage(subjectDeleteIncomingMail);
	}

	public boolean checkSubjectlInBasket(String subjectDeleteIncomingMail) {
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		refreshPage();
// 		please remove refreshPage from this method and call it as a separate service method from a test. The same should be done for the next methods with refresh
		BasketPage basketPage = accountPage.clickBasketMenuLink();
		return basketPage.isTextPresentOnPage(subjectDeleteIncomingMail);
	}

	public String getDeletedMailSubject(int index) {
		AccountPage accountPage = new AccountPage(
				WebDriverSingleton.getWebDriverInstance());
		refreshPage();
		BasketPage basketPage = accountPage.clickBasketMenuLink();
		return basketPage.getDeleteMailSubject(index);
	}

	public void moveEmailFromBasketToDraft(int index) {
		BasketPage basketPage = new BasketPage(
				WebDriverSingleton.getWebDriverInstance());
		refreshPage();
		basketPage.dragNDropMailFromBasketToDraft(index);
	}

	public boolean checkEmailInDraftFolderBySubject(String subjectOfDeletedMail) {
		DraftPage draftPage = new DraftPage(
				WebDriverSingleton.getWebDriverInstance());
		return draftPage.isTextPresentOnPage(subjectOfDeletedMail);
	}

	public void logOut() {
		new AccountPage(WebDriverSingleton.getWebDriverInstance())
				.clickLogOut();
	}
}
