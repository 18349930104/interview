import java.util.Random;

/**
 * @Author: tangx
 * @Date: 2020/8/9 14:48
 * @Description: PACKAGE_NAME
 */
public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i <100 ; i++) {
            System.out.println(random.nextInt(500));
        }

    }
}
