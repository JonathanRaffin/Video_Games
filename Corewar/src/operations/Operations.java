package operations;

public class Operations {
    
    public static int getPos (int currentPos, int shift, int divisor) {
        int res = (currentPos+shift)%divisor;
        if (res < 0) res+=divisor;
        return res;
    }
}
