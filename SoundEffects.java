import java.applet.*;

public class SoundEffects {
   private String prefix = "";
   public SoundEffects() {}

   public AudioClip getAudioClip( String soundFile )
   {
      try {
         return Applet.newAudioClip( getClass().getResource( prefix + soundFile ) );
      }
      catch ( NullPointerException nullPointerException )
      {
         return null;
      }
   }

   public void setPathPrefix( String string )
   {
      prefix = string;
   }
}
