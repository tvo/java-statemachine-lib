import java.util.Collection;

public class Controller {
	private State currentState;
	private CommandChannel commandsChannel;

	public Controller(StateMachine machine, CommandChannel commandsChannel) {
		this.commandsChannel = commandsChannel;
		transitionTo(machine.getStart());
	}

	public boolean handle(String eventCode) {
		if (currentState.hasTransition(eventCode)) {
			transitionTo(currentState.targetState(eventCode));
			return true;
		}
		// ignore unknown events
		return false;
	}

	public Collection<String> getTransitions() {
		return currentState.getTransitions();
	}

	private void transitionTo(State target) {
		currentState = target;
		currentState.executeActions(commandsChannel);
	}
}
