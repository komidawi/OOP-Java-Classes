/**
 * Class contains helpful tool for calculating distance between road pillars.
 */
public class RoadPillarTools {
    /**
     * Field used for unit conversion.
     */
    private static final int CENTIMETERS_IN_METER = 100;
    /**
     * Calculates the distance between the first and the last pillar in centimeters (without the width of the first and last pillar).
     * @param count amount of pillars
     * @param distance distance between pillars in meters
     * @param width width of an individual pillar in centimeters
     * @return distance between the first and the last pillar
     */
    public static int calculate(int count, int distance, int width){
        if (areArgumentsValid(count, distance, width)) {
            return (count - 1)*distance*CENTIMETERS_IN_METER + (count - 2)*width;
        } else if (count == 1) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Validates input data.
     * @param count amount of pillars
     * @param distance distance between pillars in meters
     * @param width width of an individual pillar in centimeters
     * @return returns true if all parameters are valid
     */
    private static boolean areArgumentsValid(int count, int distance, int width) {
        return isCountValid(count) && isDistanceValid(distance) && isWidthValid(width);
    }

    /**
     * Checks if amount of pillars is valid.
     * @param count amount of pillars
     * @return returns true if amount is valid
     */
    private static boolean isCountValid(int count) {
        return count > 1;
    }

    /**
     * Checks if a distance between pillars is valid.
     * @param distance distance between pillars in meters
     * @return returns true if distance is valid
     */
    private static boolean isDistanceValid(int distance) {
        return distance >= 10 && distance <= 30;
    }

    /**
     * Checks if a width of an individual pillar is valid
     * @param width width of individual pillar in centimeters
     * @return returns true if width is valid
     */
    private static boolean isWidthValid(int width) {
        return width >= 10 && width <= 50;
    }
}