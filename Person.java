import java.util.*;

public class Person extends Thread {

   private int ID = -1;
   private boolean moving = true;
   private Location location;
   private PersonMoveListener personMoveListener;
   private static final int TIME_TO_WALK = 3000;
   public static final int PERSON_CREATED = 1;
   public static final int PERSON_ARRIVED = 2;
   public static final int PERSON_ENTERING_ELEVATOR = 3;
   public static final int PERSON_PRESSING_BUTTON = 4;
   public static final int PERSON_EXITING_ELEVATOR = 5;
   public static final int PERSON_EXITED = 6;

   public Person( int identifier, Location initialLocation )
   {
      super();
      ID = identifier;
      location = initialLocation;
      moving = true;
   }
   
   public void setPersonMoveListener( PersonMoveListener listener )
   {
      personMoveListener = listener;
   }

   private void setLocation( Location newLocation )
   {
      location = newLocation;
   }

   private Location getLocation()
   {
      return location;
   }

   public int getID()
   {
      return ID;
   }

   public void setMoving( boolean personMoving )
   {
      moving = personMoving;
   }

   public boolean isMoving()
   {
      return moving;
   }

   public void run()
   {
      sendPersonMoveEvent( PERSON_CREATED );
      pauseThread( TIME_TO_WALK );
      setMoving( false );
      sendPersonMoveEvent( PERSON_ARRIVED );
      Door currentFloorDoor = location.getDoor();
      Elevator elevator = ( (Floor) getLocation() ).getElevatorShaft().getElevator();
      synchronized( currentFloorDoor ) {
         if ( !currentFloorDoor.isDoorOpen() ) {
            sendPersonMoveEvent( PERSON_PRESSING_BUTTON );
            pauseThread( 1000 );
            Button floorButton = getLocation().getButton();
            floorButton.pressButton( getLocation() );
         }
         try {
            while ( !currentFloorDoor.isDoorOpen() )
               currentFloorDoor.wait();
         }
         catch ( InterruptedException exception )
         {
            exception.printStackTrace();
         }
         pauseThread( 1000 );
         synchronized ( elevator ) {
            sendPersonMoveEvent( PERSON_ENTERING_ELEVATOR );
            setLocation( elevator );
            pauseThread( 1000 );
            sendPersonMoveEvent( PERSON_PRESSING_BUTTON );
            pauseThread( 1000 );
            Button elevatorButton = getLocation().getButton();
            elevatorButton.pressButton( location );
            pauseThread( 1000 );
         }
      }

      synchronized( elevator ) {
         Door elevatorDoor = getLocation().getDoor();
         synchronized( elevatorDoor ) {
            try {
               while ( !elevatorDoor.isDoorOpen() )
                  elevatorDoor.wait();
            }   
            catch ( InterruptedException exception )
            {
               exception.printStackTrace();
            }
 
           pauseThread( 1000 );
            setLocation( elevator.getCurrentFloor() );
            setMoving( true );
            sendPersonMoveEvent( PERSON_EXITING_ELEVATOR );
         }
      }     

      pauseThread( 2 * TIME_TO_WALK );
      sendPersonMoveEvent( PERSON_EXITED );
   }

   private void pauseThread( int milliseconds )
   {
      try {
         sleep( milliseconds );
      }
      catch ( InterruptedException exception )
      {
         exception.printStackTrace();
      }
   }

   private void sendPersonMoveEvent( int eventType )
   {
      PersonMoveEvent event = new PersonMoveEvent( this, getLocation(), getID() );
      switch ( eventType ) {
         case PERSON_CREATED:
            personMoveListener.personCreated( event );
            break;

         case PERSON_ARRIVED:
            personMoveListener.personArrived( event );
            break;

         case PERSON_ENTERING_ELEVATOR:
            personMoveListener.personEntered( event );
            break;
         
         case PERSON_PRESSING_BUTTON:
            personMoveListener.personPressedButton( event );
            break;

         case PERSON_EXITING_ELEVATOR:
            personMoveListener.personDeparted( event );
            break;

         case PERSON_EXITED:
            personMoveListener.personExited( event );
            break;
 
         default:
            break;
      }
   }
}
