public class Button implements ElevatorMoveListener {

   private boolean pressed = false;
   private ButtonListener buttonListener = null;
   
   public void setButtonListener( ButtonListener listener )
   {
      buttonListener = listener;
   }

   public void resetButton( Location location )
   {
      pressed = false;
      buttonListener.buttonReset( new ButtonEvent( this, location ) );
   }

   public void pressButton( Location location )
   {
      pressed = true;
      buttonListener.buttonPressed( new ButtonEvent( this, location ) );
   }

   public boolean isButtonPressed()
   {
      return pressed;
   }

   public void elevatorDeparted( ElevatorMoveEvent moveEvent ) {}

   public void elevatorArrived( ElevatorMoveEvent moveEvent )
   {
      resetButton( moveEvent.getLocation() );
   }
} 
