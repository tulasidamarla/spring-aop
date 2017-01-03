When Spring AOP is used, spring injects proxies into other spring beans. proxies are like original objects with same methods.

There are two ways proxies are implemented. 
Dynamic proxies using JDK feature if the class implements an interface.
CGLIB generated subclasses.

when other spring beans calls the original objects, it is the proxy which is actually getting invoked. This proxy will call the advice method first followed by the target method.

Note:
If one method calls another method(supposed to be advised) inside the same class then the called method is not advised, because method call never reaches the proxy and therefore never the advice.

Here is an example.
	
	@Transactional
	public void transactionalMethod(){
	}
	
	public void callsTransactionalMethod(){
		transactionalMethod();
	}

Because @Transactional is implemented using spring AOP, the transactional method is invoked through proxy, which in this case is never run as part of a transaction because it is invoked through a local method.

