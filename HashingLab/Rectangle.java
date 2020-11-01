package HashingLab;

/**
 * Rectangle provides implementation for the rectangle shape
 * @author Montek Kalsi
 * @version 4/25/2018
 */
public class Rectangle
{
	/**
	 * Stores the length of the rectangle
	 */
	private int length;
	/**
	 * Stores the width of the rectangle
	 */
	private int width;

	/**
	 * Initializes a Rectangle object with length len and width w
	 * @param len the length of the rectangle
	 * @param w the width of the rectangle
	 */
	public Rectangle(int len, int w)
	{
		length = len;
		width = w;
	}

	/**
	 * Outputs the length of the Rectangle
	 * @return the length
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * Outputs the length of the Rectangle
	 * @return the length
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Returns a string representation of the dimensions of the rectangle
	 * @return a string representation of the dimensions of the rectangle
	 */
	public String toString()
	{
		return length + "x" + width;
	}

	/**
	 * Checks whether the current rectangle equals another one
	 * @param o the other rectangle
	 * @return true if the dimensions match
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Rectangle rectangle = (Rectangle) o;

		if (length != rectangle.length) return false;
		return width == rectangle.width;
	}

	/**
	 * Outputs a new hashCode for the rectangle based on its
	 * length and width
	 * @return a new hashCode for the rectangle
	 */
	@Override
	public int hashCode()
	{
		int result = length;
		result = 31 * result + width;
		return result;
	}
}