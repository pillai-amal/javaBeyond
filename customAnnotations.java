import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface ChangeDetail {
	String authorName();

	String methodName();

	int id() default 1004;

}

abstract class Phone {
	String mobileName;

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}
	
	abstract double price();

}

@ChangeDetail(authorName = "Sam" , methodName = "price")
class Nokia extends Phone {

	@Override
	double price() {
		return 15000;
	}
	
	double calc() {
		return 13000;
	}

}


class CustomAnnotationDemo {
	public static void main(String[] args) throws Exception {
	    Phone ph = new Nokia();
		Class c = ph.getClass();
		
		// Fetches the list of annotations of the class, Nokia
		Annotation annotations[] = c.getDeclaredAnnotations();
		for (Annotation annotation : annotations) {
			// Print the name of the annotation
			System.out.println(annotation.annotationType().getName());
			ph.setMobileName("Nokia 9");
			System.out.println("Mobile Name : " + ph.getMobileName());
			// Fetches the list of attributes of the annotation
			Method[] fields = annotation.annotationType().getDeclaredMethods();
			for (Method field : fields) {
				// print the attribute name
				System.out.print(field.getName());
				// fetch the value for the attribute
				Object value = field.invoke(annotation, (Object[]) null);
				System.out.println("\t" + value);
			}
		}
	}
}