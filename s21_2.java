import java.io.*;

import java.lang.*;

class Shared
{
int a; 
boolean valueChanged = false; 
synchronized int get_data() throws Exception
{
if (!valueChanged)
wait();
System.out.println("Read :" + a);
 valueChanged =false;

notify();

return a;

}
synchronized void putdata(int n) throws Exception
{
if (valueChanged)

wait();
 this.a=n;

valueChanged = true;

System.out.println("Written : " + a);

notify();
}

}//Shared End

class Producer implements Runnable

{

Shared ob;

Producer(Shared ob)

{

this.ob = ob;

new Thread(this, "Producer").start();

}

public void run() 
{

int j = 0;
try
{
 while(true)
{
ob.putdata(j++);

}
}
catch(Exception e)
{
	System.out.println(e);
}

}

}//Producer End

class Consumer implements Runnable
{
Shared ob;

Consumer (Shared ob)
{
this.ob= ob;

 new Thread(this, "Producer").start();
}

public void run()
{
try
{
while(true)
{
ob.get_data();
}
}
catch(Exception e)
{
System.out.println(e);
}
}
}//Consumer End

class DemoThreads
{

public static void main(String args[]) throws Exception
{
Shared ob= new Shared();

new Producer (ob); 
new Consumer (ob);

System.out.println("Press Cntl+c to stop");

}
}
