package com.moon.sell.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author moonglade on 2019-01-03.
 * @version 1.0
 */
public class lambda1 {
    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        for (String player :players
                ) {
            System.out.print(player+" ;");
        }

        players.forEach((player) -> System.out.print(player+": "));

        players.forEach(System.out::println);
    }
}
