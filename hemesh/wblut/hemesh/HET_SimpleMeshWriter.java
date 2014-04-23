package wblut.hemesh;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import wblut.geom.WB_Point;

/**
 * Helper class for HE_Export.saveToSimpleMesh.
 * 
 * @author Frederik Vanhoutte, W:Blut
 * 
 */

public class HET_SimpleMeshWriter {

	/** The simple mesh stream. */
	protected OutputStream simpleMeshStream;

	/** The simple mesh writer. */
	protected PrintWriter simpleMeshWriter;

	/**
	 * Begin save.
	 * 
	 * @param stream
	 *            the stream
	 */
	public void beginSave(final OutputStream stream) {
		try {
			simpleMeshStream = stream;
			handleBeginSave();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Begin save.
	 * 
	 * @param fn
	 *            the fn
	 */
	public void beginSave(final String fn) {
		try {
			simpleMeshStream = new FileOutputStream(fn);
			handleBeginSave();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * End save.
	 */
	public void endSave() {
		try {
			simpleMeshWriter.flush();
			simpleMeshWriter.close();
			simpleMeshStream.flush();
			simpleMeshStream.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Faces.
	 * 
	 * @param f
	 *            the f
	 */
	public void faces(final int[][] f) {
		int i = 0;
		for (i = 0; i < f.length; i++) {
			face(f[i]);
		}
	}

	/**
	 * Face.
	 * 
	 * @param f
	 *            the f
	 */
	public void face(final int[] f) {
		int i = 0;
		final int fl = f.length;
		simpleMeshWriter.print(fl + " ");
		for (i = 0; i < fl - 1; i++) {
			simpleMeshWriter.print(f[i] + " ");
		}
		simpleMeshWriter.println(f[i]);
	}

	/**
	 * Handle begin save.
	 */
	protected void handleBeginSave() {
		simpleMeshWriter = new PrintWriter(simpleMeshStream);
	}

	/**
	 * Vertices.
	 * 
	 * @param v
	 *            the v
	 */
	public void vertices(final WB_Point[] v) {
		int i = 0;
		for (i = 0; i < v.length; i++) {
			simpleMeshWriter.println(v[i].x + " " + v[i].y + " " + v[i].z);
		}
	}

	/**
	 * Int value.
	 * 
	 * @param v
	 *            the v
	 */
	public void intValue(final int v) {
		simpleMeshWriter.println(v);
	}
}
