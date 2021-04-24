package Score.Filters.LastModified;

/**
 * This Class is represents a single doze function for the LastModified score filter.
 *
 * 
 */
public class LastModifiedFunction {
    /**
     * Those are the parameters for the function of the score
     */
    private static final double d = 1000;
    private static final double f = 6.36;
    private static final double c = 3.4;
    private static final double v = 66;
    private static final double E = c/v;
    private static final double a = 5.08;

    /**
     * this function get the last time the file was modified , and returns the calculated score.
     * @param t
     * @return
     */
    public static double F(double t) {
        return (f*d*a)/(v*(a-E))*(Math.exp(-E*t)-Math.exp(-a*t));
    }
}
