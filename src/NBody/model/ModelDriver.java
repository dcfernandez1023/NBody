package NBody.model;

public class ModelDriver {
    public static void main (String[] args) throws Exception {
        ContainerFactory<Celestial> celestialFactory = new ContainerFactory<>();
        Container<Celestial> arr = celestialFactory.getContainer("arraylist");
        Container<Celestial> ll = celestialFactory.getContainer("linkedlist");
        for(int i = 0; i < 6; i++) {
            Celestial c = new Celestial();
            arr.add(c);
            ll.add(c);
        }
        System.out.println(arr.toString());
        System.out.println(ll.toString());
    }
}
