package Score.Filters.LastModified;

/**
 * This Class is represents a single doze function for the LastModified score filter.
 *
 * @author Boaz Tene
 */
public class LastModifiedFunction {
    private static final double d = 1000;
    private static final double f = 6.36;
    private static final double c = 3.4;
    private static final double v = 66;
    private static final double E = c/v;
    private static final double a = 5.08;

    public static double F(double t) {
        return (f*d*a)/(v*(a-E))*(Math.exp(-E*t)-Math.exp(-a*t));
    }
}
