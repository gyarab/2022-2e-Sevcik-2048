public class Tools {
    public double decode(int x, String xory){
        if(xory.equals("x")) {
            if (x == 0) {
                return 37.5;
            } else if (x == 1) {
                return 112.5;
            } else if (x == 2) {
                return 187.5;
            } else if (x == 3) {
                return 262.5;
            }
        } else {
            if (x == 0) {
                return 37.5;
            } else if (x == 1) {
                return 112.5;
            } else if (x == 2) {
                return 187.5;
            } else if (x == 3) {
                return 262.5;
            }
        }
        return 0;
    }
}
