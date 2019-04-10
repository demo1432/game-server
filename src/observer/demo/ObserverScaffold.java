package observer.demo;

import observer.Subject;

public class ObserverScaffold {

	public void done() {
		ExObserver ob = new ExObserver();
		Subject.getInstance().addObserver(1001, ob);
		
		ExObserver ob2 = new ExObserver();
		Subject.getInstance().addObserver(1002, ob2);
		
		ExObserver ob3 = new ExObserver();
		Subject.getInstance().addObserver(1003, ob3);
		
		Subject.getInstance().notify(1001, "test");
		Subject.getInstance().removeObserver(1001, ob);
		Subject.getInstance().notify(1001, "test");
	}

}
