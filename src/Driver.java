import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {
	public static void run(StateMachine machine) {
		Controller controller = new Controller(machine, new CommandChannel() {
			public void send(String code) {
				System.out.println("Executing action : " + code);
			}
		});

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String eventCode;
			try {
				eventCode = reader.readLine();
			}
			catch (IOException e) {
				// exit on IOException
				e.printStackTrace();
				break;
			}

			// exit on EOF
			if (eventCode == null)
				break;

			if (!controller.handle(eventCode))
				System.out.println("Invalid event code, available are: " + controller.getTransitions());
		}
	}
}
