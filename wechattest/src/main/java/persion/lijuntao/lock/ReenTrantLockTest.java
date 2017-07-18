package persion.lijuntao.lock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockTest {
	public ReentrantLock lock = new ReentrantLock();
	public Condition condi = lock.newCondition();
	ThreadLocal<byte[]> threa = new ThreadLocal<byte[]>();
	HashMap<String, byte[]> hash = new HashMap<String, byte[]>();
	HashMap<Thread, byte[]> hash2 = new HashMap<Thread, byte[]>();
	int i = 0;
	public void produce(){
		
		//lock.lock();
		try {
			//threa.set(new byte[1024*1024*50]);
			i++;
			//hash2.put(Thread.currentThread(), new byte[1024*1024*50]);
			MyThread thread = (MyThread)Thread.currentThread();
			thread.hash.put(i + "", new byte[1024*1024*50]);
			//condi.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ReenTrantLockTest lockTest = new ReenTrantLockTest();
		new Thread(lockTest.new Runnable3(lockTest)).start();
		for(int i=0;i<1000000000;i++){
			Thread thread = lockTest.new MyThread(lockTest.new Runnable2(lockTest));
			try {
				//Thread.sleep(10);
				thread.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class Runnable2 implements Runnable{
		ReenTrantLockTest lockTest;
		
		public Runnable2(ReenTrantLockTest lockTest){this.lockTest = lockTest;}
		
		@Override
		public void run() {
			lockTest.produce();
		}
		
	}
	
	public class Runnable3 implements Runnable{
		ReenTrantLockTest lockTest;
		
		public Runnable3(ReenTrantLockTest lockTest){this.lockTest = lockTest;}
		
		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(1000);
					System.out.println(lockTest.i + "   ---   " + Arrays.toString(lockTest.hash.keySet().toArray()));
					if(lockTest.i >90000000){
						break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public class MyThread extends Thread{
		public MyThread(Runnable r){
			super(r);
		}
		
		public HashMap<String, byte[]> hash = new HashMap<String, byte[]>();
	}
}
