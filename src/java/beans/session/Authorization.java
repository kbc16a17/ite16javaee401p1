package beans.session;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Authorization implements Serializable {
    private Integer accountId = null;
    private String username = null;
    
    public void set(Integer accountId, String username) {
        this.accountId = accountId;
        this.username = username;
    }    

    public Integer getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }
    
    public boolean isLoggedIn() {
        return accountId != null;
    }
}
