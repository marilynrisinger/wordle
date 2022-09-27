public class ArrayTest {
    public static void foo(int[] a, int[] b) {

        // part 2: what do things look like when we get here?

        for (int i = 0; i < a.length; i++) {
             a[i] *= 2;
        }

        int[] c = {2, 4, 6, 8};
        b = c;

        // part 3: what do things look like when we get here?
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int[] b = a;
        int[] c = new int[b.length];

        for (int i = 0; i < b.length; i++) {
             c[i] = b[i];
        }

        // part 1: what do things look like when we get here?

        foo(a, c);

        // part 4: what do things look like when we get here?

        System.out.println(a[2] + " " + b[2] + " " + c[2]);
    }
}
