public class PersonMoveEvent extends ElevatorSimulationEvent {
   private int ID;
   
   public PersonMoveEvent( Object source, Location location, int identifier )
   {
      super( source, location );
      ID = identifier;
   }
   
   public int getID()
   {
      return( ID );
   }
}
