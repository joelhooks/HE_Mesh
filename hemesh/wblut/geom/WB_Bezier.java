package wblut.geom;

public class WB_Bezier implements WB_Curve {

	protected WB_Point[] points;

	protected int n;

	public WB_Bezier(final WB_Point[] controlPoints) {
		points = controlPoints;
		n = points.length - 1;

	}

	public WB_Bezier(final WB_PointHomogeneous[] controlPoints) {
		n = controlPoints.length - 1;
		points = new WB_Point[n + 1];
		for (int i = 0; i < n + 1; i++) {
			points[i] = new WB_Point(controlPoints[i].project());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wblut.nurbs.WB_Curve#curvePoint(double)
	 */
	public WB_Point curvePoint(final double u) {
		final double[] B = allBernstein(u);
		final WB_Point C = new WB_Point();
		for (int k = 0; k <= n; k++) {
			C._addSelf(B[k], points[k]);
		}
		return C;
	}

	protected double[] allBernstein(final double u) {
		final double[] B = new double[n + 1];
		B[0] = 1.0;
		final double u1 = 1.0 - u;
		double saved, temp;
		;
		for (int j = 1; j <= n; j++) {
			saved = 0.0;
			for (int k = 0; k < j; k++) {
				temp = B[k];
				B[k] = saved + u1 * temp;
				saved = u * temp;
			}
			B[j] = saved;
		}

		return B;
	}

	protected static double[] allBernstein(final double u, final int n) {
		final double[] B = new double[n + 1];
		B[0] = 1.0;
		final double u1 = 1.0 - u;
		double saved, temp;
		;
		for (int j = 1; j <= n; j++) {
			saved = 0.0;
			for (int k = 0; k < j; k++) {
				temp = B[k];
				B[k] = saved + u1 * temp;
				saved = u * temp;
			}
			B[j] = saved;
		}

		return B;
	}

	public double n() {

		return n;
	}

	public WB_Point[] points() {

		return points;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wblut.nurbs.WB_Curve#loweru()
	 */
	public double loweru() {

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wblut.nurbs.WB_Curve#upperu()
	 */
	public double upperu() {

		return 1;
	}

	public WB_Bezier elevateDegree() {

		final WB_Point[] npoints = new WB_Point[n + 2];
		npoints[0] = points[0];
		npoints[n + 1] = points[n];
		final double inp = 1.0 / (n + 1);
		for (int i = 1; i <= n; i++) {
			npoints[i] = WB_Point
					.interpolate(points[i], points[i - 1], i * inp);

		}
		return new WB_Bezier(npoints);

	}

}
