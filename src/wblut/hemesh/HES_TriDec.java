/*
 * This file is part of HE_Mesh, a library for creating and manipulating meshes.
 * It is dedicated to the public domain. To the extent possible under law,
 * I , Frederik Vanhoutte, have waived all copyright and related or neighboring
 * rights.
 *
 * This work is published from Belgium. (http://creativecommons.org/publicdomain/zero/1.0/)
 *
 */

package wblut.hemesh;

import java.util.Iterator;
import java.util.List;

import gnu.trove.map.TLongDoubleMap;
import gnu.trove.map.hash.TLongDoubleHashMap;
import javolution.util.FastTable;
import wblut.core.WB_ProgressCounter;
import wblut.geom.WB_Plane;
import wblut.geom.WB_Triangle;
import wblut.geom.WB_Vector;
import wblut.math.WB_Epsilon;

public class HES_TriDec extends HES_Simplifier {

	private double _lambda;

	private HE_Mesh _mesh;

	private Heap heap;

	TLongDoubleMap vertexCost;

	private int goal;

	private double fraction;

	int counter;

	/**
	 *
	 */
	public HES_TriDec() {
		_lambda = 20;
	}

	/**
	 *
	 *
	 * @param f
	 * @return
	 */
	public HES_TriDec setLambda(final double f) {
		_lambda = f;
		return this;
	}

	/**
	 *
	 *
	 * @param r
	 * @return
	 */
	public HES_TriDec setGoal(final int r) {
		goal = r;
		return this;
	}

	/**
	 *
	 *
	 * @param f
	 * @return
	 */
	public HES_TriDec setGoal(final double f) {
		fraction = f;
		goal = -1;
		return this;
	}

	/**
	 *
	 *
	 * @param mesh
	 * @return
	 */
	public static double[] getVertexColor(final HE_Mesh mesh) {
		final HE_VertexIterator vItr = mesh.vItr();
		final double[] values = new double[mesh.getNumberOfVertices()];
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		HE_Vertex v;
		double vvi;
		int i = 0;
		while (vItr.hasNext()) {
			v = vItr.next();
			vvi = visualImportance(v);
			values[i++] = vvi;
			if (vvi < min) {
				min = vvi;
			}
			if (vvi > max) {
				max = vvi;
			}
		}
		final double range = max - min;
		final boolean monochrome = WB_Epsilon.isZero(range);
		final double invrange = monochrome ? 0 : 1.0 / range;
		for (i = 0; i < mesh.getNumberOfVertices(); i++) {
			values[i] = monochrome ? 0 : (values[i] - min) * invrange;
		}
		return values;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wblut.hemesh.HES_Simplifier#apply(wblut.hemesh.HE_Mesh)
	 */
	@Override
	public HE_Mesh apply(final HE_Mesh mesh) {
		tracker.setStatus(this, "Starting HES_TriDec.", +1);
		if (goal == -1) {
			goal = (int) (mesh.getNumberOfVertices() * fraction);
		}
		if (mesh.getNumberOfVertices() <= goal) {
			tracker.setStatus(this, "Mesh has less vertices than goal. Exiting HES_TriDec.", -1);
			return mesh;
		}
		_mesh = mesh;
		if (_mesh.getNumberOfVertices() <= 4) {
			tracker.setStatus(this, "Mesh has  4 or less vertices. Exiting HES_TriDec.", -1);
			return _mesh;
		}
		_mesh.triangulate();
		_mesh.resetVertexInternalLabels();
		buildHeap(_mesh);
		HE_Vertex v;
		Entry entry;
		List<HE_Vertex> vertices;
		final int count = _mesh.getNumberOfVertices() - goal;
		WB_ProgressCounter pcounter = new WB_ProgressCounter(count, 10);
		tracker.setStatus(this, "Removing vertices from heap (" + heap.size() + ").", pcounter);
		while (_mesh.getNumberOfVertices() > goal && heap.size() > 0 && _mesh.getNumberOfVertices() > 4) {
			boolean valid = false;
			do {
				entry = heap.pop();
				v = entry.v;
				valid = mesh.contains(v) && entry.version == v.getInternalLabel();
			} while (heap.size() > 0 && !valid);

			if (valid) {
				vertices = v.getNeighborVertices();
				// vertices.addAll(v.getNextNeighborVertices());

				if (HET_MeshOp.collapseHalfedge(_mesh, v.getHalfedge())) {
					// System.out.println(vertexCost.get(v.key()));

					vertexCost.remove(v.key());

					counter++;
					updateHeap(vertices, _mesh);

				}
			}
			pcounter.increment();
		}
		tracker.setStatus(this, "Exiting HES_TriDec.", -1);
		return _mesh;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wblut.hemesh.HES_Simplifier#apply(wblut.hemesh.HE_Selection)
	 */
	@Override
	public HE_Mesh apply(final HE_Selection selection) {
		tracker.setStatus(this, "Starting HES_TriDec.", 1);
		selection.collectVertices();
		_mesh = selection.parent;
		if (goal == -1) {
			goal = (int) (selection.parent.getNumberOfVertices() - selection.getNumberOfVertices()
					+ selection.getNumberOfVertices() * fraction);
		} else {
			goal = selection.parent.getNumberOfVertices() - selection.getNumberOfVertices() + goal;
		}
		if (selection.parent.getNumberOfVertices() <= goal) {
			tracker.setStatus(this, "Mesh has less vertices than goal. Exiting HES_TriDec.", -1);
			return selection.parent;
		}
		if (_mesh.getNumberOfVertices() <= 4) {
			tracker.setStatus(this, "Mesh has  4 or less vertices. Exiting HES_TriDec.", -1);
			return _mesh;
		}
		_mesh.triangulate();
		_mesh.resetVertexInternalLabels();
		buildHeap(_mesh);
		HE_Vertex v;
		Entry entry;
		List<HE_Vertex> vertices;
		final int count = _mesh.getNumberOfVertices() - goal;
		WB_ProgressCounter pcounter = new WB_ProgressCounter(count, 10);
		tracker.setStatus(this, "Removing vertices.", pcounter);
		while (_mesh.getNumberOfVertices() > goal && heap.size() > 0 && _mesh.getNumberOfVertices() > 4) {
			boolean valid = false;
			do {
				entry = heap.pop();
				v = entry.v;
				valid = selection.contains(v) && _mesh.contains(v) && entry.version == v.getInternalLabel();
			} while (heap.size() > 0 && !valid);
			if (valid) {
				vertices = v.getNeighborVertices();
				// vertices.addAll(v.getNextNeighborVertices());
				if (HET_MeshOp.collapseHalfedge(_mesh, v.getHalfedge())) {
					vertexCost.remove(v.key());
					selection.remove(v);
					counter++;
					updateHeap(vertices, _mesh);
				}
			}
			pcounter.increment();
		}
		selection.clear();
		tracker.setStatus(this, "Exiting HES_TriDec.", -1);
		return _mesh;
	}

	/**
	 *
	 *
	 * @param sel
	 */
	private void buildHeap(final HE_MeshStructure sel) {
		WB_ProgressCounter pcounter = new WB_ProgressCounter(sel.getNumberOfVertices(), 10);
		tracker.setStatus(this, "Building vertex removal heap.", pcounter);
		counter = 0;
		heap = new Heap();
		vertexCost = new TLongDoubleHashMap(10, 0.5f, -1L, Double.NaN);
		final Iterator<HE_Vertex> vItr = sel.vItr();
		double min;
		double c;
		HE_Halfedge minhe;
		List<HE_Halfedge> vstar;
		HE_Vertex v;
		double vvi;
		while (vItr.hasNext()) {
			v = vItr.next();
			v.setInternalLabel(counter);
			vvi = visualImportance(v);
			if (vvi < Double.POSITIVE_INFINITY) {
				vstar = v.getHalfedgeStar();
				minhe = vstar.get(0);
				min = Double.POSITIVE_INFINITY;
				if (v.isBoundary()) { // Only consider collapsing along boundary
					// for
					// boundary vertices, never collapse
					// boundary inward
					for (int i = 0; i < vstar.size(); i++) {
						if (vstar.get(i).isInnerBoundary()) {
							c = halfedgeCollapseCost(vstar.get(i));
							if (c < min) {
								min = c;
								minhe = vstar.get(i);
							}
						}
					}
				} else {
					for (int i = 0; i < vstar.size(); i++) {
						c = halfedgeCollapseCost(vstar.get(i));
						if (!Double.isNaN(c) && c < min) {
							min = c;
							minhe = vstar.get(i);
						}
					}
				}
				if (min < Double.POSITIVE_INFINITY) {
					vertexCost.put(v.key(), min * vvi);
					sel.setHalfedge(v, minhe);
					heap.push(min * vvi, v);
				}
			}
			pcounter.increment();
		}
	}

	/**
	 *
	 *
	 * @param vertices
	 * @param selection
	 */
	private void updateHeap(final List<HE_Vertex> vertices, final HE_MeshStructure selection) {
		double min;
		double c;
		HE_Halfedge minhe;
		List<HE_Halfedge> vstar;
		double vvi;
		for (final HE_Vertex v : vertices) {
			if (selection == null || selection.contains(v)) {
				vvi = visualImportance(v);
				v.setInternalLabel(counter);
				vertexCost.remove(v.key());

				vstar = v.getHalfedgeStar();

				minhe = vstar.get(0);

				min = Double.POSITIVE_INFINITY;
				if (v.isBoundary()) { // Only consider collapsing along boundary
					// for
					// boundary vertices, never collapse
					// boundary inward
					for (int i = 0; i < vstar.size(); i++) {
						if (vstar.get(i).isInnerBoundary()) {
							c = halfedgeCollapseCost(vstar.get(i));
							if (c < min) {
								min = c;
								minhe = vstar.get(i);
							}
						}
					}
				} else {
					for (int i = 0; i < vstar.size(); i++) {
						c = halfedgeCollapseCost(vstar.get(i));
						if (!Double.isNaN(c) && c < min) {
							min = c;
							minhe = vstar.get(i);
						}
					}
				}

				if (min < Double.POSITIVE_INFINITY) {

					vertexCost.put(v.key(), min * vvi);
					selection.setHalfedge(v, minhe);
					heap.push(min * vvi, v);
				}

			}
		}
	}

	/**
	 *
	 *
	 * @param v
	 * @return
	 */
	private static double visualImportance(final HE_Vertex v) {
		final List<HE_Face> faces = v.getFaceStar();
		final WB_Vector nom = new WB_Vector();
		double denom = 0.0;
		double A;
		for (final HE_Face f : faces) {
			A = f.getFaceArea();
			nom.addMulSelf(A, f.getFaceNormal());
			denom += A;
		}
		if (WB_Epsilon.isZero(denom)) {
			return Double.POSITIVE_INFINITY;
			// throw new IllegalArgumentException(
			// "HES_TriDec: can't simplify meshes with degenerate faces.");
		}
		nom.divSelf(denom);
		final double result = 1.0 - nom.getLength3D();
		if (Double.isNaN(result)) {
			return Double.POSITIVE_INFINITY;
		}
		return result;
	}

	/**
	 *
	 *
	 * @param he
	 * @return
	 */
	private double halfedgeCollapseCost(final HE_Halfedge he) {
		final HE_Face f = he.getFace();
		final HE_Face fp = he.getPair().getFace();
		final List<HE_Vertex> vineighbors = he.getVertex().getNeighborVertices();
		final List<HE_Vertex> vfneighbors = he.getEndVertex().getNeighborVertices();
		int shared = 0;
		final int max = f == null || fp == null ? 1 : 2;
		for (final HE_Vertex vi : vineighbors) {
			for (final HE_Vertex vf : vfneighbors) {
				if (vi == vf) {
					shared++;
					break;
				}
				if (shared > max) {
					return Double.POSITIVE_INFINITY;
				}
			}
		}
		double cost = 0.0;
		HE_Halfedge boundary;
		WB_Vector v1;
		WB_Vector v2;
		HE_Halfedge helooper = he.getNextInVertex();
		WB_Triangle T;
		WB_Plane P;
		if (f == null || fp == null) {
			boundary = he.getNextInVertex();
			while (boundary.getFace() != null && boundary.getPair().getFace() != null) {
				boundary = boundary.getNextInVertex();
			}
			v1 = he.getEndVertex().subToVector3D(he.getVertex());
			v1.normalizeSelf();
			v2 = boundary.getEndVertex().subToVector3D(boundary.getVertex());
			v2.normalizeSelf();
			cost += he.getEdge().getLength() * (1.0 - v1.dot(v2)) * _lambda;
		} else {
			do {
				final HE_Face fl = helooper.getFace();
				if (fl != null) {
					if (fl != f && fl != fp) {
						T = new WB_Triangle(he.getEndVertex(), helooper.getNextInFace().getVertex(),
								helooper.getNextInFace().getNextInFace().getVertex());
						P = T.getPlane();
						if (P == null) {
							cost += 0.5 * (T.getArea() + fl.getFaceArea());
						} else {
							cost += 0.5 * (T.getArea() + fl.getFaceArea())
									* (1.0 - WB_Vector.dot(fl.getFaceNormal(), T.getPlane().getNormal()));
						}
					}
				} else {
					return Double.POSITIVE_INFINITY;
				}
				helooper = helooper.getNextInVertex();
			} while (helooper != he);
		}
		return cost;
	}

	public class Heap {

		private final List<Entry> heap;

		private final List<Double> keys;

		/**
		 *
		 */
		public Heap() {
			heap = new FastTable<Entry>();
			keys = new FastTable<Double>();
		}

		/**
		 *
		 *
		 * @param key
		 * @param obj
		 */
		public void push(final Double key, final HE_Vertex obj) {
			heap.add(new Entry(obj, obj.getInternalLabel()));
			keys.add(key);
			pushUp(heap.size() - 1);
		}

		/**
		 *
		 *
		 * @return
		 */
		public Entry pop() {
			if (heap.size() > 0) {
				swap(0, heap.size() - 1);
				final Entry store = heap.remove(heap.size() - 1);
				keys.remove(heap.size());
				pushDown(0);
				return store;
			} else {
				return null;
			}
		}

		/**
		 *
		 *
		 * @return
		 */
		public Entry getFirst() {
			return heap.get(0);
		}

		/**
		 *
		 *
		 * @return
		 */
		public double getFirstKey() {
			return keys.get(0);
		}

		/**
		 *
		 *
		 * @param index
		 * @return
		 */
		public Entry get(final int index) {
			return heap.get(index);
		}

		/**
		 *
		 *
		 * @return
		 */
		public int size() {
			return heap.size();
		}

		/**
		 *
		 *
		 * @param i
		 * @return
		 */
		protected int parent(final int i) {
			return (i - 1) / 2;
		}

		/**
		 *
		 *
		 * @param i
		 * @return
		 */
		protected int left(final int i) {
			return 2 * i + 1;
		}

		/**
		 *
		 *
		 * @param i
		 * @return
		 */
		protected int right(final int i) {
			return 2 * i + 2;
		}

		/**
		 *
		 *
		 * @param i
		 * @param j
		 * @return
		 */
		protected boolean hasPriority(final int i, final int j) {
			return keys.get(i) <= keys.get(j);
		}

		/**
		 *
		 *
		 * @param i
		 * @param j
		 */
		protected void swap(final int i, final int j) {
			final Entry tmp = heap.get(i);
			heap.set(i, heap.get(j));
			heap.set(j, tmp);
			final Double tmpv = keys.get(i);
			keys.set(i, keys.get(j));
			keys.set(j, tmpv);
		}

		/**
		 *
		 *
		 * @param i
		 */
		public void pushDown(final int i) {
			final int left = left(i);
			final int right = right(i);
			int highest = i;
			if (left < heap.size() && !hasPriority(highest, left)) {
				highest = left;
			}
			if (right < heap.size() && !hasPriority(highest, right)) {
				highest = right;
			}
			if (highest != i) {
				swap(highest, i);
				pushDown(highest);
			}
		}

		/**
		 *
		 *
		 * @param i
		 */
		public void pushUp(int i) {
			while (i > 0 && !hasPriority(parent(i), i)) {
				swap(parent(i), i);
				i = parent(i);
			}
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			final StringBuffer s = new StringBuffer("Heap:\n");
			int rowStart = 0;
			int rowSize = 1;
			for (int i = 0; i < heap.size(); i++) {
				if (i == rowStart + rowSize) {
					s.append('\n');
					rowStart = i;
					rowSize *= 2;
				}
				s.append(get(i));
				s.append(" ");
			}
			return s.toString();
		}
	}

	class Entry {

		HE_Vertex v;

		int version;

		/**
		 *
		 *
		 * @param v
		 * @param i
		 */
		Entry(final HE_Vertex v, final int i) {
			this.v = v;
			version = i;
		}
	}
}
