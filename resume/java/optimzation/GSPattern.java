package resume.java.optimzation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Guarded Suspension Pattern with return results for multiple threads
 * 
 */
public class GSPattern {

	protected static GSPattern gs = new GSPattern();
	public static void main(String[] args) {
		
		RequestQueue requestQueue = gs.new RequestQueue();
		for (int i = 0; i < 5; i++)
			gs.new ServerThread(requestQueue, "ServerThread" + i).start();
		for (int i = 0; i < 10; i++)
			gs.new ClientThread(requestQueue, "ClientThread" + i).start();
	}

	public class Request {
		private String name;
		private Data response;

		public synchronized Data getResponse() {
			return response;
		}

		public synchronized void setResponse(Data response) {
			this.response = response;
		}

		public Request(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public String toString() {
			return "[ Request " + name + " ]";
		}
	}

	public interface Data {
		public String getResult();
	}

	public class ServerThread extends Thread {
		private RequestQueue requestQueue;

		public ServerThread(RequestQueue requestQueue, String name) {
			super(name);
			this.requestQueue = requestQueue;
		}

		public void run() {
			while (true) {
				final Request request = requestQueue.getRequest();
				final FutureData future = (FutureData) request.getResponse();
				// RealData的创建比较耗时
				RealData realdata = new RealData(request.getName());
				// 处理完成后，通知客户进程
				future.setRealData(realdata);
				System.out.println(Thread.currentThread().getName() + " handles  " + request);
			}
		}
	}
	
	public class RealData implements Data {
	    protected final String result;
	    public RealData(String para) {
	    	//RealData的构造可能很慢，需要用户等待很久
	    	StringBuffer sb=new StringBuffer();
	        for (int i = 0; i < 10; i++) {
	        	sb.append(para);
	            try {
	                Thread.sleep(100);
	            } catch (InterruptedException e) {
	            }
	        }
	        result=sb.toString();
	    }
	    public String getResult() {
	        return result;
	    }
	}	

	public class RequestQueue {
		private LinkedList queue = new LinkedList();

		public synchronized Request getRequest() {
			while (queue.size() == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			return (Request) queue.remove();
		}

		public synchronized void addRequest(Request request) {
			queue.add(request);
			notifyAll();
		}
	}

	public class ClientThread extends Thread {
		private RequestQueue requestQueue;
		private List<Request> myRequest = new ArrayList<Request>();

		public ClientThread(RequestQueue requestQueue, String name) {
			super(name);
			this.requestQueue = requestQueue;
		}

		public void run() {
			// 先提出请求
			for (int i = 0; i < 10; i++) {
				Request request = new Request("RequestID:" + i + " Thread_Name:" + Thread.currentThread().getName());
				System.out.println(Thread.currentThread().getName() + " requests " + request);
				request.setResponse(new FutureData());
				requestQueue.addRequest(request);
				myRequest.add(request);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}

			// 取得服务端的返回值
			for (Request r : myRequest) {
				System.out.println("ClientThread Name is:" + Thread.currentThread().getName() + " Reponse is:"
						+ r.getResponse().getResult());
			}
		}
	}

	public class FutureData implements Data {
		protected RealData realdata = null;
		protected boolean isReady = false;

		public synchronized void setRealData(RealData realdata) {
			if (isReady) {
				return;
			}
			this.realdata = realdata;
			isReady = true;
			notifyAll();
		}

		public synchronized String getResult() {
			while (!isReady) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			return realdata.result;
		}
	}

}
