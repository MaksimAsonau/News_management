package by.asonau.web_project.bean;

import java.io.Serializable;
import java.util.Objects;

public class Session implements Serializable {

    private int sessionId;
    private String login;
    private String dateCreated;
    private String lastAccessed;

    public Session(int sessionId, String login, String dateCreated, String lastAccessed) {
        this.sessionId = sessionId;
        this.login = login;
        this.dateCreated = dateCreated;
        this.lastAccessed = lastAccessed;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(String lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return sessionId == session.sessionId && Objects.equals(login, session.login) && Objects.equals(dateCreated, session.dateCreated) && Objects.equals(lastAccessed, session.lastAccessed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, login, dateCreated, lastAccessed);
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "sessionId=" + sessionId +
                ", login='" + login + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", lastAccessed='" + lastAccessed + '\'' +
                '}';
    }
}
