package beans;

import beans.session.Authorization;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import db.entities.Account;
import db.AccountsDb;
import javax.inject.Inject;

@Named
@RequestScoped
public class RegisterController {
    @EJB
    private AccountsDb db;
    @Inject
    private Authorization auth;
    
    private Account account = new Account();

    public String register() {
        db.create(account);
        Account result = db.find(account.getUsername(), account.getPassword());
        auth.set(result.getId(), result.getName());
        return "dashboard.xhtml?faces-redirect=true";
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
