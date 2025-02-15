package by.asonau.web_project.dao;

public class DAOException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DAOException() {
    super();
  }

  public DAOException(String message) {
    super(message);
  }

  public DAOException(Throwable cause) {
    super(cause);
  }

  public DAOException(String message, Throwable cause) {
    super(message, cause);
  }
}
