public class Bell implements ElevatorMoveListener {

   private BellListener bellListener;

   private void ringBell( Location location )
   {
      if ( bellListener != null )
         bellListener.bellRang( new BellEvent( this, location ) );
   }

   public void setBellListener( BellListener listener )
   {
      bellListener = listener;
   }

   public void elevatorDeparted( ElevatorMoveEvent moveEvent ) {}

   public void elevatorArrived( ElevatorMoveEvent moveEvent )
   {
      ringBell( moveEvent.getLocation() );
   }
}
