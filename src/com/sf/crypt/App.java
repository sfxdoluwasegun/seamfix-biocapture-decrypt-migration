/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sf.crypt;

import com.sf.biocapture.dynamic.entity.dynamicdata.DynamicData;
import com.sf.crypt.tool.CryptTool;
import com.sf.dsl.CustomHibernateService;
import com.sf.enums.FieldsConstants;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author DAWUZI
 */
public class App {
    
    private static final Logger log = Logger.getLogger(App.class);
    
    public static void main (String[] args){

        int size = 100 ;
        int currentIndex = 0;
        
        String query = "select d from DynamicData d order by id";
        
        for(int x=0;;x++){
            List<DynamicData> listByHql = (List<DynamicData>) CustomHibernateService.getListByHql(query, null, currentIndex, size);
            cleanUpDynamicDataList(listByHql);
            int count = listByHql.size();
            System.out.println("x = "+x+", time : "+new Date()+", count = "+count);
            if(count < size){
                break;
            }
            currentIndex += size;
        }

    }

    private static void cleanUpDynamicDataList(List<DynamicData> listByHql) {
        for(DynamicData dd : listByHql){
            boolean cleanUpDynamicData = cleanUpDynamicData(dd);
            if(!cleanUpDynamicData){
                System.out.println("cleanUpDynamicData "+cleanUpDynamicData+" for dd with id = "+dd.getId());
            }
        }

    }

    private static boolean cleanUpDynamicData(DynamicData dd) {
        try {
            
            for(FieldsConstants fc : FieldsConstants.values()){
            
                if(fc.isBasicData() || fc.getFieldName().startsWith("dda")){
                    continue;
                }
                
                decryptField(dd,fc.getFieldName());
            }
            
            CustomHibernateService.updateRecord(dd);
            
            return true;
        } catch (Exception e) {
            log.error("Exception", e);
            return false;
        }

    }

    private static void decryptField(DynamicData dd, String fieldName) {
        try {
            Method getMethod = new PropertyDescriptor(fieldName, DynamicData.class).getReadMethod();
            Object fieldValue = getMethod.invoke(dd);
            if(fieldValue == null){ 
                return;
            }
            if(!fieldValue.getClass().equals(String.class)){
                return;
            }
            
            String stringField = (String) fieldValue;
            
            String plain = CryptTool.getInstance().decrypt(stringField);
            
            Method setMethod = new PropertyDescriptor(fieldName, DynamicData.class).getWriteMethod();
            
            setMethod.invoke(dd, plain);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
