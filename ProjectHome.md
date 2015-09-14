Struts-DI is a small library for Java 5.0 (and above) that enables dependency injection (DI) for EJB session beans in Struts 1.x Action classes using the standard Java EE @EJB annotation.

Using Struts-DI is easy:

  1. Include the libary jar file (or the separate class files) in your web application's class path (usually WEB-INF/lib or WEB-INF/classes).
  1. Activate one of Struts-DI's request processors in your WEB-INF/struts-config.xml:
```
  <controller nocache="true" processorClass="com.muchsoft.struts.annotations.RequestProcessor">
    <set-property property="namingStrategy" value="com.muchsoft.util.naming.GlassFishNamingStrategy" />
  </controller>
```
> Or, if you are using Tiles:
```
  <controller nocache="true" processorClass="com.muchsoft.struts.annotations.TilesRequestProcessor">
    <set-property property="namingStrategy" value="com.muchsoft.util.naming.GlassFishNamingStrategy" />
  </controller>
```

That's it :-)

Now you can use @EJB as if your Action classes were Java EE "managed" classes:

```
public class MyAction extends org.apache.struts.action.Action {

  @EJB
  private MySessionBean msb;

  @Override
  public ActionForward execute(...) throws Exception {

    String backendResponse = msb.myCall(...);
    ...
  }
}
```

Currently, only a GlassFish-style JNDI naming strategy is included. If your application server uses a different naming style, you can set the "name" attribute in each @EJB annotation. Alternatively, you can easily write your own implementation of com.muchsoft.util.naming.JndiNamingStrategy.