import java.awt.*;
import java.util.*;
import javax.swing.*;

public class AnimatedPanel extends MovingPanel {

   private boolean animating;
   private int animationRate;
   private int animationRateCounter;
   private boolean cycleForward = true;
   private ImageIcon imageIcons[];
   private java.util.List frameSequences;
   private int currentAnimation;
   private boolean loop;
   private boolean displayLastFrame;
   private int currentFrameCounter;
   private int len;

   public AnimatedPanel( int identifier, String imageName[] )
   {
      super( identifier, imageName[0] );
      imageIcons = new ImageIcon[ imageName.length ];
      for ( int i = 0; i < imageIcons.length; i++ ) {
         // change it if problem
         imageIcons[ i ] = new ImageIcon( imageName[ i ]  );         
      }
   }

   public void animate()
   {
      super.animate();
      if ( frameSequences != null && isAnimating() ) {
         if ( animationRateCounter > animationRate ) {
            animationRateCounter = 0;
            determineNextFrame();
         }
         else
            animationRateCounter++;
      }
   }  

   private void determineNextFrame()
   {
      int frameSequence[] = ( int [] ) frameSequences.get( currentAnimation );
   //   int frameSequence = new int[ len ];
         
      if ( currentFrameCounter >= frameSequence.length ) {
         currentFrameCounter = 0;
         if ( !isLoop() ) {
            setAnimating( false );
            if ( isDisplayLastFrame() )
               currentFrameCounter = frameSequence.length-1;
         }
      }

      setCurrentFrame( frameSequence[ currentFrameCounter ] );
      currentFrameCounter++;
   }

   public void addFrameSequence( int frameSequence[] )
   {
      len = frameSequence.length;
      try {
      frameSequences = new ArrayList<int[]>(1000000);
    //  for ( int x : frameSequence )
    //     frameSequences.add( x );
      frameSequences.add( frameSequence );
      }
      catch ( Exception e )
      {
         e.printStackTrace();
      }
   }

   public boolean isAnimating()
   {
      return animating;
   }

   public void setAnimating( boolean animate )
   {
      animating = animate;
   }

   public void setCurrentFrame( int frame )
   {
      setIcon( imageIcons[ frame ] );
   }

   public void setAnimationRate( int rate )
   {
      animationRate = rate;
   }

   public int getAnimationRate()
   {
      return animationRate;
   }

   public void setLoop( boolean loopAnimation )
   {
      loop = loopAnimation;
   }

   public boolean isLoop()
   {
      return loop;
   }

   private boolean isDisplayLastFrame()
   {
      return displayLastFrame;
   }

   public void setDisplayLastFrame( boolean displayFrame )
   {
      displayLastFrame = displayFrame;
   }

   public void playAnimation( int frameSequence )
   {
      currentAnimation = frameSequence;
      currentFrameCounter = 0;
      setAnimating( true );
   }
}
