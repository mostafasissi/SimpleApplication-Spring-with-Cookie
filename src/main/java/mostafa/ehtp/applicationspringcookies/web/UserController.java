package mostafa.ehtp.applicationspringcookies.web;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mostafa.ehtp.applicationspringcookies.entities.Utilisateur;
import mostafa.ehtp.applicationspringcookies.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    IUserService userService;
    @GetMapping("/")
    public String acceuil(){
        return "acceuil";
    }
    @GetMapping("/loginPage")
    public String loginPage(@CookieValue(name = "email" , defaultValue = "") String emailCookie, Model model){
       // Recuperer les cookies pour les transmettre à la page login pour les afficher
        model.addAttribute("email" , emailCookie);
        return "loginPage";
    }
    @GetMapping("/registerPage")
    public String registerPage(){
        return "registerPage";
    }
    @PostMapping(path = "/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpServletResponse response) {

        // Vérifiez si les informations d'identification sont valides
        if (userService.isValidUser(email,password) != null) {
            // Si les informations d'identification sont valides, stockez le nom d'utilisateur dans un cookie
            Cookie cookie = new Cookie("email", email);
            response.addCookie(cookie);
            return "redirect:/dashboard";

        } else {
            // Si les informations d'identification sont invalides, renvoyez l'utilisateur à la page de connexion
            return "loginPage";
        }

    }
    @PostMapping(path = "/register")
    public String register( @RequestParam("email") String email,
                            @RequestParam("password") String password,
                            @RequestParam("first_name") String first_name,
                            @RequestParam("last_name") String last_name,
                            HttpServletResponse response
    ) {

        Utilisateur utilisateur = new Utilisateur(null , email , password , first_name ,last_name);
        userService.saveUser(utilisateur);
        Cookie cookie = new Cookie("email", email);
        response.addCookie(cookie);
        return "redirect:/dashboard";
    }


    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request, Model model) {
        // Récupérez le cookie contenant le nom d'utilisateur
        Cookie[] cookies = request.getCookies();
        String email = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    email = cookie.getValue();
                    break;
                }
            }
        }
        // Passez le nom d'utilisateur à la vue
        model.addAttribute("email", email);

        return "dashboard";
    }

}
