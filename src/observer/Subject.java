package observer;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Subject {

	public static Subject instance = new Subject();

	public ConcurrentHashMap<Integer, Vector<IObserver>> observersMap = new ConcurrentHashMap<Integer, Vector<IObserver>>();

	public static Subject getInstance() {
		return instance;
	}

	public void addObserver(int code, IObserver ob) {
		Vector<IObserver> obs = observersMap.get(code);
		if (obs == null) {
			obs = new Vector<IObserver>();
			obs.add(ob);
			observersMap.put(code, obs);
		}

		if (!obs.contains(ob)) {
			obs.add(ob);
		}
	}

	public void removeObserver(int code, IObserver ob) {
		Vector<IObserver> obs = observersMap.get(code);
		if (obs == null) {
			return;
		}

		if (obs.contains(ob)) {
			obs.remove(ob);
		}
	}

	public void notify(int code, Object data) {
		Vector<IObserver> obs = observersMap.get(code);
		if (obs == null) {
			return;
		}
		for (IObserver ob : obs) {
			ob.onNotify(ob, code, data);
		}
	}

}
