package entity;

public interface Table {

	public String[] getHeaders();

	public void setHeader(int position, String name);

	public int getRowCount() ;

	public int getCollCount() ;

	public double[][] getTable();

	public void updCol(double[] col, int colls);
	public boolean hasCollByName(String collName) ;
	
	public void updCol(double[] col, String collName);
	public void addCol(double[] col, String collName) ;
	public void addRow(double[] row) ;
	public void delRow(int pos) ;
	public void updRow(int pos, double[] row);
	public double getMaxX() ;
	public double getMinX() ;
	public double getMaxY() ;

	public double getMinY() ;
	public double[] getRow(int row) ;
	public double[] getColl(String collName) ;

}
