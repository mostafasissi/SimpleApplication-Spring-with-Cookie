package mostafa.ehtp.applicationspringcookies.services;


import mostafa.ehtp.applicationspringcookies.entities.Utilisateur;

public interface IUserService {
        Utilisateur saveUser(Utilisateur u);
        Utilisateur isValidUser(String email , String password) ;

        }
