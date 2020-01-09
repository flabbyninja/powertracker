package com.flabbyninja.powertracker;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.security.Security;

@Configuration
public class JasyptConfiguration {

    @Bean(name="encryptorBean")
    @Primary
    public static StringEncryptor stringEncryptor(@Value("${jasypt.encryptor.password}") String password) {
        Security.addProvider(new BouncyCastleProvider());
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("BC");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        encryptor.setIvGenerator(new RandomIvGenerator());
        return encryptor;
    }
}
