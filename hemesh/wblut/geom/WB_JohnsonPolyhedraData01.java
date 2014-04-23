package wblut.geom;

/**
 * Johnson polyhedra.
 * 
 * Implemented by Frederik Vanhoutte (W:Blut), painstakingly collected by David
 * Marec. Many thanks, without David this wouldn't be here.
 * 
 * Data for the first 23 Johnson polyhedra;
 */
public class WB_JohnsonPolyhedraData01 {

	public static final String[] names = { "square pyramid",
			"pentagonal pyramid", "triangular cupola", "square cupola",
			"pentagonal cupola", "pentagonal rotunda",
			"elongated triangular pyramid", "elongated square pyramid",
			"elongated pentagonal pyramid", "gyroelongated square pyramid",
			"gyroelongated pentagonal pyramid", "triangular dipyramid",
			"pentagonal dipyramid", "elongated triangular dipyramid",
			"elongated square dipyramid", "elongated pentagonal dipyramid",
			"gyroelongated square dipyramid", "elongated triangular cupola",
			"elongated square cupola", "elongated pentagonal cupola",
			"elongated pentagonal rotunda", "gyroelongated triangular cupola",
			"gyroelongated square cupola" };

	public static final double[][][] vertices = {
			{ { 0., 0., 0.707107 }, { 0., -0.707107, 0. },
					{ 0., 0.707107, 0. }, { -0.707107, 0., 0. },
					{ 0.707107, 0., 0. } },
			{ { 0., 0., 0.525731 }, { 0.850651, 0., 0. },
					{ 0.262866, -0.809017, 0. }, { 0.262866, 0.809017, 0. },
					{ -0.688191, -0.5, 0. }, { -0.688191, 0.5, 0. } },
			{ { 0., -1., 0. }, { 0., 1., 0. }, { -0.288675, -0.5, 0.816497 },
					{ -0.288675, 0.5, 0.816497 }, { 0.57735, 0., 0.816497 },
					{ -0.866025, -0.5, 0. }, { -0.866025, 0.5, 0. },
					{ 0.866025, -0.5, 0. }, { 0.866025, 0.5, 0. } },
			{ { 0., -0.707107, 0.707107 }, { 0., 0.707107, 0.707107 },
					{ -0.707107, 0., 0.707107 }, { 0.707107, 0., 0.707107 },
					{ 0.5, 1.20711, 0. }, { -0.5, 1.20711, 0. },
					{ -1.20711, 0.5, 0. }, { -1.20711, -0.5, 0. },
					{ -0.5, -1.20711, 0. }, { 0.5, -1.20711, 0. },
					{ 1.20711, -0.5, 0. }, { 1.20711, 0.5, 0. } },
			{ { 0., -1.61803, 0. }, { 0., 1.61803, 0. },
					{ 0.850651, 0., 0.525731 },
					{ 0.262866, -0.809017, 0.525731 },
					{ 0.262866, 0.809017, 0.525731 },
					{ -0.951057, -1.30902, 0. }, { -0.951057, 1.30902, 0. },
					{ 0.951057, -1.30902, 0. }, { 0.951057, 1.30902, 0. },
					{ -0.688191, -0.5, 0.525731 },
					{ -0.688191, 0.5, 0.525731 }, { -1.53884, -0.5, 0. },
					{ -1.53884, 0.5, 0. }, { 1.53884, -0.5, 0. },
					{ 1.53884, 0.5, 0. } },
			{ { 0., -1.61803, 0. }, { 0., 1.61803, 0. },
					{ -0.951057, -1.30902, 0. }, { -0.951057, 1.30902, 0. },
					{ 0.951057, -1.30902, 0. }, { 0.951057, 1.30902, 0. },
					{ -1.53884, -0.5, 0. }, { -1.53884, 0.5, 0. },
					{ 1.53884, -0.5, 0. }, { 1.53884, 0.5, 0. },
					{ 1.37638, 0., 0.850651 },
					{ 0.425325, -1.30902, 0.850651 },
					{ 0.425325, 1.30902, 0.850651 },
					{ -1.11352, -0.809017, 0.850651 },
					{ -1.11352, 0.809017, 0.850651 },
					{ -0.850651, 0., 1.37638 },
					{ -0.262866, -0.809017, 1.37638 },
					{ -0.262866, 0.809017, 1.37638 },
					{ 0.688191, -0.5, 1.37638 }, { 0.688191, 0.5, 1.37638 } },
			{ { 0., 0., 1.8165 }, { -0.288675, -0.5, 0. },
					{ -0.288675, -0.5, 1. }, { -0.288675, 0.5, 0. },
					{ -0.288675, 0.5, 1. }, { 0.57735, 0., 0. },
					{ 0.57735, 0., 1. } },
			{ { 0., 0., 1.70711 }, { 0., -0.707107, 0. },
					{ 0., -0.707107, 1. }, { 0., 0.707107, 0. },
					{ 0., 0.707107, 1. }, { -0.707107, 0., 0. },
					{ -0.707107, 0., 1. }, { 0.707107, 0., 0. },
					{ 0.707107, 0., 1. } },
			{ { 0., 0., 1.52573 }, { 0.850651, 0., 0. }, { 0.850651, 0., 1. },
					{ 0.262866, -0.809017, 0. }, { 0.262866, -0.809017, 1. },
					{ 0.262866, 0.809017, 0. }, { 0.262866, 0.809017, 1. },
					{ -0.688191, -0.5, 0. }, { -0.688191, -0.5, 1. },
					{ -0.688191, 0.5, 0. }, { -0.688191, 0.5, 1. } },
			{ { -0.5, -0.5, -0.420448 }, { -0.5, 0.5, -0.420448 },
					{ 0., 0., 1.12755 }, { 0., -0.707107, 0.420448 },
					{ 0., 0.707107, 0.420448 }, { 0.5, -0.5, -0.420448 },
					{ 0.5, 0.5, -0.420448 }, { -0.707107, 0., 0.420448 },
					{ 0.707107, 0., 0.420448 } },
			{ { 0., 0., 0.951057 }, { -0.850651, 0., -0.425325 },
					{ 0.850651, 0., 0.425325 },
					{ -0.262866, -0.809017, -0.425325 },
					{ -0.262866, 0.809017, -0.425325 },
					{ 0.262866, -0.809017, 0.425325 },
					{ 0.262866, 0.809017, 0.425325 },
					{ -0.688191, -0.5, 0.425325 },
					{ -0.688191, 0.5, 0.425325 },
					{ 0.688191, -0.5, -0.425325 }, { 0.688191, 0.5, -0.425325 } },
			{ { 0., 0., -0.816497 }, { 0., 0., 0.816497 },
					{ -0.288675, -0.5, 0. }, { -0.288675, 0.5, 0. },
					{ 0.57735, 0., 0. } },
			{ { 0., 0., -0.525731 }, { 0., 0., 0.525731 },
					{ 0.850651, 0., 0. }, { 0.262866, -0.809017, 0. },
					{ 0.262866, 0.809017, 0. }, { -0.688191, -0.5, 0. },
					{ -0.688191, 0.5, 0. } },
			{ { 0., 0., -1.3165 }, { 0., 0., 1.3165 },
					{ -0.288675, -0.5, -0.5 }, { -0.288675, -0.5, 0.5 },
					{ -0.288675, 0.5, -0.5 }, { -0.288675, 0.5, 0.5 },
					{ 0.57735, 0., -0.5 }, { 0.57735, 0., 0.5 } },
			{ { 0., 0., -1.20711 }, { 0., 0., 1.20711 },
					{ 0., -0.707107, -0.5 }, { 0., -0.707107, 0.5 },
					{ 0., 0.707107, -0.5 }, { 0., 0.707107, 0.5 },
					{ -0.707107, 0., -0.5 }, { -0.707107, 0., 0.5 },
					{ 0.707107, 0., -0.5 }, { 0.707107, 0., 0.5 } },
			{ { 0., 0., -1.02573 }, { 0., 0., 1.02573 },
					{ 0.850651, 0., -0.5 }, { 0.850651, 0., 0.5 },
					{ 0.262866, -0.809017, -0.5 },
					{ 0.262866, -0.809017, 0.5 }, { 0.262866, 0.809017, -0.5 },
					{ 0.262866, 0.809017, 0.5 }, { -0.688191, -0.5, -0.5 },
					{ -0.688191, -0.5, 0.5 }, { -0.688191, 0.5, -0.5 },
					{ -0.688191, 0.5, 0.5 } },
			{ { -0.5, -0.5, -0.420448 }, { -0.5, 0.5, -0.420448 },
					{ 0., 0., -1.12755 }, { 0., 0., 1.12755 },
					{ 0., -0.707107, 0.420448 }, { 0., 0.707107, 0.420448 },
					{ 0.5, -0.5, -0.420448 }, { 0.5, 0.5, -0.420448 },
					{ -0.707107, 0., 0.420448 }, { 0.707107, 0., 0.420448 } },
			{ { 0., -1., -1. }, { 0., -1., 0. }, { 0., 1., -1. },
					{ 0., 1., 0. }, { -0.288675, -0.5, 0.816497 },
					{ -0.288675, 0.5, 0.816497 }, { 0.57735, 0., 0.816497 },
					{ -0.866025, -0.5, -1. }, { -0.866025, -0.5, 0. },
					{ -0.866025, 0.5, -1. }, { -0.866025, 0.5, 0. },
					{ 0.866025, -0.5, -1. }, { 0.866025, -0.5, 0. },
					{ 0.866025, 0.5, -1. }, { 0.866025, 0.5, 0. } },
			{ { 0., -0.707107, 0.707107 }, { 0., 0.707107, 0.707107 },
					{ -0.707107, 0., 0.707107 }, { 0.707107, 0., 0.707107 },
					{ 0.5, 1.20711, -1. }, { 0.5, 1.20711, 0. },
					{ -0.5, 1.20711, -1. }, { -0.5, 1.20711, 0. },
					{ -1.20711, 0.5, -1. }, { -1.20711, 0.5, 0. },
					{ -1.20711, -0.5, -1. }, { -1.20711, -0.5, 0. },
					{ -0.5, -1.20711, -1. }, { -0.5, -1.20711, 0. },
					{ 0.5, -1.20711, -1. }, { 0.5, -1.20711, 0. },
					{ 1.20711, -0.5, -1. }, { 1.20711, -0.5, 0. },
					{ 1.20711, 0.5, -1. }, { 1.20711, 0.5, 0. } },
			{ { 0., -1.61803, -1. }, { 0., -1.61803, 0. },
					{ 0., 1.61803, -1. }, { 0., 1.61803, 0. },
					{ 0.850651, 0., 0.525731 },
					{ 0.262866, -0.809017, 0.525731 },
					{ 0.262866, 0.809017, 0.525731 },
					{ -0.951057, -1.30902, -1. }, { -0.951057, -1.30902, 0. },
					{ -0.951057, 1.30902, -1. }, { -0.951057, 1.30902, 0. },
					{ 0.951057, -1.30902, -1. }, { 0.951057, -1.30902, 0. },
					{ 0.951057, 1.30902, -1. }, { 0.951057, 1.30902, 0. },
					{ -0.688191, -0.5, 0.525731 },
					{ -0.688191, 0.5, 0.525731 }, { -1.53884, -0.5, -1. },
					{ -1.53884, -0.5, 0. }, { -1.53884, 0.5, -1. },
					{ -1.53884, 0.5, 0. }, { 1.53884, -0.5, -1. },
					{ 1.53884, -0.5, 0. }, { 1.53884, 0.5, -1. },
					{ 1.53884, 0.5, 0. } },
			{ { 0., -1.61803, -1. }, { 0., -1.61803, 0. },
					{ 0., 1.61803, -1. }, { 0., 1.61803, 0. },
					{ -0.951057, -1.30902, -1. }, { -0.951057, -1.30902, 0. },
					{ -0.951057, 1.30902, -1. }, { -0.951057, 1.30902, 0. },
					{ 0.951057, -1.30902, -1. }, { 0.951057, -1.30902, 0. },
					{ 0.951057, 1.30902, -1. }, { 0.951057, 1.30902, 0. },
					{ -1.53884, -0.5, -1. }, { -1.53884, -0.5, 0. },
					{ -1.53884, 0.5, -1. }, { -1.53884, 0.5, 0. },
					{ 1.53884, -0.5, -1. }, { 1.53884, -0.5, 0. },
					{ 1.53884, 0.5, -1. }, { 1.53884, 0.5, 0. },
					{ 1.37638, 0., 0.850651 },
					{ 0.425325, -1.30902, 0.850651 },
					{ 0.425325, 1.30902, 0.850651 },
					{ -1.11352, -0.809017, 0.850651 },
					{ -1.11352, 0.809017, 0.850651 },
					{ -0.850651, 0., 1.37638 },
					{ -0.262866, -0.809017, 1.37638 },
					{ -0.262866, 0.809017, 1.37638 },
					{ 0.688191, -0.5, 1.37638 }, { 0.688191, 0.5, 1.37638 } },
			{ { -1., 0., -0.8556 }, { -0.5, -0.866025, -0.8556 },
					{ -0.5, 0.866025, -0.8556 }, { 0., -1., 0. },
					{ 0., 1., 0. }, { 0.5, -0.866025, -0.8556 },
					{ 0.5, 0.866025, -0.8556 }, { 1., 0., -0.8556 },
					{ -0.288675, -0.5, 0.816497 },
					{ -0.288675, 0.5, 0.816497 }, { 0.57735, 0., 0.816497 },
					{ -0.866025, -0.5, 0. }, { -0.866025, 0.5, 0. },
					{ 0.866025, -0.5, 0. }, { 0.866025, 0.5, 0. } },
			{ { 0., -0.707107, 0.707107 }, { 0., 0.707107, 0.707107 },
					{ 0., -1.30656, -0.860296 }, { 0., 1.30656, -0.860296 },
					{ -0.707107, 0., 0.707107 }, { 0.707107, 0., 0.707107 },
					{ -1.20711, -0.5, 0. }, { -1.30656, 0., -0.860296 },
					{ 1.30656, 0., -0.860296 },
					{ -0.92388, -0.92388, -0.860296 },
					{ -0.92388, 0.92388, -0.860296 },
					{ 0.92388, -0.92388, -0.860296 },
					{ 0.92388, 0.92388, -0.860296 }, { -0.5, -1.20711, 0. },
					{ 0.5, 1.20711, 0. }, { 0.5, -1.20711, 0. },
					{ -0.5, 1.20711, 0. }, { 1.20711, -0.5, 0. },
					{ -1.20711, 0.5, 0. }, { 1.20711, 0.5, 0. } } };

	public static final int[][][] faces = {
			{ { 2, 3, 0 }, { 3, 1, 0 }, { 1, 4, 0 }, { 4, 2, 0 },
					{ 4, 1, 3, 2 } },
			{ { 3, 5, 0 }, { 5, 4, 0 }, { 4, 2, 0 }, { 2, 1, 0 }, { 1, 3, 0 },
					{ 1, 2, 4, 5, 3 } },
			{ { 3, 2, 4 }, { 8, 7, 0, 5, 6, 1 }, { 2, 3, 6, 5 },
					{ 4, 2, 0, 7 }, { 3, 4, 8, 1 }, { 1, 6, 3 }, { 5, 0, 2 },
					{ 7, 8, 4 } },
			{ { 1, 2, 0, 3 }, { 11, 10, 9, 8, 7, 6, 5, 4 }, { 2, 1, 5, 6 },
					{ 0, 2, 7, 8 }, { 3, 0, 9, 10 }, { 1, 3, 11, 4 },
					{ 4, 5, 1 }, { 6, 7, 2 }, { 8, 9, 0 }, { 10, 11, 3 } },
			{ { 4, 10, 9, 3, 2 }, { 14, 13, 7, 0, 5, 11, 12, 6, 1, 8 },
					{ 10, 4, 1, 6 }, { 9, 10, 12, 11 }, { 3, 9, 5, 0 },
					{ 2, 3, 7, 13 }, { 4, 2, 14, 8 }, { 8, 1, 4 },
					{ 6, 12, 10 }, { 11, 5, 9 }, { 0, 7, 3 }, { 13, 14, 2 } },
			{ { 17, 15, 16, 18, 19 }, { 14, 15, 17 }, { 13, 16, 15 },
					{ 11, 18, 16 }, { 10, 19, 18 }, { 12, 17, 19 },
					{ 5, 1, 12 }, { 3, 7, 14 }, { 6, 2, 13 }, { 0, 4, 11 },
					{ 8, 9, 10 }, { 1, 3, 14, 17, 12 }, { 7, 6, 13, 15, 14 },
					{ 2, 0, 11, 16, 13 }, { 4, 8, 10, 18, 11 },
					{ 9, 5, 12, 19, 10 }, { 9, 8, 4, 0, 2, 6, 7, 3, 1, 5 } },
			{ { 4, 2, 0 }, { 2, 6, 0 }, { 6, 4, 0 }, { 3, 1, 2, 4 },
					{ 1, 5, 6, 2 }, { 5, 3, 4, 6 }, { 5, 1, 3 } },
			{ { 4, 6, 0 }, { 6, 2, 0 }, { 2, 8, 0 }, { 8, 4, 0 },
					{ 3, 5, 6, 4 }, { 5, 1, 2, 6 }, { 1, 7, 8, 2 },
					{ 7, 3, 4, 8 }, { 7, 1, 5, 3 } },
			{ { 6, 10, 0 }, { 10, 8, 0 }, { 8, 4, 0 }, { 4, 2, 0 },
					{ 2, 6, 0 }, { 5, 9, 10, 6 }, { 9, 7, 8, 10 },
					{ 7, 3, 4, 8 }, { 3, 1, 2, 4 }, { 1, 5, 6, 2 },
					{ 1, 3, 7, 9, 5 } },
			{ { 5, 0, 1, 6 }, { 6, 4, 8 }, { 1, 7, 4 }, { 0, 3, 7 },
					{ 5, 8, 3 }, { 6, 1, 4 }, { 1, 0, 7 }, { 0, 5, 3 },
					{ 5, 6, 8 }, { 4, 7, 2 }, { 7, 3, 2 }, { 3, 8, 2 },
					{ 8, 4, 2 } },
			{ { 9, 3, 1, 4, 10 }, { 10, 6, 2 }, { 4, 8, 6 }, { 1, 7, 8 },
					{ 3, 5, 7 }, { 9, 2, 5 }, { 10, 4, 6 }, { 4, 1, 8 },
					{ 1, 3, 7 }, { 3, 9, 5 }, { 9, 10, 2 }, { 6, 8, 0 },
					{ 8, 7, 0 }, { 7, 5, 0 }, { 5, 2, 0 }, { 2, 6, 0 } },
			{ { 3, 2, 1 }, { 2, 4, 1 }, { 4, 3, 1 }, { 0, 2, 3 }, { 0, 4, 2 },
					{ 0, 3, 4 } },
			{ { 4, 6, 1 }, { 6, 5, 1 }, { 5, 3, 1 }, { 3, 2, 1 }, { 2, 4, 1 },
					{ 0, 6, 4 }, { 0, 5, 6 }, { 0, 3, 5 }, { 0, 2, 3 },
					{ 0, 4, 2 } },
			{ { 5, 3, 1 }, { 3, 7, 1 }, { 7, 5, 1 }, { 0, 2, 4 }, { 0, 6, 2 },
					{ 0, 4, 6 }, { 4, 2, 3, 5 }, { 2, 6, 7, 3 }, { 6, 4, 5, 7 } },
			{ { 5, 7, 1 }, { 7, 3, 1 }, { 3, 9, 1 }, { 9, 5, 1 }, { 0, 6, 4 },
					{ 0, 2, 6 }, { 0, 8, 2 }, { 0, 4, 8 }, { 4, 6, 7, 5 },
					{ 6, 2, 3, 7 }, { 2, 8, 9, 3 }, { 8, 4, 5, 9 } },
			{ { 7, 11, 1 }, { 11, 9, 1 }, { 9, 5, 1 }, { 5, 3, 1 },
					{ 3, 7, 1 }, { 0, 10, 6 }, { 0, 8, 10 }, { 0, 4, 8 },
					{ 0, 2, 4 }, { 0, 6, 2 }, { 6, 10, 11, 7 },
					{ 10, 8, 9, 11 }, { 8, 4, 5, 9 }, { 4, 2, 3, 5 },
					{ 2, 6, 7, 3 } },
			{ { 7, 5, 9 }, { 1, 8, 5 }, { 0, 4, 8 }, { 6, 9, 4 }, { 7, 1, 5 },
					{ 1, 0, 8 }, { 0, 6, 4 }, { 6, 7, 9 }, { 5, 8, 3 },
					{ 8, 4, 3 }, { 4, 9, 3 }, { 9, 5, 3 }, { 2, 1, 7 },
					{ 2, 0, 1 }, { 2, 6, 0 }, { 2, 7, 6 } },
			{ { 4, 5, 10, 8 }, { 6, 4, 1, 12 }, { 5, 6, 14, 3 }, { 3, 10, 5 },
					{ 8, 1, 4 }, { 12, 14, 6 }, { 5, 4, 6 },
					{ 13, 11, 0, 7, 9, 2 }, { 2, 9, 10, 3 }, { 9, 7, 8, 10 },
					{ 7, 0, 1, 8 }, { 0, 11, 12, 1 }, { 11, 13, 14, 12 },
					{ 13, 2, 3, 14 } },
			{ { 2, 1, 7, 9 }, { 0, 2, 11, 13 }, { 3, 0, 15, 17 },
					{ 1, 3, 19, 5 }, { 5, 7, 1 }, { 9, 11, 2 }, { 13, 15, 0 },
					{ 17, 19, 3 }, { 1, 2, 0, 3 },
					{ 18, 16, 14, 12, 10, 8, 6, 4 }, { 4, 6, 7, 5 },
					{ 6, 8, 9, 7 }, { 8, 10, 11, 9 }, { 10, 12, 13, 11 },
					{ 12, 14, 15, 13 }, { 14, 16, 17, 15 }, { 16, 18, 19, 17 },
					{ 18, 4, 5, 19 } },
			{ { 16, 6, 3, 10 }, { 15, 16, 20, 18 }, { 5, 15, 8, 1 },
					{ 4, 5, 12, 22 }, { 6, 4, 24, 14 }, { 14, 3, 6 },
					{ 10, 20, 16 }, { 18, 8, 15 }, { 1, 12, 5 }, { 22, 24, 4 },
					{ 6, 16, 15, 5, 4 },
					{ 23, 21, 11, 0, 7, 17, 19, 9, 2, 13 }, { 13, 2, 3, 14 },
					{ 2, 9, 10, 3 }, { 9, 19, 20, 10 }, { 19, 17, 18, 20 },
					{ 17, 7, 8, 18 }, { 7, 0, 1, 8 }, { 0, 11, 12, 1 },
					{ 11, 21, 22, 12 }, { 21, 23, 24, 22 }, { 23, 13, 14, 24 } },
			{ { 27, 25, 26, 28, 29 }, { 24, 25, 27 }, { 23, 26, 25 },
					{ 21, 28, 26 }, { 20, 29, 28 }, { 22, 27, 29 },
					{ 11, 3, 22 }, { 7, 15, 24 }, { 13, 5, 23 }, { 1, 9, 21 },
					{ 17, 19, 20 }, { 3, 7, 24, 27, 22 },
					{ 15, 13, 23, 25, 24 }, { 5, 1, 21, 26, 23 },
					{ 9, 17, 20, 28, 21 }, { 19, 11, 22, 29, 20 },
					{ 18, 16, 8, 0, 4, 12, 14, 6, 2, 10 }, { 10, 2, 3, 11 },
					{ 2, 6, 7, 3 }, { 6, 14, 15, 7 }, { 14, 12, 13, 15 },
					{ 12, 4, 5, 13 }, { 4, 0, 1, 5 }, { 0, 8, 9, 1 },
					{ 8, 16, 17, 9 }, { 16, 18, 19, 17 }, { 18, 10, 11, 19 } },
			{ { 2, 6, 7, 5, 1, 0 }, { 0, 1, 11 }, { 1, 5, 3 }, { 5, 7, 13 },
					{ 7, 6, 14 }, { 6, 2, 4 }, { 2, 0, 12 }, { 1, 3, 11 },
					{ 5, 13, 3 }, { 7, 14, 13 }, { 6, 4, 14 }, { 2, 12, 4 },
					{ 0, 11, 12 }, { 9, 8, 10 }, { 8, 9, 12, 11 },
					{ 10, 8, 3, 13 }, { 9, 10, 14, 4 }, { 4, 12, 9 },
					{ 11, 3, 8 }, { 13, 14, 10 } },
			{ { 10, 3, 12, 8, 11, 2, 9, 7 }, { 7, 9, 6 }, { 9, 2, 13 },
					{ 2, 11, 15 }, { 11, 8, 17 }, { 8, 12, 19 }, { 12, 3, 14 },
					{ 3, 10, 16 }, { 10, 7, 18 }, { 9, 13, 6 }, { 2, 15, 13 },
					{ 11, 17, 15 }, { 8, 19, 17 }, { 12, 14, 19 },
					{ 3, 16, 14 }, { 10, 18, 16 }, { 7, 6, 18 },
					{ 1, 4, 0, 5 }, { 4, 1, 16, 18 }, { 0, 4, 6, 13 },
					{ 5, 0, 15, 17 }, { 1, 5, 19, 14 }, { 14, 16, 1 },
					{ 18, 6, 4 }, { 13, 15, 0 }, { 17, 19, 5 } } };
}
