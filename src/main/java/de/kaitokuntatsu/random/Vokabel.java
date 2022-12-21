package de.kaitokuntatsu.random;

public class Vokabel {
    private final String content;
    private String translation;

    public Vokabel(String pContent, String pTranslation)
    {
        this.content = pContent;
        this.translation = pTranslation;
    }

    public String getContent() {return content;}

    public String getTranslation() {return translation;}

    public void setTranslation(String new_translation) {this.translation = new_translation;}
}
