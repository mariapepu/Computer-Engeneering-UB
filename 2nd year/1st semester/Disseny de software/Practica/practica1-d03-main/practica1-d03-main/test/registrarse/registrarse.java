package registrarse;

import org.concordion.api.BeforeExample;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class registrarse {

    public boolean isValidPassword(String password){
        return true;
    }

    public boolean isTakenUsername(String username){
        return true;
    }

    public boolean isValidDni(String email){
        return true;
    }

    public String addClient(String idClient, String psw, String dni, String adress, boolean vip){
        return "Success";
    }
}
