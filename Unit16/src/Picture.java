import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void keepBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void keepRed()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void keepGreen()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
        pixelObj.setRed(0);
      }
    }
  }
  
  public void negate() {
	  Pixel[][] pixels = this.getPixels2D();
	    for (Pixel[] rowArray : pixels)
	    {
	      for (Pixel pixelObj : rowArray)
	      {
	        pixelObj.setBlue(255 - pixelObj.getBlue());
	        pixelObj.setRed(255 - pixelObj.getRed());
	        pixelObj.setGreen(255 - pixelObj.getGreen());
	      }
	    }
  }
  
  public void grayscale() {
	  Pixel[][] pixels = this.getPixels2D();
	    for (Pixel[] rowArray : pixels)
	    {
	      for (Pixel pixelObj : rowArray)
	      {
	        int Blue = pixelObj.getBlue();
	        int Red = pixelObj.getRed();
	        int Green = pixelObj.getGreen();
	    	pixelObj.setBlue((Red + Blue + Green)/3);
	        pixelObj.setRed((Red + Blue + Green)/3);
	        pixelObj.setGreen((Red + Blue + Green)/3);
	      }
	    }
  }
  
  public void fixUnderwater() {
	  Pixel[][] pixels = this.getPixels2D();
	    for (Pixel[] rowArray : pixels)
	    {
	      for (Pixel pixelObj : rowArray)
	      {
	        int Blue = pixelObj.getBlue();
	        int Red = pixelObj.getRed();
	        int Green = pixelObj.getGreen();
	    	if ( Green < Blue) {
	    		pixelObj.setRed(Red * 3);
	    		
	    	}
	      }
	    }
  }
  
  public int redOver(int red) {
	  Pixel[][] pixels = this.getPixels2D();
	  int count = 0;  
	  for (Pixel[] rowArray : pixels)
	    {
	      for (Pixel pixelObj : rowArray)
	      {
	    	int Red = pixelObj.getRed();
	    	if ( Red < red) {
	    		count++;
	    		
	    	}
	      }
	    }
	  return count;
  }
  
  public void halfRed() {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (Pixel[] rowArray : pixels)
	    {
	      for (Pixel pixelObj : rowArray)
	      {
	    	if (pixelObj.getY() < pixels.length/2) {
	    		int Red = pixelObj.getRed();
	    		pixelObj.setRed(Red/2);
	    	}
	      }
	    }
  }
  
  public void clearBlue(int num) {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (Pixel[] rowArray : pixels)
	    {
	      for (Pixel pixelObj : rowArray)
	      {
	    	if (pixelObj.getBlue() > num) {
	    		pixelObj.setBlue(0);
	    	}
	      }
	    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel upPixel = null;
    Pixel downPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height / 2; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        upPixel = pixels[row][col];
        downPixel = pixels[height - 1 - row][col];
        downPixel.setColor(upPixel.getColor());
      }
    } 
  }
  
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel upPixel = null;
    Pixel downPixel = null;
    int height = pixels.length;
    if (height > pixels[0].length) {
    	height = pixels[0].length;
    }
    for (int row = 0; row < height; row++)
    {
      for (int col = 0; col < height; col++)
      {
        upPixel = pixels[col][row];
        downPixel = pixels[row][col];
        downPixel.setColor(upPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  public void mirrorArms()
  {
    int mirrorPoint = 164;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 164; row < 225; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 95; col < 168; col++)
      {
        
        leftPixel = pixels[ row ][col];      
        rightPixel = pixels[ row - mirrorPoint + 235]                       
                         [col];
        rightPixel.setColor(leftPixel.getColor());
        leftPixel = pixels[ row ][col -95 + 236];      
        rightPixel = pixels[ row - mirrorPoint + 235]                       
                         [col - 95 + 240];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  public void mirrorGull()
  {
    int mirrorPoint = 129;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 235; row < 325; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 234; col < 345; col++)
      {
        
        leftPixel = pixels[ row ][col];      
        rightPixel = pixels[ row - 15]                       
                         [col - mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  public void chromaKey(Picture fromPic, 
          int startRow, int startCol)
{
Pixel fromPixel = null;
Pixel toPixel = null;
Pixel[][] toPixels = this.getPixels2D();
Pixel[][] fromPixels = fromPic.getPixels2D();
for (int fromRow = 0, toRow = startRow; 
  fromRow < fromPixels.length &&
  toRow < toPixels.length; 
  fromRow++, toRow++)
{
for (int fromCol = 0, toCol = startCol; 
    fromCol < fromPixels[0].length &&
    toCol < toPixels[0].length;  
    fromCol++, toCol++)
{
 for (Pixel pixelObj : fromPic.getPixels()) {
	 if (pixelObj.getBlue() > 100 || 50 < pixelObj.getBlue()) {
		 System.out.print(pixelObj.getBlue());
		 fromPixel = fromPixels[fromRow][fromCol];
		 	toPixel = toPixels[toRow][toCol];
		 	toPixel.setColor(fromPixel.getColor());
	 }
 }
}
}   
}
  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("C:/Users/geremiac1730/Documents/GitHub/Geremia_Celeste_apcsa-2022/Unit16/src/images/flower1.jpg");
    Picture flower2 = new Picture("C:/Users/geremiac1730/Documents/GitHub/Geremia_Celeste_apcsa-2022/Unit16/src/images/flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("C:/Users/geremiac1730/Documents/GitHub/Geremia_Celeste_apcsa-2022/Unit16/src/images/collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("C:/Users/geremiac1730/Documents/GitHub/Geremia_Celeste_apcsa-2022/Unit16/src/images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
