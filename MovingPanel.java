import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class MovingPanel extends ImagePanel {
   private boolean moving;
   private double xVelocity;
   private double yVelocity;

   public MovingPanel( int identifier, String imageName )
   {
      super( identifier, imageName );
      xVelocity = 0;
      yVelocity = 0;
   }

   public void animate()
   {
      if ( isMoving() ) {
         double oldXPosition = getPosition().getX();
         double oldYPosition = getPosition().getY();
         setPosition( oldXPosition + xVelocity, oldYPosition + yVelocity );
      }

      Iterator iterator = getChildren().iterator();
      while ( iterator.hasNext() ) {
         MovingPanel panel = ( MovingPanel ) iterator.next();
         panel.animate();
      }
   }

   public boolean isMoving()
   {
      return moving;
   }

   public void setMoving( boolean move )
   {
      moving = move;
   }

   public void setVelocity( double x, double y )
   {
      xVelocity = x;
      yVelocity = y;
   }

   public double getXVelocity()
   {
      return xVelocity;
   }

   public double getYVelocity()
   {
      return yVelocity;
   }
}
