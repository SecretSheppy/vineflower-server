package com.secretsheppy.vineflowerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>Vineflower Server</h1>
 * A server to run vineflower persistently without have to continuously create and destroy new jvm instances. Primarily
 * designed for use with <a href="https://github.com/SecretSheppy/marv">marv</a>, thought it is likely to be useful
 * in other applications too.
 */
@SpringBootApplication
public class VineflowerServer {

    static void main(String[] args) {
        SpringApplication.run(VineflowerServer.class, args);
    }
}
