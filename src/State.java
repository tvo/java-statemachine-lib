import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
	private String name;
	private List<Command> actions = new ArrayList<Command>();
	private Map<String, Transition> transitions = new HashMap<String, Transition>();

	public State(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addTransition(Event event, State targetState) {
		assert null != targetState;
		transitions.put(event.getCode(), new Transition(this, event, targetState));
	}

	Collection<State> getAllTargets() {
		List<State> result = new ArrayList<State>();
		for (Transition t : transitions.values())
			result.add(t.getTarget());
		return result;
	}

	public boolean hasTransition(String eventCode) {
		return transitions.containsKey(eventCode);
	}

	public Collection<String> getTransitions() {
		return transitions.keySet();
	}

	public State targetState(String eventCode) {
		return transitions.get(eventCode).getTarget();
	}

	public void addAction(Command action) {
		actions.add(action);
	}

	public void executeActions(CommandChannel commandsChannel) {
		for (Command c : actions)
			commandsChannel.send(c.getCode());
	}
}
