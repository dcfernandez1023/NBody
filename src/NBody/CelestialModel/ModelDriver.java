package NBody.CelestialModel;
/*
    This class is just driver code that I used to test out the model classes.  If you want to run the main application,
    then go to NBody.main.NBodyRunner
*/
public class ModelDriver {
    public static void main (String[] args) throws Exception {
        ContainerFactory<String> celestialFactory = new ContainerFactory<>();
        Container<String> arr = celestialFactory.getContainer("arraylist");
        Container<String> ll = celestialFactory.getContainer("linkedlist");
        for(int i = 0; i < 6; i++) {
            //Celestial c = new Celestial("test.txt",10,10,10,10,10,10, 10);
            String c = "hi";
            arr.add(c);
            ll.add(c);
        }
        System.out.println(arr.toString());
        System.out.println(ll.toString());
    }
}
