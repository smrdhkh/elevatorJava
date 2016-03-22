import java.util.*;

public class ElevatorDoor extends Door implements ElevatorMoveListener {

   public synchronized void openDoor( Location location )
   {
      location.getDoor().openDoor( location );       
      super.openDoor( location );
   }

   public synchronized void closeDoor( Location location )
   {
      location.getDoor().closeDoor( location );
      super.closeDoor( location );      
   }

   public void elevatorDeparted( ElevatorMoveEvent moveEvent ) {}
 
   public void elevatorArrived( ElevatorMoveEvent moveEvent )
   {
      openDoor( moveEvent.getLocation() );
   }
}
