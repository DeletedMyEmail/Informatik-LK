package de.kaitokuntatsu.parser;

public record Token(Type type, String content) {

    enum Type {
        KLAMMER,
        BEFEHL,
        ZAHL
    }
}
