public class Floor extends Location implements ElevatorConstants {

   private ElevatorShaft elevatorShaft;
   
   public Floor( String name )
   {
      setLocationName( name );
   }

   public Button getButton()
   {
      if ( getLocationName().equals( FIRST_FLOOR_NAME ) )
         return getElevatorShaft().getFirstFloorButton(); 
      
      else if ( getLocationName().equals( SECOND_FLOOR_NAME ) )
         return getElevatorShaft().getSecondFloorButton();
 
      else
         return null;
   }

   public Door getDoor()
   {
      if ( getLocationName().equals( FIRST_FLOOR_NAME ) )
         return getElevatorShaft().getFirstFloorDoor();

      else if ( getLocationName().equals( SECOND_FLOOR_NAME ) )
         return getElevatorShaft().getSecondFloorDoor();

      else
         return null;
   }

   public ElevatorShaft getElevatorShaft()
   {
      return elevatorShaft;
   }

   public void setElevatorShaft( ElevatorShaft shaft )
   {
      elevatorShaft = shaft;
   }
}
