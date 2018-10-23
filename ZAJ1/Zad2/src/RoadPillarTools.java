/**
 * Class which contains helpful tools for calculating e.g. distance between pillars.
 */
public class RoadPillarTools {
    private static final int CENTIMETERS_IN_METER = 100;
    /**
     * Calculates the distance between the first and the last pillar in centimeters (without the width of the first and last pillar).
     * @param count amount of pillars
     * @param distance distance between pillars in meters
     * @param width width of individual pillar in centimeters
     * @return distance between first and the last pillar
     */
    public static int calculateDistance(int count, int distance, int width){
        if (count > 1 && distance >= 10 && distance <= 30 && width >= 10 && width <= 50) {
            return (count - 1)*distance*CENTIMETERS_IN_METER + (count - 2)*width;
        } else if (count == 1) {
            return 0;
        } else {
            return -1;
        }
    }
}
