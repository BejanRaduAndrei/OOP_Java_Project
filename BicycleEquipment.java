public class BicycleEquipment {
    private boolean hasHelmet;
    private boolean hasLock;
    private boolean hasLights;
    private boolean hasBasket;

    public BicycleEquipment(boolean hasHelmet, boolean hasLock, boolean hasLights, boolean hasBasket) {
        this.hasHelmet = hasHelmet;
        this.hasLock = hasLock;
        this.hasLights = hasLights;
        this.hasBasket = hasBasket;
    }

    public boolean isHasHelmet() {
        return hasHelmet;
    }

    public void setHasHelmet(boolean hasHelmet) {
        this.hasHelmet = hasHelmet;
    }

    public boolean isHasLock() {
        return hasLock;
    }

    public void setHasLock(boolean hasLock) {
        this.hasLock = hasLock;
    }

    public boolean isHasLights() {
        return hasLights;
    }

    public void setHasLights(boolean hasLights) {
        this.hasLights = hasLights;
    }

    public boolean isHasBasket() {
        return hasBasket;
    }

    public void setHasBasket(boolean hasBasket) {
        this.hasBasket = hasBasket;
    }

    @Override
    public String toString() {
        return "BicycleEquipment{" +
                "hasHelmet=" + hasHelmet +
                ", hasLock=" + hasLock +
                ", hasLights=" + hasLights +
                ", hasBasket=" + hasBasket +
                '}';
    }
}
