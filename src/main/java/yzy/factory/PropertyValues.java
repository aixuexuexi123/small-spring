package yzy.factory;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

import java.util.ArrayList;

public class PropertyValues {
 private  final ArrayList<PropertyValue>  propertyValueList= new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        this.propertyValueList.add(pv);
    }
    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}
