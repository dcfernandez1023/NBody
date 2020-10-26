package NBody.CelestialModel;

import java.util.HashMap;

public class Celestial {
    private HashMap<String, Object> data;
    private static final String[] fields = {
            "name",
            "mass",
            "initCoordX",
            "initCoordY",
            "initDirectionX",
            "initDirectionY",
            "size"
    };
    /*
        * PARAMS: none
        * DESCRIPTION: constructs a Celestial object and populates its HashMap data member with
                       the proper keys with each key having an initial value of null
        * RETURN: none
    */
    public Celestial() {
        this.data = new HashMap<String, Object>();
        initializeData();
    }
    /*
        * PARAMS: Object[] data - data for this Celestial object
        * DESCRIPTION: loops through the data parameter and sets the keys of the HashMap data member accoriding to the sequential
                       order of the parameter
        * RETURN: none
    */
    public Celestial(Object[] data) throws Exception {
        if(data == null) {
            throw new Exception("Invalid Celestial data: null");
        }
        this.data = new HashMap<String, Object>();
        for(int i = 0; i < fields.length; i++) {
            this.data.put(fields[i], data[i]);
        }
    }
    /*
        * PARAMS: Container<Object> container  - data for this Celestial object
        * DESCRIPTION: loops through the data parameter and sets the keys of the HashMap data member accoriding to the sequential
                       order of the parameter
        * RETURN: none
    */
    public Celestial(Container<Object> container) throws Exception {
        if(container == null) {
            throw new Exception("Invalid Celestial data: null");
        }
        this.data = new HashMap<String, Object>();
        for(int i = 0; i < fields.length; i++) {
            this.data.put(fields[i], container.get(i));
        }
    }
    /*
        * PARAMS:
            * String key - the key of the HashMap data member whose value is to be updated
            * Object value - the value of the HashMap data member to be assigned to the given key
        * DESCRIPTION: updates the HashMap data member with a new value, as long as the key is valid (exists in fields)
        * RETURN: none
    */
    public void updateValue(String key, Object value) throws Exception {
        if(!this.isValidKey(key)) {
            throw new Exception("Invalid Celestial key");
        }
        this.data.put(key, value);
    }
    /*
        * PARAMS:
            * String key - the key of the specified value to be returned
        * DESCRIPTION - returns the value of the given key
        * RETURN: Object
    */
    public Object getValue(String key) {
        if(!this.isValidKey(key)) {
            return null;
        }
        return this.data.get(key);
    }
    @Override
    public String toString() {
        String s = "{";
        for(int i = 0; i < fields.length; i++) {
            String key = fields[i];
            Object value = this.data.get(fields[i]);
            if(i == fields.length-1) {
                s = s + key + ": " + value + "}";
            }
            else {
                s = s + key + ": " + value + ", ";
            }
        }
        return s;
    }

    /*
        * PARAMS: none
        * DESCRIPTION: populates HashMap data member with the proper keys, with each key having
                       an initial value of null
        * RETURN : none
    */
    private void initializeData() {
        for(String field: fields) {
            this.data.put(field, null);
        }
    }
    /*
        * PARAMS: String key - key to be validated by checking if it exists in the specified fields
        * DESCRIPTION: determines if the key exists in the specified fields
        * RETURN : boolean
    */
    private boolean isValidKey(String key) {
        for(String field: fields) {
            if(field.equals(key)) {
                return true;
            }
        }
        return false;
    }
}
