public enum Muffin {
    bran("bran"),
    pumpkinSpice("pumpkin-spice"),
    bananaNut("banana-nut"),
    chocolate("chocolate"),
    blueberry("blueberry"),
    oatmeal("oatmeal"),
    quiche("quiche");

    public final String label;

    Muffin(String label) {
        this.label = label;
    }

    public static String[] getMuffinLabels() {
        Muffin[] muffins = values();
        String[] muffinLabels = new String[muffins.length];
        for(int i = 0; i < muffinLabels.length; i++) {
            muffinLabels[i] = muffins[i].label;
        }
        return muffinLabels;
    }
}
