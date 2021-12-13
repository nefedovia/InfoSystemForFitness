package sample.infosystemforfitness.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import sample.infosystemforfitness.DatabaseHandler;
import sample.infosystemforfitness.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class SignUpTest {

    @Test
    public void test() throws SQLException {

        User user = new User(randString(),randString(),randString(),randString(),"Мужской",randString(),randString());

        new DatabaseHandler().signUpUser(user.getFirstName(), user.getLastName(), user.getUserName(),
                    user.getPassword(), user.getGender(), user.getPhone());
        ResultSet response = new DatabaseHandler().getUser(user.getUserName(), user.getPassword());

        response.next();
        String responseUsername = response.getString(4);
        String responsePassword = response.getString(5);

        Assert.assertEquals(responseUsername, user.getUserName());
        Assert.assertEquals(responsePassword, user.getPassword());

    }

    public String randString() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}