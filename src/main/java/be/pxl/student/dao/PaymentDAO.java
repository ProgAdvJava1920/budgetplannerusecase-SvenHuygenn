package be.pxl.student.dao;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.*;
import java.time.ZoneId;

@Data
@AllArgsConstructor
public class PaymentDAO {
    private static final String SELECT_BY_ID = "SELECT * FROM Payment WHERE id = ?";
    private static final String UPDATE = "UPDATE Payment SET date=?, amount=?, currency=?, detail=?, accountId=?, counterAccountId=?, labelId=? WHERE id = ?";
    private static final String INSERT = "INSERT INTO Payment (date , amount, currency, detail, accountId, counterAccountId, labelId) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM Payment WHERE id = ?";
    private String url;
    private String user;
    private String password;

    public Payment createPayment(Payment payment) {

        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, java.sql.Date.valueOf(payment.getDate().toLocalDate()));
            stmt.setString(3, payment.getCurrency());
            stmt.setString(4, payment.getDetail());
            stmt.setInt(5, payment.getAccountId());
            stmt.setInt(6, payment.getCounterAccountId());
            stmt.setInt(7, payment.getLabelId());
            if (stmt.executeUpdate() == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        payment.setId(rs.getInt(1));
                        return payment;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateAccount(Account account) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
            stmt.setString(1, account.getName());
            stmt.setString(2, account.getIBAN());
            stmt.setInt(3, account.getId());
            return stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteAccount(int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(DELETE)) {
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Account readAccount(int id) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapAccount(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Account mapAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setName(rs.getString("name"));
        account.setIBAN(rs.getString("iban"));
        account.setId(rs.getInt("id"));
        return account;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);

    }
}