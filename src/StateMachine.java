import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StateMachine {
	private State start;

	public StateMachine(State start) {
		this.start = start;
	}

	public State getStart() {
		return start;
	}

	public Collection<State> getStates() {
		List<State> result = new ArrayList<State>();
		collectStates(result, getStart());
		return result;
	}

	private void collectStates(Collection<State> result, State s) {
		if (result.contains(s))
			return;
		result.add(s);
		for (State next : s.getAllTargets())
			collectStates(result, next);
	}

	public void addResetEvents(Event... events) {
		for (State s : getStates())
			for (Event e : events)
				s.addTransition(e, start);
	}

}
