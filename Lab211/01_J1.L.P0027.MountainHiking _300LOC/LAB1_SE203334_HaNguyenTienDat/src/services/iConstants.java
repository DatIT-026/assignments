package services;

public interface    iConstants {
    public final String MountainCodePattern="^[0-9]+$";
    public final String MountainNamePattern="^[a-zA-Z0-9 ]+$";
    public final String MountainLocationPattern="^[a-zA-Z ]+$";
    public final String MountainDesPattern="^[a-zA-Z0-9 ]+$";
    
    public final String StudentCodePattern="(?i)^[SQCDH][E][0-9]{6}$"; // (?i) nghia la chu thuong, hoa deu duoc
    public final String StudentNamePattern="^[a-zA-Z ]{2,20}$";
    public final String StudentEmailPattern="^[a-zA-Z0-9]+[@][a-zA-Z]+([.][a-zA-Z]+){1,2}$";
    public final String StudentPhonePattern="^[0-9]{10}$";

    public final String VIETTEL_VALID = "^(086|096|097|098|039|038|037|036|035|034|033|032)\\d{7}$";
    public final String VNPT_VALID = "^(081|082|083|084|085|088|091|094)\\d{7}$";

    public final String COMFIRMATION = "^[YNyn]{1}$";
}
