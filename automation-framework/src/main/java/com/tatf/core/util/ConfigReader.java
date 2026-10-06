package com.tatf.core.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.util.Properties;

public class ConfigReader {

    private final Properties properties;


    public ConfigReader(String resourcePath) {

        this.properties = new Properties();

        String contenido =
                ResourceLoader.loadAsString(resourcePath);

        try {
            properties.load(
                    new StringReader(contenido)
            );
        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Error cargando configuración: " + resourcePath,
                    e
            );
        }
    }


    public String asString(String key) {

        String systemValue =
                System.getProperty(key);

        if (systemValue != null) {
            return systemValue;
        }

        String value =
                properties.getProperty(key);

        if (value == null) {
            throw new IllegalStateException(
                    "No se encontró la propiedad: " + key
            );
        }

        return value;
    }


    public int asInt(String key) {
        return Integer.parseInt(
                asString(key)
        );
    }


    public boolean asBoolean(String key) {
        return Boolean.parseBoolean(
                asString(key)
        );
    }
}