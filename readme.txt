Struts-DI is a small library for Java 5.0 (and above) that enables dependency injection (DI) for EJB session beans in Struts 1.x Action classes using the standard Java EE @EJB annotation.

Using Struts-DI is easy:

   1. Include the libary jar file (or the separate class files) in your web application's class path (usually WEB-INF/lib or WEB-INF/classes).
   2. Activate one of Struts-DI's request processors in your WEB-INF/struts-config.xml:

        <controller nocache="true"
                    processorClass="com.muchsoft.struts.annotations.RequestProcessor" />

    Or, if you are using Tiles:

      <controller nocache="true"
                  processorClass="com.muchsoft.struts.annotations.TilesRequestProcessor" />

That's it :-)

Now you can use @EJB as if your Action classes were Java EE "managed" classes:

public class MyAction extends org.apache.struts.action.Action {

  @EJB
  private MySessionBean msb;

  @Override
  public ActionForward execute(...) throws Exception {

    String backendResponse = msb.myCall(...);
    ...
  }
}

Currently, only Glassfish-style JNDI names are supported, i.e. the session beans must use their fully qualified interface name as their global JNDI name.

Annotation attributes (like mappedName), setter injection and class annotation for local JNDI definitions are not supported yet, but that will definitely change in future versions of the library.


Struts-DI is hosted at <http://code.google.com/p/struts-di/> where you can checkout the source code via Subversion.

Struts-DI is released under the new BSD licens (see license.txt and <http://en.wikipedia.org/wiki/BSD_license>).

Copyright (c)2009 Thomas Much, <mailto:thomas@muchsoft.com>
