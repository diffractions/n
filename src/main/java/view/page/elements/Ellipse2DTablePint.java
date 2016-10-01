//package view.page.elements;
//
//import java.awt.geom.Ellipse2D;
//
//import entity.Table;
//
//public class Ellipse2DTablePint extends Ellipse2D.Double {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @param x
//	 * @param y
//	 * @param w
//	 * @param h
//	 */
//	public Ellipse2DTablePint(double x, double y, double w, double h) {
//		super(x, y, w, h);
//		// TODO Auto-generated constructor stub
//	}
//
//	private Table table = null;
//	private int collNumber;
//	private int rowNumber;
//	private double multiplyX;
//	private double multiplyY;
//	private double xmin;
//	private double ymin;
//
//	public Ellipse2DTablePint(double x, double y, double w, double h,
//			Table table, int collNumber, int rowNumber, double multiplyX,double multiplyy, double xmin,double ymin) {
//		super(x, y, w, h);
//		this.table = table;
//		this.collNumber = collNumber;
//		this.rowNumber = rowNumber;
//		this.multiplyX = multiplyX;
//		this.multiplyY = multiplyy;
//		this.xmin = xmin;
//		this.ymin = ymin;
//
//	}
// 
//	public void setFrames(double x, double y, double w, double h) {
////		super.setFrame((int)x,(int) y, w, h);
////		System.out.println(x/multiplyX +" " + y/multiplyY);
//		table.getTable()[rowNumber][0] =  xmin+(x/multiplyX);
//		table.getTable()[rowNumber][collNumber] =  ymin+( y/multiplyY);
//		System.out.println(1);
//	}
//}
