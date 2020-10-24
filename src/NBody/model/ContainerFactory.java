package NBody.model;

public class ContainerFactory<E> {
    public Container<E> getContainer(String structureType) throws Exception {
        if(structureType.equalsIgnoreCase("ARRAYLIST")) {
            return new ContainerArrayList<E>(0);
        }
        if(structureType.equalsIgnoreCase("LINKEDLIST")) {
            return new ContainerLinkedList<E>();
        }
        throw new Exception("Structure " + "'" + structureType + "' " + "is not a valid Container type");
    }
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
