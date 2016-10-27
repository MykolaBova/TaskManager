package org.rublin;

import org.rublin.repository.JdbcRepository;

/**
 * Created by Ruslan Sheremet (rublin) on 26.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Active tasks:");
        JdbcRepository.getRepository().getAllActive().forEach(System.out::println);
        System.out.println("Closed task:");
        JdbcRepository.getRepository().getAllClosed().forEach(System.out::println);
    }
}
