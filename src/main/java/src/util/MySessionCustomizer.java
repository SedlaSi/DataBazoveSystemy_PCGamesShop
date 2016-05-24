package src.util;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;

public class MySessionCustomizer implements SessionCustomizer {
    @Override
    public void customize(Session session) throws Exception {
        DatabaseLogin dbLogin = (DatabaseLogin) session.getDatasourceLogin();
        dbLogin.setTransactionIsolation(DatabaseLogin.TRANSACTION_REPEATABLE_READ);
    }
}
