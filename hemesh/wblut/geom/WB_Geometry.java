package wblut.geom;


public interface WB_Geometry {
	public static final WB_GeometryFactory geometryfactory = WB_GeometryFactory
			.instance();

	public WB_GeometryType getType();

	public int getDimension();

	public int getEmbeddingDimension();

	public WB_Geometry apply(WB_Transform T);

}
