package com.nd.tepia.entities;

// import java.util.ArrayList;
// import java.util.List;

public class TepiaProperties {

    // private List<String> keys = new ArrayList<>();
    // private List<String> values = new ArrayList<>();

    // public TepiaProperties(){}

    // public TepiaProperties(String[] from){
    //     addAllFrom(from);
    // }

    // public boolean setProperty(String key, String value){
    //     if(!containsKey(key)){
    //         keys.add(key);
    //         values.add(value);
    //         return true;
    //     }else {
    //         for (int i = 0; i < keys.size(); i++) {
    //             if(keys.get(i) == key){
    //                 values.set(i, value);
    //                 return true;
    //             }
    //         }
    //         return false;
    //     }
    // }

    // public String getProperty(String key){
    //     int pos = 0;
    //     for (int i = 0; i < keys.size(); i++) {
    //         if(keys.get(i) == key){
    //             pos = i;
    //             break;
    //         }else if(i == keys.size()-1) throw new RuntimeException("Inexistent key");
    //     }
    //     return values.get(pos);
    // }

    // public boolean containsKey(String key){
    //     for (int i = 0; i < keys.size(); i++) {
    //         if(keys.get(i) == key){
    //             return true;
    //         }else if(i == keys.size()-1){
    //             return false;
    //         }else
    //             continue;
    //     }
    //     return false;
    // }
    
    // public boolean containsValue(String value){
    //     for (int i = 0; i < values.size(); i++) {
    //         if(values.get(i) == value){
    //             return true;
    //         }else if(i == values.size() - 1){
    //             return false;
    //         }else
    //             continue;
    //     }
    //     return false;
    // }

    // public boolean contains(String arg){
    //     return (containsKey(arg) || containsValue(arg))? true : false;
    // }

    // public void addAllFrom(String[] data){
    //     boolean isInteger = (((data.length / 2) % 2 ) == 0) ? true : false;
	// 	if (isInteger)
	// 		for (int i = 0; i < data.length; i+=2) {
    //             keys.add(data[i]);
    //             values.add(data[i+1]);
	// 		}
	// 	else throw new RuntimeException("'Data' Array is corrupted.");
    // }

    // public String[] toStringArray() {
    //     String[] list = new String[keys.size()*2];
    //     for (int i = 0; i < keys.size()*2; i+=2) {
    //         list[i] = keys.get(i/2);
    //         list[i+1] = values.get(i/2);
    //     }
    //     return list;
    // }

    // @Override
    // public String toString() {
    //     String[] ka  = new String[keys.size()];
    //     keys.toArray(ka);
    //     StringBuilder karBuilder = new StringBuilder("\n");
    //     for (Object obj : ka) karBuilder.append("\t\t\"" + obj.toString() + "\"\n");

    //     String[] va = new String[values.size()];
    //     values.toArray(va);
    //     StringBuilder varBuilder = new StringBuilder("\n");
    //     for (Object obj : va) varBuilder.append("\t\t\"" + obj.toString() + "\"\n");

    //     return "TepiaProperties [\n\t\"keys\":{\n" + karBuilder.toString()  + "\t},\n\t\"values\":{\n" + varBuilder.toString() + "\t}\n]";
    // }

}
