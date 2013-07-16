package nars.storage;

/** a {@link BagObserver} that does nothing (null design pattern) */
public class NullBagObserver<Type> implements BagObserver{
	@Override
	public void setTitle(String title) {}
	@Override
	public void setBag(Bag<?> concepts) {}
	@Override
	public void post(String str) {}
	@Override
	public void refresh(String string) {}
	@Override
	public void stop() {}    	
}