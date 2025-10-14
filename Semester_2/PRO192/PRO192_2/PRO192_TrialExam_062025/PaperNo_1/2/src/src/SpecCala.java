public class SpecCala extends Cala {
    private int color;

    public SpecCala(String owner, int price, int color) {
        super(owner, price);
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %d", super.getOwner(), super.getPrice(), color);
    }

    public void setData() {
    String owner = super.getOwner();
    if (owner.length() >= 2) {
        String newOwner = owner.substring(0, 1) + "XX" + owner.substring(2);
        System.out.println(newOwner);
    }
}


    public int getValue() {
        if(color % 2 != 0) return super.getPrice() + 3;
            else return super.getPrice() + 7;
    }
}