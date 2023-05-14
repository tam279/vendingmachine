package tam279.vendingmachine.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tam279.vendingmachine.controller.VendingMachineController;

/**
 * This is the main application entry point for the Vending Machine.
 */
public class App {

    /**
     * The main method which starts the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);

        try {
            controller.run();
        } catch (Exception e) {
            System.err.println("Application encountered an error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
