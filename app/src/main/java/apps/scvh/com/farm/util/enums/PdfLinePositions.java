package apps.scvh.com.farm.util.enums;


public enum PdfLinePositions {

    TOP_LEVEL(750), START_OF_LINE(50);

    private float coordinate;

    PdfLinePositions(float coordinate) {
        this.coordinate = coordinate;
    }

    public float getCoordinate() {
        return coordinate;
    }
}
