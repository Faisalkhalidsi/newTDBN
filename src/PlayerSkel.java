import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Created by sourcemycode on 15/09/16.
 */
class PlayerSkel {
    HashMap<String, PlayerBone> bones;

    PlayerSkel(@NotNull String asfPath) throws Exception{
        bones = new HashMap<>();
        BufferedReader asf = new BufferedReader(new FileReader(asfPath));
        parseSkel(asf);
    }

    void parseSkel(@NotNull BufferedReader reader) throws Exception{
        //only for root
        bones.put("root", new PlayerBone("root",0,0,0,0,0,0,0,0,1,1,1));
        bones.get("root").root=1;

        //read parameters for other bones
        while (!reader.readLine().equals(":bonedata")){
        }
        while (!reader.readLine().equals(":hierarchy")){
            PlayerBone bone;
            bone = new PlayerBone(reader);
            bones.put(bone.name,bone);
        }

        //read skeleton hierarchical
        String[] words;
        while (true){
            words = reader.readLine().trim().split(" ");
            if (words[0].equals("begin")) continue;
            if (words[0].equals("end")) break;
            PlayerBone bone = bones.get(words[0]);
            bone.child = bones.get(words[0]);
            if (words.length>2)
                bone.child2 = bones.get(words[2]);
            if (words.length>3)
                bone.child3 = bones.get(words[3]);
        }
    }
}
