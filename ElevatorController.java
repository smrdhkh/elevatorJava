import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ElevatorController extends JPanel implements ElevatorConstants {
   private JButton firstControllerButton;
   private JButton secondControllerButton;
   private ElevatorSimulation elevatorSimulation;

   public ElevatorController( ElevatorSimulation simulation )
   {
      elevatorSimulation = simulation;
      setBackground( Color.WHITE );

      firstControllerButton = new JButton( "First Floor" );
      add( firstControllerButton );

      secondControllerButton = new JButton( "Second Floor" );
      add( secondControllerButton );

      firstControllerButton.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent event )
            {
               elevatorSimulation.addPerson( FIRST_FLOOR_NAME );
               firstControllerButton.setEnabled( false );
            }
         }
      );

      secondControllerButton.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent event )
            {
               elevatorSimulation.addPerson( SECOND_FLOOR_NAME );
               secondControllerButton.setEnabled( false );
            }
         }
      );
      
      elevatorSimulation.addPersonMoveListener(
         new PersonMoveListener()
         {
            public void personEntered( PersonMoveEvent event )
            {
               String location = event.getLocation().getLocationName();
               if ( location.equals( FIRST_FLOOR_NAME ) )
                  firstControllerButton.setEnabled( true );
     
               else
                  secondControllerButton.setEnabled( true );
            }

            public void personCreated( PersonMoveEvent event ) {}

            public void personArrived( PersonMoveEvent event ) {}

            public void personExited( PersonMoveEvent event ) {}
 
            public void personDeparted( PersonMoveEvent event ) {}

            public void personPressedButton( PersonMoveEvent event ) {}
         }
      );
     
   }
}
