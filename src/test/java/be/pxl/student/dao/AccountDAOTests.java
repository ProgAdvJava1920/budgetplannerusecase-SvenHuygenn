package be.pxl.student.dao;

import be.pxl.student.entity.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountDAOTests {

    @Test
    public void testAccountInsert(){
        Account account = new Account();
        account.setIBAN("testIBAN");
        account.setName("testName");

        AccountDAO accountDAO=new AccountDAO("jdbc:mariadb://localhost:3306/budgetplanner?useSSL=false", "root", "admin");

        Account accountInserted = accountDAO.createAccount(account);
        //assertEquals(account,accountInserted);
        assertNotEquals(0,accountInserted.getId());
        System.out.println(accountInserted.getId());
    }
}

