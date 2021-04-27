public enum Muffin {
    bran("bran"),
    pumpkinSpice("pumpkin-spice"),
    bananaNut("banana-nut"),
    chocolate("chocolate"),
    blueberry("blueberry"),
    oatmeal("oatmeal"),
    quiche("quiche");

    public final String label;

    private Muffin(String label) {
        this.label = label;
    }
}
