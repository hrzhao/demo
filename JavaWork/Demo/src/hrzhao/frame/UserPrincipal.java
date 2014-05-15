package hrzhao.frame;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

public class UserPrincipal implements Principal, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private List<String> subjects;
	public UserPrincipal() {
		// TODO Auto-generated constructor stub
	}
	public UserPrincipal(String username) {
		if (username == null)
			throw new NullPointerException("illegal null input");
		this.name = username;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	 public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
	public List<String> getSubjects() {
        return subjects;
    }
	 
	 public boolean equals(Object o) {
        if (o == null)
            return false;

        if (this == o)
            return true;

        if (!(o instanceof UserPrincipal))
            return false;
        UserPrincipal that = (UserPrincipal) o;

        if (this.getName().equals(that.getName()))
            return true;
        return false;
    }
}
