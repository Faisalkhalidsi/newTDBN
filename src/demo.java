import processing.core.PApplet;

import java.io.File;
import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sourcemycode on 15/09/16.
 */
public class demo extends PApplet {
    private static boolean done = false;
    private static final String fileName = demo.class.getName();
    private final Logger logger = Logger.getLogger(fileName);
    TDBN net;
    PlayerSkel skel;

    public void setup(){
        //clean log directory
        if (!done) {
            File dir = new File(LoggerSetup.LOG_DIR);
            for (File file : dir.listFiles()) file.delete();
            done = true;
        }

        LoggerSetup.setFileHandler(logger, fileName);
        setCustomFormatter();
        LoggerSetup.setHandlerLevel(logger, Level.FINE);

        boolean isNetNull = (net == null);
        logger.fine("Start the program!... is net null? : "+isNetNull);

        URL asfBase =null;

        try {
            asfBase = this.getClass().getResource("data/137/137.asf");
            logger.fine("File Skeleton: "+asfBase.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            assert asfBase != null;
            skel = new PlayerSkel(asfBase.getFile());
        }catch (Exception e){
            e.printStackTrace();
            exit();
        }

        size(900, 700, P3D);
        frameRate(30);
        sphereDetail(5);
        noSmooth();
    }

    public void setCustomFormatter(){
        Handler[] handlers = Logger.getLogger("").getHandlers();
        for (Handler handler : handlers){
            handler.setFormatter(new CustomFormatter());;
        }
    }



}
