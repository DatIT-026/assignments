public class Water {
    private String source;
    private int volume;
    
    public Water() {}
    public Water(String source, int volume) {
        this.source = source;
        this.volume = volume;
    }

    public String getSource() {
        return "" + volume + source.charAt(0) + source.charAt(source.length() - 1);
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        String n = String.format("%s", volume);
        this.volume = volume / n.length();
    }
}