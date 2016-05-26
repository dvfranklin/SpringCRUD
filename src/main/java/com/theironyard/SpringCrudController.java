package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SpringCrudController {

    @Autowired
    UserRepository uRepo;

    @Autowired
    FootballerRepository fRepo;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {


        User user = uRepo.findUserByUsername((String)session.getAttribute("username"));

        model.addAttribute("user", user);
        model.addAttribute("badInfo", session.getAttribute("badInfo"));

        List<Footballer> footballers = fRepo.findAllByUser(user);

        model.addAttribute("footballers", footballers);

        return "home";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String signUpPage(){
        return "signup";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String createUser(String username, String password) throws PasswordStorage.CannotPerformOperationException {

        String hashedPass = PasswordStorage.createHash(password);

        User user = new User(username, hashedPass);
        uRepo.save(user);

        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String doLogin(HttpSession session, Model model, String username, String password) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        User user = uRepo.findUserByUsername(username);

        if(PasswordStorage.verifyPassword(password, user.getPassword())){
            session.setAttribute("username", username);
            session.removeAttribute("badInfo");
        } else{
            session.setAttribute("badInfo", "badInfo");
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String doLogout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createFootballer(HttpSession session, String name, String position, String club, int goals){

        User user = uRepo.findUserByUsername((String)session.getAttribute("username"));
        Footballer f = new Footballer(name, position, club, goals, user);

        fRepo.save(f);

        return "redirect:/";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String deleteFootballer(Integer id){
        fRepo.delete(id);

        return "redirect:/";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String editPage(Model model, Integer id){
        Footballer footballer = fRepo.findOne(id);
        model.addAttribute(footballer);

        return "edit";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String doEdit(Integer id, String name, String club, String position, Integer goals){

        Footballer newFootballer = fRepo.findOne(id);
        newFootballer.setName(name);
        newFootballer.setPosition(position);
        newFootballer.setClub(club);
        newFootballer.setGoals(goals);
        fRepo.save(newFootballer);

        return "redirect:/";
    }
}
