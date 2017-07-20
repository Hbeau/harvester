package testPlugin.model.filesystem;

import com.google.gson.Gson;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import testPlugin.Constant;
import testPlugin.model.bean.BlockComportementBean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class BlockComportementDAO {

    private String readFile(){
        try {
            BufferedReader br = new BufferedReader(new FileReader(Constant.CONFIG_FILE));

            StringBuilder sb = new StringBuilder();
            while (br.readLine() != null){
                sb.append(br.readLine());
            }
            return sb.toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    private List<BlockComportementBean> getComportement(){
        Gson gson = new Gson();
        //gson.fromJson();
    }
}
