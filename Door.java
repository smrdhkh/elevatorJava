import java.util.*;

public class Door {
   private boolean open = false;
   public static final int AUTOMATIC_CLOSE_DELAY = 3000;

   private Set doorListeners;
   private Location doorLocation;

   public Door()
   {
      doorListeners = new HashSet( 1 );
   }

   public void addDoorListener( DoorListener listener )
   {
      synchronized( doorListeners )
      {
         doorListeners.add( listener );
      }
   }

   public void removeDoorListener( DoorListener listener )
   {
      synchronized( doorListeners )
      {
         doorListeners.remove( listener );
      }
   }
   
   public synchronized void openDoor( Location location )
   {
      if ( !open ) {
         open = true;
         Iterator iterator;
         synchronized( doorListeners )
         {
            iterator = new HashSet( doorListeners ).iterator();
         }

         while ( iterator.hasNext() ) {
            DoorListener doorListener = ( DoorListener ) iterator.next();
            doorListener.doorOpened( new DoorEvent( this, location ) );
         }
         doorLocation = location;
         Thread closeThread = new Thread(
            new Runnable() {
               public void run()
               {
                  try {
                     Thread.sleep( AUTOMATIC_CLOSE_DELAY );
                     closeDoor( doorLocation );
                  }
                  catch ( InterruptedException exception )
                  {
                     exception.printStackTrace();
                  }
               }
            }
         );
         closeThread.start();
      } 
      notifyAll();
   }
  
   public synchronized void closeDoor( Location location )
   {
      if( open ) {
         open = false;

         Iterator iterator;
         synchronized( doorListeners )
         {
            iterator = new HashSet( doorListeners ).iterator();
         }

         while ( iterator.hasNext() ) {
            DoorListener doorListener = ( DoorListener ) iterator.next();
            doorListener.doorClosed( new DoorEvent( this, location ) );
         }
      }
   }

   public synchronized boolean isDoorOpen()
   {
      return open;
   }
}
