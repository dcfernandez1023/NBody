package NBody.CelestialModel;

/*
    This Class is a factory for instantiating objects that implement the Container Interface that is defined
    in NBody.CelestialModel. Currently, it supports instantiating the ContainerArrayList object and the ContainerLinkedList
    object, which are both defined in NBody.CelestialModel
*/
public class ContainerFactory<E> {
    /*
        * PARAMS: String structureType - the type of data structure to be instantiated
        * DESCRIPTION: instantiates and returns an implementation of the Container interface based on the
                       value of the String parameter; throws exception if String parameter does not
                       represent a data structure that this factory class supports
        * RETURN Container<E> - an instantiated object that implements the Container interface
    */
    public Container<E> getContainer(String structureType) throws Exception {
        if(structureType.equalsIgnoreCase("ARRAYLIST")) {
            return new ContainerArrayList<E>(0);
        }
        if(structureType.equalsIgnoreCase("LINKEDLIST")) {
            return new ContainerLinkedList<E>();
        }
        throw new Exception("Structure " + "'" + structureType + "' " + "is not a valid Container type");
    }
    /*
        * PARAMS:
            * String structureType - the type of data structure to be instantiated
            * int capacity - the initial length of the data structure
        * DESCRIPTION: instantiates and returns an implementation of Container interface based on the
                       value of the String parameter; throws exception if String parameter does not
                       represent a data structure that this factory class supports
        * RETURN Container<E> - an instantiated object that implements the Container interface
    */
    public Container<E> getContainer(String structureType, int capacity) throws Exception {
        if(structureType.equalsIgnoreCase("ARRAYLIST")) {
            return new ContainerArrayList<E>(capacity);
        }
        if(structureType.equalsIgnoreCase("LINKEDLIST")) {
            return new ContainerLinkedList<E>();
        }
        throw new Exception("Structure " + "'" + structureType + "' " + "is not a valid Container type");
    }
}
