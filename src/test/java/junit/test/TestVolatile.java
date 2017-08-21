package junit.test;

import java.util.concurrent.atomic.AtomicInteger;

public class TestVolatile {
	//public AtomicInteger inc = new AtomicInteger();
	public volatile int inc = 0;
	public void increase(){
		//inc.getAndAdd(1);
		inc++;
	}
	
	public static void main(String[] args) {
		final TestVolatile testVolatile = new TestVolatile();
		for(int i=0;i<10;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					for(int i=0;i<1000;i++) testVolatile.increase();
				}
				
			}).start();
		}
		while(Thread.activeCount()>1)
			Thread.yield();
		System.out.println(testVolatile.inc);
	}
}
