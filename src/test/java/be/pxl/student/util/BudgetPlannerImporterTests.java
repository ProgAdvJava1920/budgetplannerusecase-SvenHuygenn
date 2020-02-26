package be.pxl.student.util;

import be.pxl.student.*;
import be.pxl.student.entity.Account;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BudgetPlannerImporterTests {
    public BudgetPlannerFeeder testFeeder;


    @BeforeEach
    void init(){
        testFeeder = new BudgetPlannerFeeder();
    }

    @Test
    @SneakyThrows
    public void shouldCreateAListOfAccounts(){
        testFeeder.faker = new Faker();
        testFeeder.myAccountName = "Karel";
        testFeeder.myIBANNumber = "MNOP";

        String fileLocation = "src/test/resources/account_payments.csv";

        Method datalinesMethod = BudgetPlannerFeeder.class.getDeclaredMethod("generateLines", int.class);
        Method saveFileMethod = BudgetPlannerFeeder.class.getDeclaredMethod("saveFile", String.class, String[].class);
        saveFileMethod.setAccessible(true);
        datalinesMethod.setAccessible(true);

        Object returnedDataLines = datalinesMethod.invoke(testFeeder, 5);
        String[] dataLines = (String[]) returnedDataLines;
        saveFileMethod.invoke(testFeeder, fileLocation, dataLines);

        List<Account> accounts = BudgetPlannerImporter.importPayments(fileLocation);

        assertTrue(accounts.size() > 0);

        accounts.forEach(account -> System.out.println(account.toString()));

    }

}
