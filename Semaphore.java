

//Dominik Albiniak
import java.util.concurrent.Semaphore;

public class Piaskownica implements PiaskownicaI{
      Semaphore Piaskownica = new Semaphore(1 ,true);
      Semaphore redS = new Semaphore(1, true);
      Semaphore blueS = new Semaphore(1, true);
      Semaphore greenS = new Semaphore(1, true);
      int red = 0;
      int blue = 0;
      int green = 0;
      @Override
      public void greenEnter() {
          greenS.acquireUninterruptibly();
          green++;
          if (green == 1)
            Piaskownica.acquireUninterruptibly();
          greenS.release();
      }
	 @Override
      public void greenExit() {
          greenS.acquireUninterruptibly();
          green--;
          if (green == 0)
            Piaskownica.release();
          greenS.release();
      }
      @Override
      public void redEnter() {
          redS.acquireUninterruptibly();
          red++;
          if (red == 1)
            Piaskownica.acquireUninterruptibly();
          redS.release();
      }
      @Override
      public void redExit() {
          redS.acquireUninterruptibly();
          red--;
          if (red == 0)
            Piaskownica.release();
          redS.release();
      }
      @Override
      public void blueEnter() {
          blueS.acquireUninterruptibly();
          blue++;
          if (blue == 1)
            Piaskownica.acquireUninterruptibly();
          blueS.release();
      }
      @Override
      public void blueExit() {
          blueS.acquireUninterruptibly();
          blue--;
          if (blue == 0)
            Piaskownica.release();
          blueS.release();
      }
}