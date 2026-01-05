package ru.stqa.mantis.common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunc {
    public static String randomString(int n) {  // String вместо Supplier<String>
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        return Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
    }

    public static String randomPhone (){
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(10);
        var result = Stream.generate(randomNumbers)
                .limit(11)
                .map(i -> '0'+i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }

}
