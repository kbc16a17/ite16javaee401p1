package beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import beans.session.Authorization;
import db.AccountsDb;
import db.entities.Account;

@Named
@RequestScoped
public class LoginController {
    @EJB
    private AccountsDb db;
    @Inject
    private Authorization auth;

    private Account account = new Account();
        
    public String login() {
        Account result = db.find(account.getUsername(), account.getPassword());
        if (result != null) {
            sessionUpdate();
            auth.set(result.getId(), result.getName());
            return "dashboard.xhtml?faces-redirect=true";
        }
        return "login.xhtml?faces-redirect=true";
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

    private void sessionUpdate() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest)context.getRequest();
        request.changeSessionId();
    }

    public Account getAccount() {
        return account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
}
