/**
 * 
 */
package org.dirigent.metafacade.builder.classloader;

import java.util.Iterator;

import junit.framework.TestCase;

import org.dirigent.metafacade.IAttribute;
import org.dirigent.metafacade.IElement;

/**
 * @author khubl
 *
 */
public class TestClassLoaderBuilder extends TestCase {
	public void testGetClassName() {
		String className=new ClassLoaderBuilder().getClassName("com/gem/TestClass.java");
		assertEquals("com.gem.TestClass", className);
		className=new ClassLoaderBuilder().getClassName("com.gem.TestClass");
		assertEquals("com.gem.TestClass", className);
	}
	
	public void testGetElement() {
		IElement element=new ClassLoaderBuilder().getMetafacade("org.dirigent.metafacade.builder.classloader.SampleClass");
		ClassDecorator sampleClass=(ClassDecorator)element;
		assertEquals("SampleClass", sampleClass.getName());
		assertEquals("org.dirigent.metafacade.builder.classloader.SampleClass", sampleClass.getType());
		assertEquals("org.dirigent.metafacade.builder.classloader",sampleClass.getPackageName());
		assertEquals("org/dirigent/metafacade/builder/classloader",sampleClass.getPackagePath());
		
		Iterator<IAttribute> instanceFieldIterator=sampleClass.getInstanceAttributes().iterator();
		
		FieldDecorator nameField=(FieldDecorator)instanceFieldIterator.next();
		assertEquals("name", nameField.getName());
		assertEquals("java.lang.String", nameField.getType());
		
		FieldDecorator childrensField=(FieldDecorator)instanceFieldIterator.next();
		assertEquals("childrens", childrensField.getName());
		assertEquals("java.util.Collection<java.lang.String>", childrensField.getType());
		
		Iterator<IAttribute> staticFieldIterator=sampleClass.getStaticAttributes().iterator();
		
		FieldDecorator emptyStringField=(FieldDecorator)staticFieldIterator.next();
		assertEquals("EMPTY_STRING", emptyStringField.getName());
		assertEquals("java.lang.String", emptyStringField.getType());
		
		assertEquals(3, sampleClass.getAttributes().size());
		
		
	}
}
