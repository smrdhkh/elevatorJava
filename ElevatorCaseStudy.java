import java.awt.*;
import javax.swing.*;

public class ElevatorCaseStudy extends JFrame {

   private ElevatorSimulation model;
   private ElevatorView view;
   private ElevatorController controller;

   public ElevatorCaseStudy()
   {
      super( "Elevator Simulation" );

      model = new ElevatorSimulation();
      view = new ElevatorView();
      controller = new ElevatorController( model );

      model.setElevatorSimulationListener( view );

      getContentPane().add( view, BorderLayout.CENTER );
      getContentPane().add( controller, BorderLayout.SOUTH );
   }

   public static void main( String args[] )
   {
      ElevatorCaseStudy simulation = new ElevatorCaseStudy();
      simulation.setDefaultCloseOperation( EXIT_ON_CLOSE );
      
      // pack no idea what it is
      simulation.pack();

      simulation.setVisible( true );
   }

}
