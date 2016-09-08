package view;

public abstract class AbstractViewCreator implements ViewCreator {

	public int x;
	public int y;

	public AbstractViewCreator(int x, int y) {
		setSize(x, y);
	};

	@Override
	public void setSize(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
