package auctionSite.controllers;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import auctionSite.DAOs.ListingDAOImpl;
import auctionSite.DAOs.UserMethodDAOImpl;
import auctionSite.entities.Logging;
import auctionSite.entities.OldListing;
import auctionSite.entities.User;

@Controller
public class AccountController {
	@Autowired
	private UserMethodDAOImpl userDao;
	@Autowired
	private ListingDAOImpl listDao;

	public AccountController(UserMethodDAOImpl userDao, ListingDAOImpl listDao) {
		super();
		this.userDao = userDao;
		this.listDao = listDao;
	}

	public AccountController() {
	}

	@RequestMapping("/user/updateInfo")
	public String goToUpdateInfo(Model model, HttpSession session) {
		User user = new User();
		model.addAttribute("user", user);
		return "user/UpdateInfo";
	}

	@RequestMapping("/user/doUpdateInfo")
	public String doUpdateInfo(User userInf, HttpServletRequest request, HttpSession session)
			throws PersistenceException {
		User userCur = (User) session.getAttribute("user");
		if (userCur.getPassword().equals(userInf.getPassword())) {
			userDao.updateUser(userInf);

		} else {
			request.setAttribute("Incorrect", "Oops the password entered is incorrect!");
			Logging.Log("info", userCur.getUsername()
					+ " attempted to updated account info but the password was incorrect, redirected to the UpdateInfo page");
			return "user/UpdateInfo";

		}
		request.setAttribute("UpdatedInfo", "Your profile information has been updated");
		Logging.Log("info", userInf.getUsername() + " updated account info");
		return "redirect:/user/account";
	}

	@RequestMapping("/user/passwordUpdate")
	public String doPasswordUpdate(HttpServletRequest request, HttpSession session) {
		String oldPassword = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String confNewPassword = request.getParameter("confNewPassword");
		User user = (User) session.getAttribute("user");
		if (oldPassword.equals(user.getPassword())) {
			if (newPassword.equals(confNewPassword)) {
				request.setAttribute("UpdatedPass", "Your password has been succesfully changed");
				user.setPassword(newPassword);
				userDao.updateUser(user);
				Logging.Log("info", user.getUsername() + " updated password");
				return "user/Account";
			} else {
				request.setAttribute("passNotMatch", "The two new passwords you entered do not match!");
				Logging.Log("info", user.getUsername()
						+ " attempted to change password but the two new passwords were different, redirected to the UpdateInfo page");
				return "user/UpdateInfo";
			}
		} else {
			request.setAttribute("incorrectPass", "The password you entered is not correct");
			Logging.Log("info", user.getUsername()
					+ " attempted to change password but the current password was wrong, redirected to the UpdateInfo page");
			return "user/UpdateInfo";
		}
	}

	@RequestMapping("/user/pay")
	public String pay(@RequestParam int id) {
		OldListing oldListing = listDao.getOldListing(id);
		oldListing.setPaid("paid");
		listDao.updateOldListing(oldListing);
		return "redirect:/user/account";
	}

}
