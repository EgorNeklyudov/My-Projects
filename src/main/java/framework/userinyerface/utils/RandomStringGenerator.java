package framework.userinyerface.utils;

import java.util.Random;

public class RandomStringGenerator {

    public String randomString() {
        
        int length = 16;
        Random random = new Random();
        String string = random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return string;
    }

}
