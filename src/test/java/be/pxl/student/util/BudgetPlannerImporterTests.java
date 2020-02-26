package be.pxl.student.util;

import be.pxl.student.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;


public class BudgetPlannerImporterTests {
    public BudgetPlannerFeeder testFeeder;


    @BeforeEach
    void init(){
        testFeeder = new BudgetPlannerFeeder();
    }

    @Test
    public void shouldCreateAListOfAccounts(){
        testFeeder.faker = new Faker();
        testFeeder.myAccountName = "Karel";
        testFeeder.myIBANNumber = "MNOP";

         
    }

}
