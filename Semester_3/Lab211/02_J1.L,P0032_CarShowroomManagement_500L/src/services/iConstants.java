package services;

public interface iConstants {
    public final String CasualString="^[A-Za-z0-9-_() ]+$";
    public final String StringButOnlyAlphabetAllowed="^[A-Za-z ]+$";
    public final String StringButAlphabetAndNumberAllowed="^[A-Za-z0-9]+$";
    public final String StringOnlyForFrameFormat="^[Ff]\\d{5}$";
    public final String StringOnlyForEngineFormat="^[Ee]\\d{5}$";
    
    // (?!0+(\\.0+)?$) la negative lookahead, 0+ la toan so 0, \\.0+ la phan thap pha toan 0
    // kiem tra so co hop le khong.
    // \\d+(\\.\\d+)? kiem tra so co hop le?
    public final String thePrice="^(?!0+(\\.0+)?$)\\d+(\\.\\d+)?$";
}
