package javaExercise.concurrent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import javax.imageio.ImageIO;

public class ForkJoinPoolDemo {
	
	public static void main(String [] args) throws IOException {
		String srcName = "ForkJoinPoolDemo.jpg";
        File srcFile = new File(srcName);
        BufferedImage image = ImageIO.read(srcFile);
        
        System.out.println("Source image: " + srcName);
        
        blur(image);
	}
	
	private static BufferedImage blur(BufferedImage srcImage) {
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        System.out.println("Array size is " + src.length);
        System.out.println("Threshold is " + 100000);

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(Integer.toString(processors) + " processor"
                + (processors != 1 ? "s are " : " is ")
                + "available");

        ForkBlur fb = new ForkJoinPoolDemo().new ForkBlur(src, 0, src.length, dst);

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();
        pool.invoke(fb);
        long endTime = System.currentTimeMillis();

        System.out.println("Image blur took " + (endTime - startTime) + 
                " milliseconds.");

        BufferedImage dstImage =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);

        return dstImage;
    }
	
	class ForkBlur extends RecursiveAction {
		private static final long serialVersionUID = -2207382099287812128L;
		
		private int[] mSource;
	    private int mStart;
	    private int mLength;
	    private int[] mDestination;
	  
	    // Processing window size; should be odd.
	    private int mBlurWidth = 15;
	    
	    public ForkBlur(int[] src, int start, int length, int[] dst) {
	        mSource = src;
	        mStart = start;
	        mLength = length;
	        mDestination = dst;
	    }
	    
	    protected void computeDirectly() {
	        int sidePixels = (mBlurWidth - 1) / 2;
	        for (int index = mStart; index < mStart + mLength; index++) {
	            // Calculate average.
	            float rt = 0, gt = 0, bt = 0;
	            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
	                int mindex = Math.min(Math.max(mi + index, 0),
	                                    mSource.length - 1);
	                int pixel = mSource[mindex];
	                rt += (float)((pixel & 0x00ff0000) >> 16)
	                      / mBlurWidth;
	                gt += (float)((pixel & 0x0000ff00) >>  8)
	                      / mBlurWidth;
	                bt += (float)((pixel & 0x000000ff) >>  0)
	                      / mBlurWidth;
	            }
	          
	            // Reassemble destination pixel.
	            int dpixel = (0xff000000     ) |
	                   (((int)rt) << 16) |
	                   (((int)gt) <<  8) |
	                   (((int)bt) <<  0);
	            mDestination[index] = dpixel;
	        }
	    }

		@Override
		protected void compute() {
			if (mLength < 100000) {
		        computeDirectly();
		        return;
		    }
		    
		    int split = mLength / 2;
		    
		    invokeAll(new ForkBlur(mSource, mStart, split, mDestination),
		              new ForkBlur(mSource, mStart + split, mLength - split,
		                           mDestination));
		}
		
	}

}
