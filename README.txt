this project contains a way to read and write base (java) objects (Strings, ints, Collections, Arrays exc)
 to xml utf-8 strings in a way that is independent of the protocol (web socket, http) and may be used for storage (on disk)
and with GWT (although GWT-RPC works great too, you need to use a different solution for disk storage,
and web sockets, and java to java commuication, exc).

It also provides a set of classes which can be used to generate a xml schema from java classes, which generally works but
there are some issues including;

Collections,Arrays and Maps are scoped to the any xml element, which 
can cause runtime errors on xml reads, as a String [] could look like a int [] in the xml ie;
<L><s>abc</s><s>def</s></L>
<L><i>123</i><i>234</i></L>
So if the class had a String [] field it would throw a exception when it tried to read the later xml

While this particular problem could be overcome by defining a collection for each type,
and map of every key, value type combination this still would NOT solve the problem down the road of 
collections with interface generics ie;
interface I_Name {
	String getName();
}

class MyClass {
  List<I_Name> names;
}


 



