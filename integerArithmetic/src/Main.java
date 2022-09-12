public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String a = "34";
        String b = "58";
        System.out.println("a = " + a + ", b = " + b);
        System.out.print("a - b = ");
        MyBigInteger T = new MyBigInteger();
        String ab = T.IntegerSubtraction(a, b);
        System.out.println(ab);

        a = "92964789756415219848485649846516589416519891865";
        b = "91456485464321644645878932121214654584651486484";

        System.out.println("a = " + a + ", b = " + b);
        System.out.print("a + b = ");
        ab = T.integerAddition(a, b);
        System.out.println(ab);
    }
}