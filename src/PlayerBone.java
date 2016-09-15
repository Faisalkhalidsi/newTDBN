import org.jetbrains.annotations.NotNull;
import processing.core.PApplet;

import java.io.BufferedReader;

/**
 * Created by sourcemycode on 15/09/16.
 */
class PlayerBone {
    String name;
    int type =0;
    int ind;
    int root;
    PlayerBone child;
    PlayerBone child2;
    PlayerBone child3;

    float axisX;
    float axisY;
    float axisZ;
    float dirX;
    float dirY;
    float dirZ;

    int dofX;
    int dofY;
    int dofZ;
    float len;

    //read skeleton for root only
    PlayerBone(String name,
               int _ind, float _len,
               float _dirX, float _dirY, float _dirZ,
               float _axisX, float _axisY, float _asixZ,
               int _dofX, int _dofY, int _dofZ){
        this.name = name;
        ind =_ind;
        len = _len;
        dirX = _dirX;
        dirY = _dirY;
        dirZ = _dirZ;
        axisX = PApplet.radians(_axisX);
        axisY = PApplet.radians(_axisY);
        axisZ = PApplet.radians(_asixZ);
        dofX = _dofX;
        dofY = _dofY;
        dofZ = _dofZ;
    }

    //read parameter
    PlayerBone(@NotNull BufferedReader reader) throws Exception{
        dofX = 0;
        dofY = 0;
        dofZ = 0;

        String[] words;
        do {
            words = reader.readLine().trim().split("\\s+");
            switch (words[0]){
                case "id":
                    ind = Integer.parseInt(words[1]);
                    break;
                case "name":
                    name = words[1];
                    break;
                case "direction":
                    dirX = Float.parseFloat(words[1]);
                    dirY = Float.parseFloat(words[2]);
                    dirZ = Float.parseFloat(words[3]);
                    break;
                case "length":
                    len = (float) (Float.parseFloat(words[1])*10.0);
                    break;
                case "axis":
                    axisX = PApplet.radians(Float.parseFloat(words[1]));
                    axisY = PApplet.radians(Float.parseFloat(words[2]));
                    axisZ = PApplet.radians(Float.parseFloat(words[3]));
                    if (!words[4].equals("XYZ"))
                        throw new Exception("Error in parsing axis from asf file");
                    break;
                case "dof":
                    for (int i =1;i < words.length;i++){
                        switch (words[i]){
                            case "rx":
                                dofX=1;
                                break;
                            case "ry":
                                dofY =1;
                                break;
                            case "rz":
                                dofZ =1;
                                break;
                        }
                    }
                    break;
            }

        } while (!words[0].equals("end"));
    }
}
