package wblut.external.JAMA;

import wblut.math.WB_Math;

/**
 * Part of JAMA: http://math.nist.gov/javanumerics/jama/
 * 
 * Original copyright Notice:
 * 
 * This software is a cooperative product of The MathWorks and the National
 * Institute of Standards and Technology (NIST) which has been released to the
 * public domain. Neither The MathWorks nor NIST assumes any responsibility
 * whatsoever for its use by other parties, and makes no guarantees, expressed
 * or implied, about its quality, reliability, or any other characteristic.
 */

public class QRDecomposition implements java.io.Serializable {

	/*
	 * ------------------------ Class variables ------------------------
	 */

	/**
	 * Array for internal storage of decomposition.
	 * 
	 * @serial internal array storage.
	 */
	private final double[][] QR;

	/**
	 * Row and column dimensions.
	 * 
	 * @serial column dimension.
	 * @serial row dimension.
	 */
	private final int m, n;

	/**
	 * Array for internal storage of diagonal of R.
	 * 
	 * @serial diagonal of R.
	 */
	private final double[] Rdiag;

	/*
	 * ------------------------ Constructor ------------------------
	 */

	/**
	 * QR Decomposition, computed by Householder reflections. Structure to
	 * access R and the Householder vectors and compute Q.
	 * 
	 * @param A
	 *            Rectangular matrix
	 */

	public QRDecomposition(final Matrix A) {
		// Initialize.
		QR = A.getArrayCopy();
		m = A.getRowDimension();
		n = A.getColumnDimension();
		Rdiag = new double[n];

		// Main loop.
		for (int k = 0; k < n; k++) {
			// Compute 2-norm of k-th column without under/overflow.
			double nrm = 0;
			for (int i = k; i < m; i++) {
				nrm = WB_Math.hypot(nrm, QR[i][k]);
			}

			if (nrm != 0.0) {
				// Form k-th Householder vector.
				if (QR[k][k] < 0) {
					nrm = -nrm;
				}
				for (int i = k; i < m; i++) {
					QR[i][k] /= nrm;
				}
				QR[k][k] += 1.0;

				// Apply transformation to remaining columns.
				for (int j = k + 1; j < n; j++) {
					double s = 0.0;
					for (int i = k; i < m; i++) {
						s += QR[i][k] * QR[i][j];
					}
					s = -s / QR[k][k];
					for (int i = k; i < m; i++) {
						QR[i][j] += s * QR[i][k];
					}
				}
			}
			Rdiag[k] = -nrm;
		}
	}

	/*
	 * ------------------------ Public Methods ------------------------
	 */

	/**
	 * Is the matrix full rank?
	 * 
	 * @return true if R, and hence A, has full rank.
	 */

	public boolean isFullRank() {
		for (int j = 0; j < n; j++) {
			if (Rdiag[j] == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return the Householder vectors
	 * 
	 * @return Lower trapezoidal matrix whose columns define the reflections
	 */

	public Matrix getH() {
		final Matrix X = new Matrix(m, n);
		final double[][] H = X.getArray();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i >= j) {
					H[i][j] = QR[i][j];
				} else {
					H[i][j] = 0.0;
				}
			}
		}
		return X;
	}

	/**
	 * Return the upper triangular factor
	 * 
	 * @return R
	 */

	public Matrix getR() {
		final Matrix X = new Matrix(n, n);
		final double[][] R = X.getArray();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j) {
					R[i][j] = QR[i][j];
				} else if (i == j) {
					R[i][j] = Rdiag[i];
				} else {
					R[i][j] = 0.0;
				}
			}
		}
		return X;
	}

	/**
	 * Generate and return the (economy-sized) orthogonal factor
	 * 
	 * @return Q
	 */

	public Matrix getQ() {
		final Matrix X = new Matrix(m, n);
		final double[][] Q = X.getArray();
		for (int k = n - 1; k >= 0; k--) {
			for (int i = 0; i < m; i++) {
				Q[i][k] = 0.0;
			}
			Q[k][k] = 1.0;
			for (int j = k; j < n; j++) {
				if (QR[k][k] != 0) {
					double s = 0.0;
					for (int i = k; i < m; i++) {
						s += QR[i][k] * Q[i][j];
					}
					s = -s / QR[k][k];
					for (int i = k; i < m; i++) {
						Q[i][j] += s * QR[i][k];
					}
				}
			}
		}
		return X;
	}

	/**
	 * Least squares solution of A*X = B
	 * 
	 * @param B
	 *            A Matrix with as many rows as A and any number of columns.
	 * @return X that minimizes the two norm of Q*R*X-B.
	 * @exception IllegalArgumentException
	 *                Matrix row dimensions must agree.
	 * @exception RuntimeException
	 *                Matrix is rank deficient.
	 */

	public Matrix solve(final Matrix B) {
		if (B.getRowDimension() != m) {
			throw new IllegalArgumentException(
					"Matrix row dimensions must agree.");
		}
		if (!isFullRank()) {
			throw new RuntimeException("Matrix is rank deficient.");
		}

		// Copy right hand side
		final int nx = B.getColumnDimension();
		final double[][] X = B.getArrayCopy();

		// Compute Y = transpose(Q)*B
		for (int k = 0; k < n; k++) {
			for (int j = 0; j < nx; j++) {
				double s = 0.0;
				for (int i = k; i < m; i++) {
					s += QR[i][k] * X[i][j];
				}
				s = -s / QR[k][k];
				for (int i = k; i < m; i++) {
					X[i][j] += s * QR[i][k];
				}
			}
		}
		// Solve R*X = Y;
		for (int k = n - 1; k >= 0; k--) {
			for (int j = 0; j < nx; j++) {
				X[k][j] /= Rdiag[k];
			}
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < nx; j++) {
					X[i][j] -= X[k][j] * QR[i][k];
				}
			}
		}
		return (new Matrix(X, n, nx).getMatrix(0, n - 1, 0, nx - 1));
	}

	private static final long serialVersionUID = 1;
}
