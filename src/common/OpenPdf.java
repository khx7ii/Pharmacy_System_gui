package common;

import deo.InventoryUtils;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Heba Ahmed
 */
public class OpenPdf {
    public static void OpenById(String id){
     try{
    if((new File(InventoryUtils.billPath + id + ".pdf")).exists()){
    Process p =Runtime
                .getRuntime()
                .exec("rundll32 url.dll,FileProtocolHandler "+InventoryUtils.billPath+""+id+".pdf");
    }
    else{
    JOptionPane.showMessageDialog(null, "file is not exists");
    }

    }catch(Exception e){
         JOptionPane.showMessageDialog(null, e);
                    
}
    }
    
   
    
}
