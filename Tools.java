import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Tools{
    public double decode(int x, String xory){
        if(xory.equals("x")) {
            if (x == 0) {
                return 0;
            } else if (x == 1) {
                return 75;
            } else if (x == 2) {
                return 150;
            } else if (x == 3) {
                return 225;
            }
        } else {
            if (x == 0) {
                return 0;
            } else if (x == 1) {
                return 75;
            } else if (x == 2) {
                return 150;
            } else if (x == 3) {
                return 220;
            }
        }
        return 0;
    }
    public void add(Button b, AnchorPane u){
        u.getChildren().add(b);
    }

}
